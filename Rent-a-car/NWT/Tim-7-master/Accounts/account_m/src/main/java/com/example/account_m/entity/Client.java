package com.example.account_m.entity;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity
public class Client extends Person {

    @Email(message = "Incorrect email!")
    private String emailAdress;

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }
}
