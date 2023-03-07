package com.project.boxinator.controllers;

import com.project.boxinator.models.Shipment;
import com.project.boxinator.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void addShipment (@RequestBody Shipment shipment) {
        shipmentService.addShipment(shipment);

    }

}
