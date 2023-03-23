package com.project.boxinator.exceptions;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(int id) {
        super("Country does not exist with ID: " + id);
    }
    public CountryNotFoundException(String name) {
        super("Country does not exist with Name: " + name);
}

}
