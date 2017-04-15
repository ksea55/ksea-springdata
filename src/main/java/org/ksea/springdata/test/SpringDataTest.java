package org.ksea.springdata.test;

import org.junit.Test;
import org.ksea.springdata.dao.PersonRepository;
import org.ksea.springdata.model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by mexican on 2017/4/15.
 */
public class SpringDataTest {

    private ApplicationContext applicationContext = null;

    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void datasourceTest() throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void entityManagerTest() {

    }

    @Test
    public void firstSpringData() {
        PersonRepository personRepository = applicationContext.getBean(PersonRepository.class);
        Person person = personRepository.getByLastName("haiyang");
        System.out.println(person);
    }
}
