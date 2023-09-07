package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String registrationNumber;
    private String ownerName;
    private String brand;
    private Date registrationExpires;
    private String createdBy;
    private Date creationTime;
    private String modifiedBy;
    private Date modifiedTime;

    // Default constructor
    public Vehicle() {
    }

    // Constructor with all attributes except 'id'
    public Vehicle(String registrationNumber, String ownerName, String brand, Date registrationExpires,
                   String createdBy, Date creationTime, String modifiedBy, Date modifiedTime) {
        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;
        this.brand = brand;
        this.registrationExpires = registrationExpires;
        this.createdBy = createdBy;
        this.creationTime = creationTime;
        this.modifiedBy = modifiedBy;
        this.modifiedTime = modifiedTime;
    }

    // Getters and setters for all attributes
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getRegistrationExpires() {
        return registrationExpires;
    }

    public void setRegistrationExpires(Date registrationExpires) {
        this.registrationExpires = registrationExpires;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}

    


