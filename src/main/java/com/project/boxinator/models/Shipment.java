package com.project.boxinator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.boxinator.enums.WeightOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(length = 150, nullable = false)
    private String recieverName;
    @Column(nullable = false)
    private WeightOption weightOption;

    @Column(length = 50, nullable = false)
    private String boxColour;

    @ManyToOne
    @JoinColumn(name = "destination_country_id")
    private Country destinationCountry;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipment")
    private Set<ShipmentStatusHistory> shipmentHistory = new HashSet<>();

    @Column(length = 10)
    private Integer price;

    public Shipment(int id, String recieverName, WeightOption weightOption, String boxColour, Country destinationCountry, Set<ShipmentStatusHistory> shipmentHistory) {
        Id = id;
        this.recieverName = recieverName;
        this.weightOption = weightOption;
        this.boxColour = boxColour;
        this.destinationCountry = destinationCountry;
        this.shipmentHistory = shipmentHistory;
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

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public WeightOption getWeightOption() {
        return weightOption;
    }

    public void setWeightOption(WeightOption weightOption) {
        this.weightOption = weightOption;
    }

    public String getBoxColour() {
        return boxColour;
    }

    public void setBoxColour(String boxColour) {
        this.boxColour = boxColour;
    }

    public Country getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(Country destinationCountry) {
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
