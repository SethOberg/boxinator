package com.project.boxinator.models;

import com.project.boxinator.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
public class ShipmentStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private ShipmentStatus shipmentStatus;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
