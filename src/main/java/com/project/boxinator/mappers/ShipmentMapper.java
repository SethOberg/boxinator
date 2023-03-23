package com.project.boxinator.mappers;

import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.dtos.ShipmentDto;
import com.project.boxinator.services.ShipmentService;
import com.project.boxinator.services.UserService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ShipmentMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private ShipmentService shipmentService;

    public ShipmentDto convertToShipmentDTO(Shipment shipment) {
        ShipmentDto shipmentDTO = new ShipmentDto();
        shipmentDTO.setFirstName(shipment.getUser().getFirstName());
        shipmentDTO.setLastName(shipment.getUser().getLastName());
        return shipmentDTO;
    }




}
