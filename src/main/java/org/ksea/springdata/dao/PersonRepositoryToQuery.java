package org.ksea.springdata.dao;

/**
 * Created by mexican on 2017/4/16.
 */

import org.ksea.springdata.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Person.class, idClass = Integer.class)
public interface PersonRepositoryToQuery {

    //通过query查询，获取id最大的person
    @Query(value = "select p from Person p where p.id=(select  max (p2.id) from Person p2)")
    Person findByMaxId();

    //基于Query查询的传递参数的方式>根据占位符，占位符是必须按照顺序的
    @Query(value = "select  p  from Person p where p.lastName=?1 and p.email=?2")
    List<Person> findByLastNameAndEmail(String lastName, String email);


    /*基于Query查询的传递参数的方式>根据参数命名的方式
      命名传参的好处在于，不用根据顺序
      其中@Param("")注解，参数绑定必须指明需要传递的参数，这个与Mybaties一样
     */
    @Query(value = "select p from Person p where p.lastName=:lastName and p.email=:email")
    List<Person> findByLastNameAndEmail2(@Param("email") String email, @Param("lastName") String lastName);


    /*query中基于like的占位符查询*/
    @Query("select p from Person p where p.lastName  like %?1%  and p.email like %?2%")
    List<Person> findByLike1(String lastName, String email);

    /*query中基于like的命名方式查询*/
    @Query("select p from Person p where p.lastName  like %:lastName%  and p.email like %:email%")
    List<Person> findByLike2(@Param("lastName") String lastName, @Param("email") String email);


}
