package com.etf.ppis.lambda.telenash.model.builders;

import com.etf.ppis.lambda.telenash.model.User;
import com.etf.ppis.lambda.telenash.model.UserRole;

/**
 * Created by Alem
 */
public class UserBuilder
{
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private UserRole userRole;

    public UserBuilder withUserName(String login)
    {
        this.userName = login;
        return this;
    }

    public UserBuilder withPassword(String password)
    {
        this.password = password;
        return this;
    }

    public UserBuilder withFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email)
    {
        this.email = email;
        return this;
    }

    public UserBuilder withPhone(String phone)
    {
        this.phone = phone;
        return this;
    }

    public UserBuilder withAddress(String address)
    {
        this.address = address;
        return this;
    }

    public UserBuilder withRole(UserRole userRole)
    {
        this.userRole = userRole;
        return this;
    }

    public User createUser()
    {
        User user = new User();
        user.setUsername(this.userName);
        user.setPassword(this.password);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setAddress(this.address);
        user.setPhone(this.phone);
        user.setUserRole(this.userRole);

        return user;
    }
}