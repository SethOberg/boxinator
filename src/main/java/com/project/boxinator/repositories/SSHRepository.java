package com.project.boxinator.repositories;

import com.project.boxinator.models.ShipmentStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SSHRepository extends JpaRepository<ShipmentStatusHistory, Integer> {
}
