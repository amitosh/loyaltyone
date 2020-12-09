package com.loyaltyone.assignment.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<RestError> handleGenericException(Exception e) {
        RestError restError = new RestError();
        restError.setStatus(HttpStatus.BAD_REQUEST);
        restError.setMessage(e.getMessage());
        return new ResponseEntity<>(restError, restError.getStatus());
    }
}
