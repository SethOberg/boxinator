package com.project.boxinator.models;

import com.project.boxinator.enums.WeightOption;
import com.project.boxinator.models.dtos.CreateGuestShipmentDTO;
import com.project.boxinator.models.dtos.CreateShipmentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(length = 150, nullable = false)
    private String receiverName;
    @Column(nullable = false)
    private WeightOption weightOption;

    @Column(length = 50, nullable = false)
    private String boxColor;

    @Column(length = 50, nullable = false)
    private String destinationCountry;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipment")
    private Set<ShipmentStatusHistory> shipmentHistory = new HashSet<>();

    @Column(length = 10)
    private Integer price;

    public Shipment(String receiverName, WeightOption weightOption, String boxColor, String destinationCountry, Set<ShipmentStatusHistory> shipmentHistory) {
        this.receiverName = receiverName;
        this.weightOption = weightOption;
        this.boxColor = boxColor;
        this.destinationCountry = destinationCountry;
        this.shipmentHistory = shipmentHistory;
    }


    public Shipment(CreateGuestShipmentDTO createGuestShipmentDTO) {
        this.receiverName = createGuestShipmentDTO.getReceiverName();
        this.weightOption = WeightOption.valueOf(createGuestShipmentDTO.getWeightOption());
        this.boxColor = createGuestShipmentDTO.getBoxColor();
        this.destinationCountry = createGuestShipmentDTO.getDestinationCountry();
        this.price = createGuestShipmentDTO.getPrice();

    }


    public Shipment(CreateShipmentDTO createShipmentDTO) {
        this.receiverName = createShipmentDTO.getReceiverName();
        this.weightOption = WeightOption.valueOf(createShipmentDTO.getWeightOption());
        this.boxColor = createShipmentDTO.getBoxColor();
        this.destinationCountry = createShipmentDTO.getDestinationCountry();

    }

    public void addUserToShipment(User user) {
        setUser(user);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String recieverName) {
        this.receiverName = recieverName;
    }

    public WeightOption getWeightOption() {
        return weightOption;
    }

    public void setWeightOption(WeightOption weightOption) {
        this.weightOption = weightOption;
    }

    public String getBoxColour() {
        return boxColor;
    }

    public void setBoxColour(String boxColour) {
        this.boxColor = boxColour;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ShipmentStatusHistory> getShipmentHistory() {
        return shipmentHistory;
    }

    public void setShipmentHistory(Set<ShipmentStatusHistory> shipmentHistory) {
        this.shipmentHistory = shipmentHistory;
    }

    public void addSSHToShipment(ShipmentStatusHistory shipmentStatusHistory) {
        shipmentHistory.add(shipmentStatusHistory);
    }


}
