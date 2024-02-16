package com.mortage.demo.Exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNumberFormatException(Exception ex) {
        return ResponseEntity.badRequest().body("Please enter valid positive numbers");
    }

    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        return ResponseEntity.badRequest().body("Invalid value. Please provide a positive number");
    }
}
