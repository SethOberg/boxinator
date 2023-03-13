package com.project.boxinator.controllers;


import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.User;
import com.project.boxinator.services.ShipmentService;
import com.project.boxinator.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShipmentService shipmentService;

    @PostMapping
    public void addUser (@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public ResponseEntity getAllUsers() {return ResponseEntity.ok(userService.getAllUsers());}

    @GetMapping("{userId}")
    public ResponseEntity getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("{userId}")
    public void updateUser(@PathVariable Integer userId, @RequestBody User user) {
        if(userId != user.getUserId())
            ResponseEntity.badRequest().build();
        userService.update(user);
    }

    @PutMapping("{userId}/shipments/{shipmentId}")
    public ResponseEntity addShipmentToUser(@PathVariable Integer userId,
                                            @PathVariable Integer shipmentId) {
        User user = userService.getUserById(userId);
        Shipment shipment = shipmentService.getShipmentById(shipmentId);
        user.addShipmentToUser(shipment);
        userService.update(user);
        return ResponseEntity.ok("Shipment added to user");

    }




    }


















