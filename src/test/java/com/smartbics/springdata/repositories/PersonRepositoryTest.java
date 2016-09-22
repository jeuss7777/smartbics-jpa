package com.smartbics.springdata.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.smartbics.springdata.config.AppConfig;
import com.smartbics.springdata.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:META-INF/database.xml")
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void save() {
        Person newPerson = new Person();
        newPerson.setName("New Person");
        personRepository.save(newPerson);

        boolean match = false;
        for (Person person : personRepository.findAll()) {
            match = person.getName().equals(newPerson.getName());
            if (match) break;
        }
        assertTrue(match);
    }

    @Test
    public void findOne() {
        Person person = personRepository.findOne(1L);
        assertTrue(person.getName().equals("Morgan Freeman"));
    }

    @Test
    public void findAll() {
        Collection<Person> persons = (Collection<Person>) personRepository.findAll();
        assertEquals(4, persons.size());
    }

    @Test
    public void findAllPagination() {
        Page<Person> personPage = personRepository.findAll(new PageRequest(0, 2));

        assertEquals(2, personPage.getTotalPages());
        for (Person person : personPage.getContent()) {
            assertTrue(person.getId() <= 2);
        }
    }

    @Test
    public void delete() {
        personRepository.delete(personRepository.findOne(1L));
        assertEquals(3, ((Collection<Person>) personRepository.findAll()).size());
    }

    @Test
    public void createWithCard() {
        String name = "New Person";
        String cardNumber = "7777";
        Person person = personRepository.createWithCard(name, cardNumber);

        Person newPerson = personRepository.findOne(person.getId());
        assertTrue(newPerson.getName().equals(person.getName()));
        assertEquals(1, newPerson.getCreditCards().size());
    }

    @Test
    public void searchByCreditCardNumber() {
        String number = "1234";
        Collection<Person> persons = personRepository.findByCreditCardsNumber(number);

        assertEquals(1, persons.size());
        assertTrue("Morgan Freeman".equals(persons.iterator().next().getName()));
    }

    @Test
    public void findByAmount() {
        Collection<Person> persons = personRepository.findByCreditCardsOperationsAmountGreaterThanEqualOrderByNameAsc(new BigDecimal(30));

        assertEquals(2, persons.size());
    }

    @Test
    public void findByNameContains() {
        Collection<Person> persons = personRepository.findByNameContains("an");

        prettyPrint(persons);
        assertEquals(3, persons.size());
    }

    private <T> void prettyPrint(Iterable<T> iterable) {
        for (T obj : iterable) {
            System.out.println(obj);
        }
    }
}