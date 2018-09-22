package com.example.account_m.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person {

    @Id
    @GeneratedValue
    @NotNull(message = "The id must not be null!")
    private long id;

    @Size(min = 3, max = 50, message = "The length of the name must be between 3 and 50 characters!")
    private String name;

    @Size(min = 3, max = 50, message = "The length of the surname must be between 3 and 50 characters!")
    private String surname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
