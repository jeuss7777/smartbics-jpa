package com.smartbics.springdata.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    @ManyToOne(cascade = {ALL})
    private Person person;

    @OneToMany(cascade = {ALL}, fetch = EAGER, mappedBy = "creditCard")
    private Set<Operation> operations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", operations=" + operations +
                '}';
    }

    public static CreditCard createCard(String number) {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(number);
        return creditCard;
    }
}