package org.ksea.springdata.dao;

import org.ksea.springdata.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by mexican on 2017/4/16.
 */
public interface PersonPageingAndSortingRepository extends PagingAndSortingRepository<Person, Integer> {
}
