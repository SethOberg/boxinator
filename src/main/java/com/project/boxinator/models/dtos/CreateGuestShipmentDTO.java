package com.project.boxinator.models.dtos;

public class CreateGuestShipmentDTO {

    private String receiverName;
    private String weightOption;
    private String boxColor;
    private String destinationCountry;

    private GuestUserDto guestUserDto;
    private Integer price;

    public CreateGuestShipmentDTO(String receiverName, String weightOption, String boxColor, String destinationCountry, GuestUserDto guestUserDto, Integer price) {
        this.receiverName = receiverName;
        this.weightOption = weightOption;
        this.boxColor = boxColor;
        this.destinationCountry = destinationCountry;
        this.guestUserDto = guestUserDto;
        this.price = price;
    }

    public CreateGuestShipmentDTO() {
    }

    public String getReceiverName() {
        return receiverName;
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

    public GuestUserDto getGuestUserDto() {
        return guestUserDto;
    }

    public void setGuestUserDto(GuestUserDto guestUserDto) {
        this.guestUserDto = guestUserDto;
    }
}
