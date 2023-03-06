package com.project.boxinator.services;

import com.project.boxinator.exceptions.ShipmentNotFoundException;
import com.project.boxinator.models.Shipment;
import com.project.boxinator.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment getShipmentById(Integer id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ShipmentNotFoundException(id));
    }

}
