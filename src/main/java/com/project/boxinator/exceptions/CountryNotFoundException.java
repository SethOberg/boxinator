package com.project.boxinator.exceptions;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(int id) {
        super("Country does not exist with ID: " + id);
    }


}
