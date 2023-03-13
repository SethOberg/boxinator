package com.project.boxinator;

import com.project.boxinator.models.ShipmentStatusHistory;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuditingEntityListener {

    @PrePersist
    public void setCreatedAt(ShipmentStatusHistory entity) {
        entity.setCreatedAt(new Date());
    }


    }
