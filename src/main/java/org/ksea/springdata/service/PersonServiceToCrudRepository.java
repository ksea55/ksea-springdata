package org.ksea.springdata.service;

import org.ksea.springdata.dao.PersonCrudRepository;
import org.ksea.springdata.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by mexican on 2017/4/16.
 */
@Service
public class PersonServiceToCrudRepository {
    @Autowired
    private PersonCrudRepository personCrudRepository;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void batchAddPerson(List<Person> personList) {
        personCrudRepository.save(personList);
    }
}
