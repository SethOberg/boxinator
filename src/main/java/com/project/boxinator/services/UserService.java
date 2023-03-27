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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SSHRepository sshRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

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


        //retrieve all guest user data
        User guestUser = userRepository.findByEmail(user.getEmail()); //Guest user

        if (guestUser != null){

        Set<Shipment> shipmentSet = shipmentRepository.findAllByUserId(guestUser.getId());

        //insert all the necessary data into the newly registered user
        User guestToRegisteredUser = new User();
        guestToRegisteredUser.setId(user.getId());
        guestToRegisteredUser.setTypeOfUser(TypeOfUser.Registered);
        guestToRegisteredUser.setCountry(user.getCountry());
        guestToRegisteredUser.setEmail(user.getEmail());
        guestToRegisteredUser.setFirstName(user.getFirstName());
        guestToRegisteredUser.setLastName(user.getLastName());
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

            //Shipment save = shipmentRepository.save(shipment1);
            shipment1.setShipmentHistory(moveSSHFromGuestToRegisteredUsersShipments(shipment.getShipmentHistory(), shipment1));
            //Shipment s = shipmentRepository.save(save);
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
       // System.out.println(sshRepository.saveAll(shipmentSSHSet));
        //Set<ShipmentStatusHistory> s = new HashSet<>(sshRepository.saveAll(shipmentSSHSet));
        //System.out.println(s);
        return shipmentSSHSet;
    }

    public User update(User user) {
        getUserById(user.getId());
        return userRepository.save(user);
    }
}
