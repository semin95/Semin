package com.example.account_m.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class Salesman extends Person{

    private int JMBG;

    @Past
    private Date birthDate;

    @DecimalMin(value = "0", message = "The cars number must be positive!")
    private double pay;


    //@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "rentACarOffice_id")
    private RentACarOffice rentACarOffice;


    public int getJMBG() {
        return JMBG;
    }

    public void setJMBG(int JMBG) {
        this.JMBG = JMBG;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public RentACarOffice getRentACarOffice() {
        return rentACarOffice;
    }

    public void setRentACarOffice(RentACarOffice rentACarOffice) {
        this.rentACarOffice = rentACarOffice;
    }
}
