package com.project.boxinator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.boxinator.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private ShipmentStatus shipmentStatus;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonIgnore
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }


    public ShipmentStatusHistory(ShipmentStatus shipmentStatus, Shipment shipment) {
        this.shipmentStatus = shipmentStatus;
        this.shipment = shipment;
    }
}


