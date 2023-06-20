package com.example.common.exception;

import lombok.Getter;

@Getter
public abstract class DomainException extends RuntimeException {

  protected String message;

  protected Object[] args;

  public DomainException(String message, Object... args) {
    this.message = message;
    this.args = args;
  }

}
