package com.project.boxinator.controllers;


import com.project.boxinator.enums.TypeOfUser;
import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.User;
import com.project.boxinator.models.dtos.CreateUserDTO;
import com.project.boxinator.services.ShipmentService;
import com.project.boxinator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
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
    public ResponseEntity getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody User user) {
        if(userId != user.getId())
            ResponseEntity.badRequest().build();
        userService.update(user);
    }

   @GetMapping("{userId}/shipments")
   public List<Shipment> getAllShipmentsByUser(@PathVariable String userId) {
        List<Shipment> shipments = userService.getUserById(userId).getShipments()
                .stream()
                //.map metod för att göra om till DTO
                .collect(Collectors.toList());

        return shipments;
   }

    @PutMapping("{userId}/shipments/{shipmentId}")
    public ResponseEntity addShipmentToUser(@PathVariable String userId,
                                                    @PathVariable Integer shipmentId) {
        User user = userService.getUserById(userId);
        Shipment shipment = shipmentService.getShipmentById(shipmentId);
        user.addShipmentToUser(shipment);
        shipment.addUserToShipment(user);
        userService.update(user);
        shipmentService.update(shipment);

        return ResponseEntity.ok("User added to shipment");
    }


    @GetMapping("info")
    public ResponseEntity getLoggedInUserInfo(@AuthenticationPrincipal Jwt principal) {
        Map<String, String> map = new HashMap<>();
        map.put("subject", principal.getClaimAsString("sub"));
        map.put("user_name", principal.getClaimAsString("preferred_username"));
        map.put("email", principal.getClaimAsString("email"));
        map.put("first_name", principal.getClaimAsString("given_name"));
        map.put("last_name", principal.getClaimAsString("family_name"));
        map.put("roles", String.valueOf(principal.getClaimAsStringList("roles")));
        return ResponseEntity.ok(map);
    }

    @PostMapping("registerGuest")
    public ResponseEntity addNewGuestUserFromJwt(@AuthenticationPrincipal Jwt jwt) {
        String primaryKey = jwt.getClaimAsString("sub");

        if(userService.userExists(primaryKey)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        } else {
            User user = new User(primaryKey, jwt.getClaimAsString("email"), TypeOfUser.Guest);
            return ResponseEntity.ok(userService.addUser(user));
        }
    }

    @PostMapping("registerRegularUser")
    public ResponseEntity addNewUserFromJwtAndDto(@AuthenticationPrincipal Jwt jwt, @RequestBody CreateUserDTO userDTO) {
        String primaryKey = jwt.getClaimAsString("sub");
        System.out.println("Hello");
        if(userService.userExists(primaryKey)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        } else {
            User user = new User(primaryKey, userDTO);
            return ResponseEntity.ok(userService.addUser(user));
        }

    }

}
