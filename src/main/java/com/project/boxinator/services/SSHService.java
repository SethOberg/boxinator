package com.project.boxinator.services;

import com.project.boxinator.models.ShipmentStatusHistory;
import com.project.boxinator.repositories.SSHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SSHService {

    @Autowired
    private SSHRepository SSHRepository;

    public List<ShipmentStatusHistory> getAllSSH() { return SSHRepository.findAll(); }

    public void addSSHItem (ShipmentStatusHistory shipmentStatusHistory) {
        SSHRepository.save(shipmentStatusHistory);

    }




}
