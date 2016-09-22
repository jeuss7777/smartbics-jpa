package com.smartbics.springdata.repositories;

import com.smartbics.springdata.model.CreditCard;
import com.smartbics.springdata.model.Person;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

import static com.smartbics.springdata.model.CreditCard.createCard;
import static com.smartbics.springdata.model.Person.createPerson;

@Transactional
public class PersonRepositoryImpl implements PersonRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Person createWithCard(String name, String number) {
        CreditCard creditCard = createCard(number);
        Person person = createPerson(name, Arrays.asList(creditCard));
        entityManager.persist(person);
        return person;
    }
}