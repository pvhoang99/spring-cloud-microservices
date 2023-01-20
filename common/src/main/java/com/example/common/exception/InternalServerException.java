package com.example.common.exception;

import lombok.Getter;

@Getter
public class InternalServerException extends RuntimeException {

    protected String message;

    protected Object[] args;

    public InternalServerException(String message, Object... args) {
        this.message = message;
        this.args = args;
    }

}
