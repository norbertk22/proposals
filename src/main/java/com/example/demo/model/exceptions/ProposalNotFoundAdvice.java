package com.example.demo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ProposalNotFoundAdvice {

    @ExceptionHandler(ProposalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String proposalNotFoundException(ProposalNotFoundException ex) {
        return ex.getMessage();
    }
}
