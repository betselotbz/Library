package com.example.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//Create InformationNotFoundException class extends RuntimeException
//Return a not found HTTP status code in ResponseStatus
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {
    public InformationNotFoundException(String message) { //constructor tha accepts message
        super(message); //means this runtimeexception
    }
}