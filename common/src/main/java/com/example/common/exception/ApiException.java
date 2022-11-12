package com.example.common.exception;


import com.example.common.api.IError;

public class ApiException extends RuntimeException {

  private IError errorCode;

  public ApiException(IError errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ApiException(String message) {
    super(message);
  }

  public ApiException(Throwable cause) {
    super(cause);
  }

  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public IError getErrorCode() {
    return errorCode;
  }
}
