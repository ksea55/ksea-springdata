package org.ksea.springdata.dao;

import org.ksea.springdata.model.Person;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Date;
import java.util.List;

/**
 * Created by mexican on 2017/4/15.
 * 持久化层的接口都需要继承来自包org.springframework.data.repository下的Repository接口
 * 其中其泛型 第一个参数是你要处理的实体bean 第二个参数指明主键Id的类型
 * <p>
 * 1、Repository 是一个空接口，既是一个标记接口
 * 2、当我定义的接口继承了Repository，则该接口会被IOC容易识别为一个Repository Bean纳入到IOC容器中，进而可以在该接口中定义满足一定规范的方法
 * 3、实际上我们也可以通过注解@RepositoryDefinnition来代替Repository接口
 * <p>
 * Repository仅仅是一个标识，表明任何继承它的均为仓库接口类
 * |--CrudRepository是一个实现了增删改读的接口，它继承于Repository
 * |--PagingAndSortingRepository接口继承于CrudRepository实现了一组分页排序相关的方法
 * |--JpaRepository继承自PagingAndSortingRepository实现一组JPA规范相关的方法
 * |--XxxRepository属于自定义的接口需要继承JpaRepository这样的XxxRepository接口具备了通用的数据访问控制层的能力
 * |--JpaSpecificationExecutor不属于Repository体系，实现一组JPA Criteria查询相关的方法
 */
@RepositoryDefinition(domainClass = Person.class, idClass = Integer.class)
public interface PersonRepository extends Repository<Person, Integer> {
    Person getByLastName(String lastName);

    //执行sql条件中 where id >1 and lastName ='hai'
    Person findByIdGreaterThanAndLastName(Integer id, String lastName);

    /*
    * Spring data中like组合查询
    * sql： where lastName like ?% and id< ?  lastName以什么开始进行模糊查询并且id小于某个介质
    * */
    List<Person> findByLastNameStartingWithAndIdLessThan(String lastName, Integer id);

    /*
     *
      * sql"where lastName like %? and id <?  lastName以什么结尾进行模糊查询并且id小于某个介质
     */
    List<Person> findByLastNameEndingWithAndIdLessThan(String lastName, Integer id);


    /*sql: where email in(?) or birtyday<   email邮箱在某个范围或者birthday生日小于某个时间范围 */

    List<Person> findByEmailInOrBirthdayLessThan(List<String> email, Date birthday);

    /*sql: where email in(?) and birtyday<   email邮箱在某个范围或者birthday生日小于某个时间范围 */
    List<Person> findByEmailInAndBirthdayLessThan(List<String> email, Date birthday);


    /*两张表中的级联查询
    * 这里的addressId是我们Person中的一个类属性  private  Address addressId;
    * 这里他会自动关联到 address表中
    * 执行结果:
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
    List<Person> findByAddressIdGreaterThan(Integer addressId);

    /*
    * 当某个类中与级联属性的关联字段相同的情况下，其会优先使用该类中的字段
    * 如 Person中也有一个String addressId的属性，在做查询的时候则会优先使用addressId，并会会抛出列重复的异常，
    * 对于这种情况，Spring data推荐我们在使用级联属性的时候命名加上_
    *
    * 运行结果:Hibernate:
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
    List<Person> findByAddress_idGreaterThan(Integer addressId);

}
