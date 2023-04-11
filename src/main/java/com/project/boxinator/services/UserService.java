package com.project.boxinator.services;

import com.project.boxinator.enums.TypeOfUser;
import com.project.boxinator.exceptions.UserNotFoundException;
import com.project.boxinator.models.Country;
import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.ShipmentStatusHistory;
import com.project.boxinator.models.User;
import com.project.boxinator.repositories.SSHRepository;
import com.project.boxinator.repositories.ShipmentRepository;
import com.project.boxinator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private  UserRepository userRepository;
    private  ShipmentRepository shipmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, ShipmentRepository shipmentRepository) {
        this.userRepository = userRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserById(String uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }

    public boolean userExists(String uuid) {
        return userRepository.existsById(uuid);
    }

    public User addUser(User user) {
        User newUser = copyGuestToUserAndDelete(user);
        return userRepository.save(newUser);
    }

    public User copyGuestToUserAndDelete(User user){
        User guestUser = userRepository.findByEmail(user.getEmail());
        if (guestUser != null){
        Set<Shipment> shipmentSet = shipmentRepository.findAllByUserId(guestUser.getId());
        User guestToRegisteredUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateOfBirth(),
                user.getCountry(), user.getZipCode(), user.getContactNumber(), TypeOfUser.Registered);
        User savedUser = userRepository.save(guestToRegisteredUser);
        savedUser.setShipments(moveShipmentsFromGuestToRegisteredUser(shipmentSet, user));
        userRepository.save(savedUser);

        try{
           userRepository.delete(guestUser);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
            return savedUser;
        }

            return user;
    }
    private Set<Shipment> moveShipmentsFromGuestToRegisteredUser(Set<Shipment> set, User user){
        Set<Shipment> shipmentSet = new HashSet<>();
        for (Shipment shipment: set) {
            Shipment shipment1 = new Shipment(shipment.getReceiverName(), shipment.getWeightOption(),
                    shipment.getBoxColour(), shipment.getDestinationCountry(), shipment.getShipmentHistory());
            shipment1.setUser(user);
            shipment1.setPrice(shipment.getPrice());
            shipment1.setShipmentHistory(moveSSHFromGuestToRegisteredUsersShipments(shipment.getShipmentHistory(), shipment1));
            shipmentSet.add(shipment1);
        }
       shipmentRepository.saveAll(shipmentSet);
        return shipmentSet;
    }

    private Set<ShipmentStatusHistory> moveSSHFromGuestToRegisteredUsersShipments(Set<ShipmentStatusHistory> set, Shipment shipment){
        Set<ShipmentStatusHistory> shipmentSSHSet = new HashSet<>();
        for (ShipmentStatusHistory shipmentSSH: set) {
            shipmentSSHSet.add(new ShipmentStatusHistory(shipmentSSH.getShipmentStatus(), shipment));
        }
        return shipmentSSHSet;
    }

    public User update(User user) {
        getUserById(user.getId());
        return userRepository.save(user);
    }
}
