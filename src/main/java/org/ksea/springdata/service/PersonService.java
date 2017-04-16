package org.ksea.springdata.service;

import org.ksea.springdata.dao.PersonRepositoryToModifying;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mexican on 2017/4/16.
 */
@Service
public class PersonService {
    @Autowired
    private PersonRepositoryToModifying personRepositoryToModifying;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void updatePersonLastNameById(String lastName, Integer id) {
        personRepositoryToModifying.updatePersonById(lastName, id);
    }


    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void removePersonById(Integer id) {
        personRepositoryToModifying.deletePersonById(id);
    }

}
