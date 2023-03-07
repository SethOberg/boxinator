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

    @PutMapping
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        if(id != user.getId())
            ResponseEntity.badRequest().build();
        userService.update(user);


    }












}
