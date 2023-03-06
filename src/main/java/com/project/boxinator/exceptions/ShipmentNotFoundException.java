package com.project.boxinator.exceptions;

public class ShipmentNotFoundException extends RuntimeException {
    public ShipmentNotFoundException(int id) {
        super("Shipment does not exist with ID: " + id);
    }

}
