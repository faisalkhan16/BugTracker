package com.codebyte.bugservice.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class BugExceptionHandler {

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<?> requestParametersValidation(RequestValidationException exception, WebRequest request) {

        CustomError errorDetails = new CustomError();
        errorDetails.setStatus("VALIDATION ERROR");
        errorDetails.setHttpStatus(HttpStatus.BAD_REQUEST.toString());
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceFallbackException.class)
    public ResponseEntity<?> serviceFallbackHandler(RequestValidationException exception, WebRequest request) {

        CustomError errorDetails = new CustomError();
        errorDetails.setStatus("SERVICE ERROR");
        errorDetails.setHttpStatus(HttpStatus.NOT_FOUND.toString());
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
}
