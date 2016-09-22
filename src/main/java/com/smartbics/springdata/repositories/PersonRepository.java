package com.smartbics.springdata.repositories;

import com.smartbics.springdata.model.Person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>, PersonRepositoryCustom {

    Page<Person> findAll(Pageable pageable);

    Collection<Person> findByCreditCardsNumber(String number);

    Collection<Person> findByCreditCardsOperationsAmountGreaterThanEqualOrderByNameAsc(BigDecimal amount);

    @Query("select p from Person p where p.name like %?1%")
    Collection<Person> findByNameContains(String name);
}