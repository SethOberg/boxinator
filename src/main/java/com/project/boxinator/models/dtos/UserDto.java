package com.project.boxinator.models.dtos;

import com.project.boxinator.enums.TypeOfUser;
import com.project.boxinator.models.Shipment;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private int Id;
    private String firstName;
    private String lastName;
    private String password;
    private String dateOfBirth;
    private String country;
    private Integer zipCode;
    private String contactNumber;
    private TypeOfUser typeOfUser;
    private Set<Integer> shipmentsByUser = new HashSet<>();




}
