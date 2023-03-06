package com.project.boxinator.controllers;

import com.project.boxinator.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping
    public ResponseEntity getAllShipments() {
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }

    @GetMapping("{shipmentId}")
    public ResponseEntity getShipment(@PathVariable Integer shipmentId) {
        return ResponseEntity.ok(shipmentService.getShipmentById(shipmentId));
    }

}
