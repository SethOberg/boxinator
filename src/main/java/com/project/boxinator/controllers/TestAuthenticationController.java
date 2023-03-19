package com.project.boxinator.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/resources")
@CrossOrigin(origins = "http://localhost:3000")
public class TestAuthenticationController {


    @GetMapping("public")
    public ResponseEntity getPublic() {
        return ResponseEntity.ok("Public resources");
    }

    @GetMapping("protected")
    public ResponseEntity getProtected() {
        return ResponseEntity.ok("Protected resources");
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAdminResources() {
        return ResponseEntity.ok("Admin resources");
    }

    @GetMapping("user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getUserResources() {
        return ResponseEntity.ok("User resources");
    }

    @GetMapping("guest")
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity getGuestResources() {
        return ResponseEntity.ok("Guest resources");
    }

}