package org.ksea.springdata.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mexican on 2017/4/15.
 */
@Entity
@Table(name = "JPA_PERSON")
public class Person {
    @Id
    @GeneratedValue
    private Integer id;
    private String lastName;
    private String email;
    private Date birthday;

    @JoinColumn(name = "addressId")
    @ManyToOne //多对一
    private  Address  address ;

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
