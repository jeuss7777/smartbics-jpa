package com.smartbics.springdata.controller;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartbics.springdata.model.Person;
import com.smartbics.springdata.service.PersonService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/ccards")
public class PersonController {

	@Autowired
	private PersonService personService;

	public Collection<Person> findByCreditCardsNumber(String number) {
		return personService.findByCreditCardsNumber(number);
	}

	public Collection<Person> findByCreditCardsOperationsAmountGreaterThanEqualOrderByNameAsc(
			BigDecimal amount) {
		return personService
				.findByCreditCardsOperationsAmountGreaterThanEqualOrderByNameAsc(amount);
	}

	@RequestMapping(value ="/find/{contains}", method = RequestMethod.GET))
	public Collection<Person> findByNameContains(@PathVariable String contains) {
		return personService.findByNameContains(contains);
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public Page<Person> findAll(Pageable pageable) {
		return personService.findAll(pageable);
	}

	/*public Person createWithCard(String name, String number) {

		return personService.createWithCard(name, number);
	}*/
}
