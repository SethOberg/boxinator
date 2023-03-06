package com.project.boxinator.models;

import com.project.boxinator.enums.TypeOfUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 50)
    private String password;
    @Column(length = 50)
    private Date dateOfBirth;
    @Column(length = 50, nullable = false)
    private String country;
    @Column(length = 50)
    private Integer zipCode;
    @Column(length = 50)
    private Date contactNumber;
    private TypeOfUser typeOfUser;
    private Set<Shipment> shipments = new HashSet<>();


}
