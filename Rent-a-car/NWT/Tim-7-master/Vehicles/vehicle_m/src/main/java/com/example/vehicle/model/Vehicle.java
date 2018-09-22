package com.example.vehicle.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    @NotNull(message = "The id must not be null!")
    private Integer id;
    @Valid
    @Size(min = 3, max = 20, message = "The length of the brand must be between 3 and 20 characters!")
    private String brand;
    @Valid
    @Size(min = 3, max = 20, message = "The length of the name must be between 3 and 20 characters!")
    private String name;
    @Valid
    @Size(min = 3, max = 20, message = "The length of the name must be between 3 and 20 characters!")
    private String type;
    @Valid
    @Min(value = 0L, message = "Year must be positive!")
    private Integer producedYear;
    @Valid
    @Size(min = 6, max = 9, message = "The length of the name must be between 6 and 9 characters! (Manual or Automatic")
    private String transmission;
    @Valid
    private String color;
    @OneToOne
    @JoinColumn(name = "id")
 //   @NotNull(message = "The id must not be null!")
    private Location locationID;
    @Valid
    private boolean available;

    public Vehicle() {
    }

    public Vehicle(@Valid String brand, @Valid String name, @Valid String type, @Valid Integer producedYear, @Valid String transmission, @Valid String color, Location locationID, @Valid boolean available) {
        this.brand = brand;
        this.name = name;
        this.type = type;
        this.producedYear = producedYear;
        this.transmission = transmission;
        this.color = color;
        this.locationID = locationID;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProducedYear() {
        return producedYear;
    }

    public void setProducedYear(Integer producedYear) {
        this.producedYear = producedYear;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Location getLocationID() {
        return locationID;
    }

    public void setLocationID(Location locationID) {
        this.locationID = locationID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString(){
        return "Vehicle{" + " id = "+ id +
                            " brand = "+ brand +
                            " name = " + name +
                            " type = " + type +
                            " producedYear = " + producedYear +
                            " transmission = " + transmission +
                            " color = " + color +
                            " locationID = " + locationID +
                            " available= " + available +
                        '}';
    }
}
