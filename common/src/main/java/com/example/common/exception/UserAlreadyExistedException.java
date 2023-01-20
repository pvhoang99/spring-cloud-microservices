package com.example.common.exception;

import javax.persistence.EntityExistsException;

public class UserAlreadyExistedException extends EntityExistsException {

    public UserAlreadyExistedException(String username) {
        super("User exists with: " + username);
    }

}
