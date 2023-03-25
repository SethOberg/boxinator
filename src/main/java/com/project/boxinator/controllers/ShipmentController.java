package com.project.boxinator.controllers;

import com.project.boxinator.enums.ShipmentStatus;
import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.ShipmentStatusHistory;
import com.project.boxinator.models.User;
import com.project.boxinator.models.dtos.CreateGuestShipmentDTO;
import com.project.boxinator.models.dtos.CreateShipmentDTO;
import com.project.boxinator.models.dtos.ShipmentDto;
import com.project.boxinator.services.CountryService;
import com.project.boxinator.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/shipments")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping
    public void addShipment (@RequestBody CreateShipmentDTO shipment) {
        shipmentService.addShipmentFromDTO(shipment);
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


    @PostMapping("/createGuestShipment/{email}")
    public ResponseEntity addShipmentForGuest(@RequestBody CreateGuestShipmentDTO guestShipmentDto, @PathVariable String email) throws Exception {
        if (guestShipmentDto != null){
            //System.out.println(guestShipmentDto.getGuestUserDto().getTypeOfUser());
            //System.out.println(guestShipmentDto.getBoxColor());
            Shipment guestShipment = new Shipment(guestShipmentDto);
            shipmentService.addGuestShipment(guestShipment, email);

        }else {
            return (ResponseEntity) ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(200);
    }

    @PostMapping("/{shipmentId}/status/{shipmentStatus}")
    public ResponseEntity addShipmentStatus(@PathVariable Integer shipmentId, @PathVariable String shipmentStatus) {
        ShipmentStatus status = ShipmentStatus.valueOf(shipmentStatus);
        return ResponseEntity.ok(shipmentService.addShipmentStatus(shipmentId, status));
    }

}
