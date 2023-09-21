package com.example.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
 * Custom exception class for handling cases where information is not found.
 * This exception is  thrown when attempting to retrieve or operate on non-existing information.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {

    /*
     * Constructor for InformationNotFoundException.
     *
     * @param message A message describing the specific reason for the exception
     */

    public InformationNotFoundException(String message) { //constructor tha accepts message
        super(message); //means this runtimeexception
    }
}