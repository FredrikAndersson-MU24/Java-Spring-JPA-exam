package com.example.javaspringjpaexam.exceptionhandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidArguments(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations()
                .forEach(e -> errors.put(e.getPropertyPath().toString(), e.getMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateKeyException.class)
    public String handleDuplicateKey(DuplicateKeyException e) {
        return e.getMessage();
    }

}
