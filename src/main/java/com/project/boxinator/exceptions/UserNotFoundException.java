package com.project.boxinator.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String uuid) { super("User does not exist with uuid: " + uuid);}
}
