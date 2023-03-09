package com.project.boxinator.mappers;

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







}
