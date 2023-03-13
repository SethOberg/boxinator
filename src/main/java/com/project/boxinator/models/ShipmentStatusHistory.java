package com.project.boxinator.models;

import com.project.boxinator.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class ShipmentStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private ShipmentStatus shipmentStatus;
    private Date date;

//    @ManyToOne
//    @JoinColumn(name = "shipment_id")
//    private Shipment shipment;
}
