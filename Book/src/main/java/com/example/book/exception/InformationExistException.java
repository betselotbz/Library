package com.example.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException {
    public InformationExistException(String message)
    {
      super (message);
    }
}
//handling cases where Information already exists
//So, when an InformationExistException is thrown in your code, it will result in an HTTP response with a status code of 409 Conflict.
