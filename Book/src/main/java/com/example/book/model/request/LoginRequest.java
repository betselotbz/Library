package com.example.book.model.request;

public class LoginRequest {
    private String emailAddress;
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
/*It contains two private fields, emailAddress and password, along with corresponding getter methods to access these fields.
This class is typically used to encapsulate user-provided login information, such as email address and password, when making login requests to a server or application.*/