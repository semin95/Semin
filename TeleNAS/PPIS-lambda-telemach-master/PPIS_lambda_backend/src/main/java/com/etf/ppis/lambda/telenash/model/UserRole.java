package com.etf.ppis.lambda.telenash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "user_role")
public class UserRole
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String name;

    @OneToMany (mappedBy = "userRole", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> users;


    public UserRole()
    {}

    public UserRole(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                ", name='" + name + '\'' +
                '}';
    }
}