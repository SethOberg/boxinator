package com.project.boxinator.controllers;


import com.project.boxinator.models.User;
import com.project.boxinator.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getAllUsers() {return ResponseEntity.ok(userService.getAllUsers());}

    @GetMapping("{userId}")
    public ResponseEntity getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping
    public void addUser (@RequestBody User user) {
        userService.addUser(user);

    }







}
