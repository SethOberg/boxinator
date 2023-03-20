package com.project.boxinator.models.dtos;

import com.project.boxinator.enums.TypeOfUser;

public class CreateUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth; //Kanske vill byta till date
    private String country;
    private Integer zipCode;
    private String contactNumber;
    private TypeOfUser typeOfUser;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String firstName, String lastName, String email, String dateOfBirth, String country, Integer zipCode, String contactNumber, TypeOfUser typeOfUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.zipCode = zipCode;
        this.contactNumber = contactNumber;
        this.typeOfUser = typeOfUser;
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
}
