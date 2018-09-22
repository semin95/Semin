package com.example.vehicle.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EntityScan
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    @NotNull(message = "The id must not be null!")
    private Integer id;
    @Valid
    @Size(min = 3, max = 20, message = "The length of the brand must be between 3 and 20 characters!")
    private String name;
    @Valid
    @Size(min = 3, max = 20, message = "The length of the brand must be between 3 and 20 characters!")
    private String address;

    public Location() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
