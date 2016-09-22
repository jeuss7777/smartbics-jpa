package com.smartbics.springdata.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = {ALL}, fetch = EAGER, mappedBy = "person")
    private Set<CreditCard> creditCards = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creditCards=" + creditCards +
                '}';
    }

    public static Person createPerson(String name, Collection<CreditCard> cards) {
        Person person = new Person();
        person.setName(name);

        for (CreditCard card : cards) {
            card.setPerson(person);
            person.getCreditCards().add(card);
        }

        return person;
    }
}