package com.example.common.api;

public enum ResultCode implements IErrorCode {

  SUCCESS(200, "SUCCESS"),
  FAILED(500, "FAILED"),
  VALIDATE_FAILED(404, "VALIDATE_FAILED"),
  UNAUTHORIZED(401, "401"),
  FORBIDDEN(403, "403");

  private final long code;
  private final String message;

  ResultCode(long code, String message) {
    this.code = code;
    this.message = message;
  }

  public long getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
