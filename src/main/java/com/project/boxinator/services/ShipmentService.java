package com.project.boxinator.services;

import com.project.boxinator.enums.ShipmentStatus;
import com.project.boxinator.exceptions.ShipmentNotFoundException;
import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.ShipmentStatusHistory;
import com.project.boxinator.models.User;
import com.project.boxinator.models.dtos.CreateShipmentDTO;
import com.project.boxinator.models.dtos.ShipmentDto;
import com.project.boxinator.repositories.ShipmentRepository;
import com.project.boxinator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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
        User user = userService.getUserById(shipment.getUser().getId());
        user.addShipmentToUser(shipment);
        userRepository.save(user);
        shipmentRepository.save(shipment);
    }

    public Shipment update(Shipment shipment) {
        getShipmentById(shipment.getId());
        return shipmentRepository.save(shipment);
    }

    public void addShipmentFromDTO(CreateShipmentDTO createShipmentDTO) {
        Shipment shipment = new Shipment(createShipmentDTO);
        System.out.println("Received shipment: " + createShipmentDTO);
        ShipmentStatusHistory SSHCreate = new ShipmentStatusHistory(ShipmentStatus.CREATED, shipment);
        shipment.addSSHToShipment(SSHCreate);
        User user = userService.getUserById(createShipmentDTO.getUserId());
        user.addShipmentToUser(shipment);
        shipment.setUser(user);
        shipment.setPrice(createShipmentDTO.getPrice());
        userRepository.save(user);
        shipmentRepository.save(shipment);
    }

}
