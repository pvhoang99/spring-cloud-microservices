package com.example.common.api;

import lombok.Getter;

@Getter
public class CommonResult<T> {

  private long code;

  private String message;

  private T data;

  protected CommonResult() {
  }

  protected CommonResult(long code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  protected CommonResult(long code, String message) {
    this.code = code;
    this.message = message;
  }

  public static <T> CommonResult<T> success() {
    return new CommonResult<T>(ResponseMsg.SUCCESS.getCode(), ResponseMsg.SUCCESS.getMessage());
  }

  public static <T> CommonResult<T> success(T data) {
    return new CommonResult<T>(ResponseMsg.SUCCESS.getCode(), ResponseMsg.SUCCESS.getMessage(), data);
  }

  public static <T> CommonResult<T> success(T data, String message) {
    return new CommonResult<T>(ResponseMsg.SUCCESS.getCode(), message, data);
  }

  public static <T> CommonResult<T> failed(IError errorCode) {
    return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
  }

  public static <T> CommonResult<T> failed(IError errorCode, String message) {
    return new CommonResult<T>(errorCode.getCode(), message, null);
  }

  public static <T> CommonResult<T> failed(String message) {
    return new CommonResult<T>(ResponseMsg.FAILED.getCode(), message, null);
  }

  public static <T> CommonResult<T> failed() {
    return failed(ResponseMsg.FAILED);
  }

  public static <T> CommonResult<T> validateFailed() {
    return failed(ResponseMsg.VALIDATE_FAILED);
  }

  public static <T> CommonResult<T> validateFailed(String message) {
    return new CommonResult<T>(ResponseMsg.VALIDATE_FAILED.getCode(), message, null);
  }

  public static <T> CommonResult<T> unauthorized(T data) {
    return new CommonResult<T>(ResponseMsg.UNAUTHORIZED.getCode(), ResponseMsg.UNAUTHORIZED.getMessage(), data);
  }

  public static <T> CommonResult<T> forbidden(T data) {
    return new CommonResult<T>(ResponseMsg.FORBIDDEN.getCode(), ResponseMsg.FORBIDDEN.getMessage(), data);
  }

}
