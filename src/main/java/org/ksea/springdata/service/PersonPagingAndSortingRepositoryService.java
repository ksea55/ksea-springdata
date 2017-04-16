package org.ksea.springdata.service;

import org.ksea.springdata.dao.PersonPageingAndSortingRepository;
import org.ksea.springdata.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


/**
 * Created by mexican on 2017/4/16.
 */
@Service
public class PersonPagingAndSortingRepositoryService {
    @Autowired
    private PersonPageingAndSortingRepository personPageingAndSortingRepository;

    public void findAll() {
        int page = 2 - 1; //当前页，注意page是从下标0开始的 因此第二页 就应该是2-1
        int pageSize = 2;
        //PageRequest封装了分页的信息
        //  Pageable pageable = new PageRequest(page, pageSize);

        //进行排序 对id属性进行降序排序
        Sort.Order order_id = new Sort.Order(Sort.Direction.DESC, "id");
        //对lastName属性进行升序排序
        Sort.Order order_lastName = new Sort.Order(Sort.Direction.ASC, "lastName");
        Sort sort = new Sort(order_id, order_lastName);
        Pageable pageable = new PageRequest(page, pageSize,sort);
        Page<Person> personPage = this.personPageingAndSortingRepository.findAll(pageable);
        //当前第几页因为是下标0开始的，因此需要加1
        System.out.println("当前第几页:" + (personPage.getNumber() + 1));
        System.out.println("总共多少页:" + personPage.getTotalPages());
        System.out.println("总共多少条记录:" + personPage.getTotalElements());
        System.out.println("当前页面的数据List:" + personPage.getContent());
        System.out.println("当前页面的记录数:" + personPage.getNumberOfElements());

    }

}
