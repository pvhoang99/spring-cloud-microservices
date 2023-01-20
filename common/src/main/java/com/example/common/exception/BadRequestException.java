package com.example.common.exception;

public class BadRequestException extends DomainException {

    public BadRequestException(String message, Object... args) {
        super(message, args);
    }

}
