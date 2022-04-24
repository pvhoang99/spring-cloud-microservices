package com.example.common.exception;

import com.example.common.config.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = ApiException.class)
  public CommonResult handle(ApiException e) {
    if (e.getErrorCode() != null) {
      return CommonResult.failed(e.getErrorCode());
    }
    return CommonResult.failed(e.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public CommonResult handleValidException(MethodArgumentNotValidException e) {
    return getCommonResult(e.getBindingResult(), e);
  }

  private CommonResult getCommonResult(BindingResult bindingResult2,
      Exception e) {
    BindingResult bindingResult = bindingResult2;
    String message = null;
    if (bindingResult.hasErrors()) {
      FieldError fieldError = bindingResult.getFieldError();
      if (fieldError != null) {
        message = fieldError.getField() + fieldError.getDefaultMessage();
      }
    }
    return CommonResult.validateFailed(message);
  }

  @ResponseBody
  @ExceptionHandler(value = BindException.class)
  public CommonResult<?> handleValidException(BindException e) {
    return getCommonResult(e.getBindingResult(), e);
  }
}
