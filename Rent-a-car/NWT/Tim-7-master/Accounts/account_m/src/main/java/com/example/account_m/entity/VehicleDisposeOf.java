package com.example.account_m.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class VehicleDisposeOf {

    @Id
    @GeneratedValue
    @NotNull(message = "The id must not be null!")
    private long id;

    @NotNull(message = "The vehicle id must not be null!")
    private int vehicleID;

    @Size(max = 255, message = "The length of the reason must be less than 255 characters!")
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "rentACarOffice_id")
    private RentACarOffice rentACarOffice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "salesman_id")
    private Salesman salesman;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RentACarOffice getRentACarOffice() {
        return rentACarOffice;
    }

    public void setRentACarOffice(RentACarOffice rentACarOffice) {
        this.rentACarOffice = rentACarOffice;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }
}
