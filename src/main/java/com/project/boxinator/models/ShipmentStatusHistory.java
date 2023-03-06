package com.project.boxinator.models;

import com.project.boxinator.enums.ShipmentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class ShipmentStatusHistory {
    private ShipmentStatus shipmentStatus;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}
