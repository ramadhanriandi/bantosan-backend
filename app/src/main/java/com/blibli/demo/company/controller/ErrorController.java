package com.blibli.demo.company.controller;

import com.blibli.oss.command.exception.CommandValidationException;
import com.blibli.oss.common.error.ErrorWebFluxControllerHandler;
import com.blibli.oss.common.error.Errors;
import com.blibli.oss.common.response.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorController implements ErrorWebFluxControllerHandler, MessageSourceAware {

  @Getter
  @Setter
  private MessageSource messageSource;

  @Override
  public Logger getLogger() {
    return log;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(CommandValidationException.class)
  public Response<Object> CommandValidationException(CommandValidationException e) {
    Response<Object> response = new Response();
    response.setCode(HttpStatus.BAD_REQUEST.value());
    response.setStatus(HttpStatus.BAD_REQUEST.name());
    response.setErrors(Errors.from(e.getConstraintViolations()));
    return response;
  }
}
