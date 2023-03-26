package com.project.boxinator.services;

import com.project.boxinator.enums.TypeOfUser;
import com.project.boxinator.exceptions.UserNotFoundException;
import com.project.boxinator.models.Country;
import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.ShipmentStatusHistory;
import com.project.boxinator.models.User;
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
        System.out.println("This is the user that was passed through the parameters (line 42): "+user);

        //retrieve all guest user data
        User guestUser = userRepository.findByEmail(user.getEmail()); //Guest user

        if (guestUser != null){
        System.out.println("This is the Guest user from the database (line 48): "+guestUser);
        Set<Shipment> shipmentSet = shipmentRepository.findAllByUserId(guestUser.getId());

        //insert all the necessary data into the newly registered user
        User guestToRegisteredUser = new User();
        guestToRegisteredUser.setId(guestUser.getId());
        guestToRegisteredUser.setTypeOfUser(TypeOfUser.Registered);
        guestToRegisteredUser.setCountry(user.getCountry());
        guestToRegisteredUser.setEmail(user.getEmail());
        guestToRegisteredUser.setFirstName(user.getFirstName());
        guestToRegisteredUser.setLastName(user.getLastName());
        guestToRegisteredUser.setShipments(moveShipmentsFromGuestToRegisteredUser(shipmentSet, user));
        
        try{
            userRepository.delete(guestUser);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

            return userRepository.save(guestToRegisteredUser);
        }

            return userRepository.save(user);
    }
    private Set<Shipment> moveShipmentsFromGuestToRegisteredUser(Set<Shipment> set, User user){
        Set<Shipment> shipmentSet = new HashSet<>();
        for (Shipment shipment: set) {
            Shipment shipment1 = new Shipment(shipment.getReceiverName(), shipment.getWeightOption(),
                    shipment.getBoxColour(), shipment.getDestinationCountry(), shipment.getShipmentHistory());
            shipment1.setUser(user);
            shipment1.setShipmentHistory(moveSSHFromGuestToRegisteredUsersShipments(shipment.getShipmentHistory(), shipment1));
            shipmentSet.add(shipment1);
        }
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
