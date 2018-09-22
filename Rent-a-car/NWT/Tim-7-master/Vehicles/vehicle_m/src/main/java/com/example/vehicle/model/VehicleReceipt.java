package com.example.vehicle.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class VehicleReceipt {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    @NotNull(message = "The id must not be null!")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id")
    @NotNull(message = "The id must not be null!")
    private Vehicle vehicleID;
    @Valid
    @Positive
    private float costPerDay;
    @Valid
    @Positive
    private float deposit;

    public VehicleReceipt() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vehicle getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Vehicle vehicleID) {
        this.vehicleID = vehicleID;
    }

    public float getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(float costPerDay) {
        this.costPerDay = costPerDay;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }
}
