package com.example.common.api;

public enum ResponseMsg implements IError {

  SUCCESS(200, "success"),
  FAILED(500, "failed"),
  VALIDATE_FAILED(404, "validate failed"),
  UNAUTHORIZED(401, "unauthorized"),
  FORBIDDEN(403, "forbidden");

  private final long code;
  private final String message;

  ResponseMsg(long code, String message) {
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
