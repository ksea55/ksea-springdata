package org.ksea.springdata.dao;

import org.ksea.springdata.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mexican on 2017/4/16.
 * 用于执行其添加
 */
public interface PersonCrudRepository extends CrudRepository<Person, Integer> {


}
