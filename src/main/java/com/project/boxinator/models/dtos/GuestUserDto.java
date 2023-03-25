package com.project.boxinator.models.dtos;

import com.project.boxinator.enums.TypeOfUser;

public class GuestUserDto {
    private String email;
    private String typeOfUser;

    public GuestUserDto(String email, String typeOfUser) {
        this.email = email;
        this.typeOfUser = typeOfUser;
    }

    public GuestUserDto() {
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }
}
