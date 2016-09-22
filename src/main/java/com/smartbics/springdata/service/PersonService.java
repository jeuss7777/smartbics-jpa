package com.smartbics.springdata.service;

import static com.smartbics.springdata.model.CreditCard.createCard;
import static com.smartbics.springdata.model.Person.createPerson;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.smartbics.springdata.model.CreditCard;
import com.smartbics.springdata.model.Person;
import com.smartbics.springdata.repositories.PersonRepository;
//import com.smartbics.springdata.repositories.PersonRepositoryImpl;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Collection<Person> findByCreditCardsNumber(String number){
		return personRepository.findByCreditCardsNumber(number);
	}
	
	
	public Collection<Person> findByCreditCardsOperationsAmountGreaterThanEqualOrderByNameAsc(BigDecimal amount){
		return personRepository.findByCreditCardsOperationsAmountGreaterThanEqualOrderByNameAsc(amount);
	}

    
    public Collection<Person> findByNameContains(String name){
    	return personRepository.findByNameContains(name);
    }
	
    public Page<Person> findAll(Pageable pageable){
    	return personRepository.findAll(pageable);
    }
    
   /* public Person createWithCard(String name, String number) {
        
        return personRepository.createWithCard(name, number);
    }*/
}
