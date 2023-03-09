package com.project.boxinator.models.dtos;

import com.project.boxinator.enums.WeightOption;
import com.project.boxinator.models.ShipmentStatusHistory;
import com.project.boxinator.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ShipmentDto {
    private int Id;
    private String recieverName;
    private WeightOption weightOption;
    private String boxColour;
    private String destinationCountry;
    private Set<ShipmentStatusHistory> shipmentHistory = new HashSet<>();

}
