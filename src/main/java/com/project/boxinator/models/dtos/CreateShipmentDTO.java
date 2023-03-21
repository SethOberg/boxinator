package com.project.boxinator.models.dtos;

import com.project.boxinator.enums.WeightOption;

import java.util.UUID;

public class CreateShipmentDTO {

    private String receiverName;
    private String weightOption;
    private String boxColor;
    private String destinationCountry;
    private Integer price;
    private String userId;

    public CreateShipmentDTO(String receiverName, String weightOption, String boxColor, String destinationCountry, Integer price, String userId) {
        this.receiverName = receiverName;
        this.weightOption = weightOption;
        this.boxColor = boxColor;
        this.destinationCountry = destinationCountry;
        this.price = price;
        this.userId = userId;
    }

    public CreateShipmentDTO() {
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getWeightOption() {
        return weightOption;
    }

    public void setWeightOption(String weightOption) {
        this.weightOption = weightOption;
    }

    public String getBoxColor() {
        return boxColor;
    }

    public void setBoxColor(String boxColor) {
        this.boxColor = boxColor;
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


}
