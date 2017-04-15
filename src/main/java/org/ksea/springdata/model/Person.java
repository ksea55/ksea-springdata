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
    private Date brityday;

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

    public Date getBrityday() {
        return brityday;
    }

    public void setBrityday(Date brityday) {
        this.brityday = brityday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", brityday=" + brityday +
                '}';
    }
}