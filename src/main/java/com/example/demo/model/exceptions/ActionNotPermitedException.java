package com.example.demo.model.exceptions;

public class ActionNotPermitedException extends RuntimeException{

    public ActionNotPermitedException(String action) {
        super("Action '" + action + "' not permited");
    }
}
