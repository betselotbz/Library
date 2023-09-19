package com.example.book.exception;

public class InformationExistException extends RuntimeException {
    public InformationExistException(String message)
    {
      super (message)
    }
}
//handling cases where Information already exists