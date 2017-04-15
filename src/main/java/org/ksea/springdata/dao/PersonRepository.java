package org.ksea.springdata.dao;

import org.ksea.springdata.model.Person;
import org.springframework.data.repository.Repository;

/**
 * Created by mexican on 2017/4/15.
 * 持久化层的接口都需要继承来自包org.springframework.data.repository下的Repository接口
 * 其中其泛型 第一个参数是你要处理的实体bean 第二个参数指明主键Id的类型
 */
public interface PersonRepository extends Repository<Person, Integer> {
    Person getByLastName(String lastName);
}
