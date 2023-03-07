package com.project.boxinator.models;

import com.project.boxinator.enums.WeightOption;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;


@Entity
@Data
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(length = 150, nullable = false)
    private String recieverName;
    @Column(nullable = false)
    private WeightOption weightOption;

    @Column(length = 50, nullable = false)
    private String boxColour;
    @Column(nullable = false)
    private String destinationCountry;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @OneToMany(mappedBy = "shipment")
    private List<ShipmentStatusHistory> shipmentHistory = new LinkedList<>();

}
