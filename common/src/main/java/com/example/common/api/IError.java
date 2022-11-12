package com.example.common.api;

public interface IError {

  long getCode();

  String getMessage();

  static IError of(long code, String message) {

    return new IError() {
      @Override
      public long getCode() {
        return code;
      }

      @Override
      public String getMessage() {
        return message;
      }
    };
  }

}
