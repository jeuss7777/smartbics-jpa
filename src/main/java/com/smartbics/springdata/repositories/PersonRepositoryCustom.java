package com.smartbics.springdata.repositories;

import com.smartbics.springdata.model.Person;

public interface PersonRepositoryCustom {

    Person createWithCard(String name, String number);

}
