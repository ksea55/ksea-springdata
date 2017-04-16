package org.ksea.springdata.test;

import org.junit.Test;
import org.ksea.springdata.dao.PersonCrudRepository;
import org.ksea.springdata.dao.PersonRepository;
import org.ksea.springdata.dao.PersonRepositoryToQuery;
import org.ksea.springdata.model.Person;
import org.ksea.springdata.service.PersonService;
import org.ksea.springdata.service.PersonServiceToCrudRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by mexican on 2017/4/15.
 */
public class SpringDataTest {

    private ApplicationContext applicationContext = null;
    private PersonRepository personRepository = null;

    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        personRepository = applicationContext.getBean(PersonRepository.class);
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

    /**
     * 用于测试命名规则定义的方法
     */
    @Test
    public void byNameTest() {

        Person person = personRepository.findByIdGreaterThanAndLastName(1, "hai");
        System.out.println(person); //打印结果Person{id=2, lastName='hai', email='mexican@qq.com', birthday=2017-04-16 00:01:17.0}

        System.out.println("----------------------------------------------------");

        List<Person> personList = personRepository.findByLastNameStartingWithAndIdLessThan("hai", 4);
        System.out.println(personList); //运行结果:[Person{id=1, lastName='haiyang', email='ksea@qq.com', birthday=2017-04-16 00:00:00.0}, Person{id=2, lastName='hai', email='mexican@qq.com', birthday=2017-04-16 00:01:17.0}]


        System.out.println("----------------------------------------------------");
        List<Person> persons = personRepository.findByLastNameEndingWithAndIdLessThan("yang", 4);
        System.out.println(persons); //运行结果:[Person{id=1, lastName='haiyang', email='ksea@qq.com', birthday=2017-04-16 00:00:00.0}, Person{id=3, lastName='yang', email='aa@qq.com', birthday=2017-04-26 00:01:40.0}]


        System.out.println("----------------------------------------------------");
        persons = personRepository.findByEmailInOrBirthdayLessThan(Arrays.asList("aa@qq.com", "bb@126.com"), new Date());
        System.out.println(persons); //[Person{id=1, lastName='haiyang', email='ksea@qq.com', birthday=2017-04-16 00:00:00.0}, Person{id=2, lastName='hai', email='mexican@qq.com', birthday=2017-04-16 00:01:17.0}, Person{id=4, lastName='kabc', email='bb@126.com', birthday=2017-04-04 00:01:57.0}]

        System.out.println("----------------------------------------------------");
        persons = personRepository.findByEmailInAndBirthdayLessThan(Arrays.asList("aa@qq.com", "bb@126.com"), new Date());
        System.out.println(persons); //[Person{id=4, lastName='kabc', email='bb@126.com', birthday=2017-04-04 00:01:57.0}]


        System.out.println("----------------------------------------------------");
        persons = personRepository.findByAddressIdGreaterThan(2);
        System.out.println(persons);
        /*
        * 这里会自动帮我们进行关联
        * Hibernate:
            select
                person0_.id as id1_1_,
                person0_.addressId as addressI5_1_,
                person0_.birthday as birthday2_1_,
                person0_.email as email3_1_,
                person0_.lastName as lastName4_1_
            from
                JPA_PERSON person0_
            left outer join
                jpa_address address1_
                    on person0_.addressId=address1_.id
            where
                address1_.id>?
        []

        * */


        System.out.println("----------------------------------------------------");
        persons = personRepository.findByAddress_idGreaterThan(2);
        System.out.println(persons);
    }

    /*基于注解@Query的查询*/
    @Test
    public void queryTest() {
        PersonRepositoryToQuery repositoryToQuery = applicationContext.getBean(PersonRepositoryToQuery.class);
        Person person = repositoryToQuery.findByMaxId();
        System.out.println(person);  //Person{id=4, lastName='kabc', email='bb@126.com', birthday=2017-04-04 00:01:57.0}

        System.out.println("--------------------------------------------------------------------");

        List<Person> persons = repositoryToQuery.findByLastNameAndEmail("yang", "aa@qq.com");
        System.out.println(persons);

        System.out.println("--------------------------------------------------------------------");

        persons = repositoryToQuery.findByLastNameAndEmail2("aa@qq.com", "yang");
        System.out.println(persons);  //[Person{id=3, lastName='yang', email='aa@qq.com', birthday=2017-04-26 00:01:40.0}]

        System.out.println("--------------------------------------------------------------------");
        persons = repositoryToQuery.findByLike1("ya", "aa");
        System.out.println(persons); //[Person{id=3, lastName='yang', email='aa@qq.com', birthday=2017-04-26 00:01:40.0}]


        System.out.println("--------------------------------------------------------------------");
        persons = repositoryToQuery.findByLike2("ya", "aa");
        System.out.println(persons); //[Person{id=3, lastName='yang', email='aa@qq.com', birthday=2017-04-26 00:01:40.0}]

        System.out.println("--------------------------------------------------------------------");

        person = repositoryToQuery.findByLastNameAndEmailToNativeSQL("yang", "aa@qq.com");

        System.out.println(person); //Person{id=3, lastName='yang', email='aa@qq.com', birthday=2017-04-26 00:01:40.0}
    }

    @Test
    public void modifyingTest() {
        PersonService personService = applicationContext.getBean(PersonService.class);
        personService.updatePersonLastNameById("k.sea√", 4);

        System.out.println("----------------------------------");
        personService.removePersonById(4);
    }

    @Test
    public void batchAddPersonTest() {
        PersonServiceToCrudRepository serviceToCrudRepository = applicationContext.getBean(PersonServiceToCrudRepository.class);
        List<Person> personList = new ArrayList<Person>();
        Person p1 = new Person();
        p1.setBirthday(new Date());
        p1.setEmail("p1@126.com");
        p1.setLastName("p1");


        Person p2 = new Person();
        p2.setBirthday(new Date());
        p2.setEmail("p2@126.com");
        p2.setLastName("p2");


        Person p3 = new Person();
        p3.setBirthday(new Date());
        p3.setEmail("p3@126.com");
        p3.setLastName("p3");
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);

        serviceToCrudRepository.batchAddPerson(personList);
    }
}
