package com.project.boxinator.unitTesting;

import com.project.boxinator.enums.TypeOfUser;
import com.project.boxinator.exceptions.UserNotFoundException;
import com.project.boxinator.models.User;
import com.project.boxinator.repositories.ShipmentRepository;
import com.project.boxinator.repositories.UserRepository;
import com.project.boxinator.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock private UserRepository userRepository;
    @Mock private ShipmentRepository shipmentRepository;
    private UserService userService;



    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, shipmentRepository);
    }


    @Test
    void getAllUsers() {
        ArrayList<User> expectedListOfUsers = new ArrayList<>();
        expectedListOfUsers.add(new User("1", "test1@gmail.com", TypeOfUser.Registered));
        expectedListOfUsers.add(new User("2", "test2@gmail.com", TypeOfUser.Registered));
        expectedListOfUsers.add(new User("3", "test3@gmail.com", TypeOfUser.Administrator));

        when(userRepository.findAll()).thenReturn(expectedListOfUsers);
        ArrayList<User> actualListOfUsers = (ArrayList<User>) userService.getAllUsers();

        assertEquals(expectedListOfUsers, actualListOfUsers);
    }

    @Test
    void getUserByIdWhenUserExists() {
        User expectedUser = new User("1", "test@gmail.com", TypeOfUser.Registered);
        when(userRepository.findById("1")).thenReturn(Optional.of(expectedUser));
        User actualUser = userService.getUserById("1");

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getUserByIdWhenUserDoesntExists() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById("2"));
    }

    @Test
    void checkIfUserExists() {
        when(userRepository.existsById("1")).thenReturn(true);
        boolean actualValue = userService.userExists("1");

        assertTrue(actualValue);
    }

    @Test
    void checkWhenUserDoesntExists() {
        when(userRepository.existsById("2")).thenReturn(false);
        boolean actualValue = userService.userExists("2");

        assertFalse(actualValue);
    }

    @Test
    void addUser() {
        User expectedUser = new User("1", "test@gmail.com", TypeOfUser.Registered);
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);
        User actualUser = userService.addUser(expectedUser);

        assertEquals(expectedUser, actualUser);
    }


    @Test
    void updatesUser() {
        User initialUser = new User("1", "test@gmail.com", TypeOfUser.Registered);
        when(userRepository.findById("1")).thenReturn(Optional.of(initialUser));

        User updatedUser = initialUser;
        updatedUser.setEmail("changed@gmail.com");
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        User actualUser = userService.update(updatedUser);


        assertEquals(updatedUser, actualUser);
    }
}