package com.example.common.exception;

import com.example.common.api.CommonResult;
import jakarta.persistence.EntityExistsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public CommonResult<?> globalExceptionHandling(RuntimeException exception) {
    return CommonResult.failed(exception.getMessage());
  }

  @ExceptionHandler(EntityExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public CommonResult<?> entityExistExceptionHandling(EntityExistsException exception) {
    return CommonResult.failed(exception.getMessage());
  }

  @ExceptionHandler(value = ApiException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public CommonResult<?> handle(ApiException e) {
    if (e.getErrorCode() != null) {
      return CommonResult.failed(e.getErrorCode());
    }
    return CommonResult.failed(e.getMessage());
  }

}
