package com.project.boxinator.services;

import com.project.boxinator.enums.TypeOfUser;
import com.project.boxinator.exceptions.UserNotFoundException;
import com.project.boxinator.models.Country;
import com.project.boxinator.models.User;
import com.project.boxinator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserById(String uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }

    public boolean userExists(String uuid) {
        return userRepository.existsById(uuid);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }


    public User update(User user) {
        getUserById(user.getId());
        return userRepository.save(user);
    }
}
