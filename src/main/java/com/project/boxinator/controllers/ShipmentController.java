package com.project.boxinator.controllers;

import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.User;
import com.project.boxinator.services.ShipmentService;
import com.project.boxinator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private UserService userService;

    @PostMapping
    public void addShipment (@RequestBody Shipment shipment) {
        shipmentService.addShipment(shipment);
    }

    @GetMapping
    public ResponseEntity getAllShipments() {
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }

    @GetMapping("{shipmentId}")
    public ResponseEntity getShipment(@PathVariable Integer shipmentId) {
        return ResponseEntity.ok(shipmentService.getShipmentById(shipmentId));
    }

    @PutMapping("{shipmentId}")
    public void updateShipment(@PathVariable Integer shipmentId, @RequestBody Shipment shipment) {
        if(shipmentId != shipment.getId())
            ResponseEntity.badRequest().build();
        shipmentService.update(shipment);
    }

//    @PutMapping("{userId}/shipments/{shipmentId}")
//    public ResponseEntity addUserToShipment(@PathVariable Integer userId,
//                                            @PathVariable Integer shipmentId) {
//        User user = userService.getUserById(userId);
//        Shipment shipment = shipmentService.getShipmentById(shipmentId);
//        shipment.setUser(user);
//        shipmentService.update(shipment);
//        return ResponseEntity.ok("User added to shipment");
//    }




}
