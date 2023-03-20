package com.project.boxinator.controllers;

import com.project.boxinator.models.ShipmentStatusHistory;
import com.project.boxinator.services.SSHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/SSH")
@CrossOrigin(origins = "http://localhost:3000")
public class SSHController {

    @Autowired
    private SSHService sshService;


    @GetMapping
    public ResponseEntity getAllSSH() {return ResponseEntity.ok(sshService.getAllSSH());}









}
