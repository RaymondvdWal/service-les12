package com.example.controllerles10.exceptions;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
