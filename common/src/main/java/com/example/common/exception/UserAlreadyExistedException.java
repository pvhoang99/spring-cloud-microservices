package com.example.common.exception;

import jakarta.persistence.EntityExistsException;

public class UserAlreadyExistedException extends EntityExistsException {

  public UserAlreadyExistedException(String username) {
    super("User exists with: " + username);
  }

}
