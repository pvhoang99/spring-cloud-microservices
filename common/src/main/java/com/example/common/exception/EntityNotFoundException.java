package com.example.common.exception;

public class EntityNotFoundException extends DomainException {

  public EntityNotFoundException(String message, Object... args) {
    super(message, args);
  }

}
