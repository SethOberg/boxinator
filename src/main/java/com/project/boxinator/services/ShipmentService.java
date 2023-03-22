package com.project.boxinator.services;

import com.project.boxinator.enums.ShipmentStatus;
import com.project.boxinator.exceptions.ShipmentNotFoundException;
import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.ShipmentStatusHistory;
import com.project.boxinator.models.User;
import com.project.boxinator.models.dtos.ShipmentDto;
import com.project.boxinator.repositories.ShipmentRepository;
import com.project.boxinator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment getShipmentById(Integer id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ShipmentNotFoundException(id));
    }

    public void addShipment(Shipment shipment) {
        System.out.println("Received shipment: " + shipment);
        ShipmentStatusHistory SSHCreate = new ShipmentStatusHistory(ShipmentStatus.CREATED, shipment);
        shipment.addSSHToShipment(SSHCreate);
        shipmentRepository.save(shipment);
    }

    public void addGuestShipment(Shipment shipment, String email) throws Exception{
        //create a user that only stores email and a random ID since necessary
       User user1 = userRepository.findByEmail(email);
        if(user1 == null){
        User user = new User();
        user.setEmail(email);
        user.setId(String.valueOf(UUID.randomUUID()));
        user.setTypeOfUser(shipment.getUser().getTypeOfUser());
        shipment.addUserToShipment(user);
        System.out.println("Received shipment: " + shipment);
        ShipmentStatusHistory SSHCreate = new ShipmentStatusHistory(ShipmentStatus.CREATED, shipment);
        shipment.addSSHToShipment(SSHCreate);
        //shipment.setId(UUID.randomUUID());
        }else {

                shipment.addUserToShipment(user1);
                System.out.println(shipment.getBoxColour());
                System.out.println("Received shipment: " + shipment);
                ShipmentStatusHistory SSHCreate = new ShipmentStatusHistory(ShipmentStatus.CREATED, shipment);
                shipment.addSSHToShipment(SSHCreate);
        }
        shipmentRepository.save(shipment);
    }

    public Shipment update(Shipment shipment) {
        getShipmentById(shipment.getId());
        return shipmentRepository.save(shipment);
    }



}
