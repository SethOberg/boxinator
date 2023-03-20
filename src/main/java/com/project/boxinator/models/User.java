package com.project.boxinator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.boxinator.enums.TypeOfUser;
import com.project.boxinator.models.dtos.CreateUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="shipmentUser")
public class User {
    @Id
    private String uuid;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 50)
    private String password;
    @Column(length = 50)
    private String dateOfBirth; //Kanske vill byta till date
    private String country;
    @Column(length = 50)
    private Integer zipCode;
    @Column(length = 50)
    private String contactNumber;
    @Column(nullable = false)
    private TypeOfUser typeOfUser;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Shipment> shipments = new HashSet<>();


    public User(String uuid, String email, TypeOfUser typeOfUser) {
        this.uuid = uuid;
        this.email = email;
        this.country = country;
        this.typeOfUser = typeOfUser;
    }

    public User(String uuid, CreateUserDTO userDTO) {
        this.uuid = uuid;
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.dateOfBirth = userDTO.getDateOfBirth();
        this.country = userDTO.getCountry();
        this.zipCode = userDTO.getZipCode();
        this.contactNumber = userDTO.getContactNumber();
        this.typeOfUser = userDTO.getTypeOfUser();
        this.shipments = new HashSet<>();
    }

    public void addShipmentToUser(Shipment shipment) { shipments.add(shipment); }
    public void add(User user) {
    }

    public String getId() {
        return uuid;
    }

    public void setId(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(TypeOfUser typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public Set<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(Set<Shipment> shipments) {
        this.shipments = shipments;
    }
}
