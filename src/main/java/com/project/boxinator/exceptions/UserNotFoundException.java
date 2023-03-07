package com.project.boxinator.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) { super("User does not exist with ID: " + id);}
}
