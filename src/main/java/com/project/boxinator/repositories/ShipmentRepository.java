package com.project.boxinator.repositories;

import com.project.boxinator.models.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    Set<Shipment> findAllByUserId(String userId);
}
