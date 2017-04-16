package org.ksea.springdata.dao;

import org.ksea.springdata.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

/**
 * Created by mexican on 2017/4/16.
 */
@RepositoryDefinition(domainClass = Person.class, idClass = Integer.class)
public interface PersonRepositoryToModifying {

    @Modifying
    @Query("update Person p set p.lastName=:lastName where p.id=:id")
    void updatePersonById(@Param("lastName") String lastName, @Param("id") Integer id);

    @Modifying
    @Query("delete  from  Person p  where p.id=:id")
    void deletePersonById(@Param("id") Integer id);

}
