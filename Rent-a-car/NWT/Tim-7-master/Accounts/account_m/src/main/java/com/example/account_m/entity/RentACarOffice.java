package com.example.account_m.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class RentACarOffice {

    @Id
    @GeneratedValue
    @NotNull(message = "The id must not be null!")
    private long id;

    @Size(min = 3, max = 50, message = "The length of the office name must be between 3 and 50 characters!")
    private String officeName;

    @Min(value = 0L, message = "The cars number must be positive!")
    private int carsNumber;

    @NotNull(message = "The location id must not be null!")
    private int locationID;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public int getCarsNumber() {
        return carsNumber;
    }

    public void setCarsNumber(int carsNumber) {
        this.carsNumber = carsNumber;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
}
