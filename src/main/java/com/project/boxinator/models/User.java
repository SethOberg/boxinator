package com.project.boxinator.models;

import com.project.boxinator.enums.TypeOfUser;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
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
    @Column(nullable = false)
    private TypeOfUser typeOfUser;
    @OneToMany(mappedBy = "User")
    private Set<Shipment> shipments = new HashSet<>();


}
