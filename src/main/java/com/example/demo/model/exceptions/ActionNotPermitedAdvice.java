package com.example.demo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ActionNotPermitedAdvice {

    @ExceptionHandler(ActionNotPermitedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String actionNotPermitedException(ActionNotPermitedException ex) {
        return ex.getMessage();
    }
}
