package com.example.book.service;

import com.example.book.exception.InformationExistException;
import com.example.book.model.User;
import com.example.book.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//user registration
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User userObject) {
        // Check if a user with the same email address already exists
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            // Encode the user's password for security
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            // Save the user to the database
            return userRepository.save(userObject);
        } else {
            // Throw an exception if the email address is already in use
            throw new InformationExistException("user with email address " + userObject.getEmailAddress() + " already exists");
        }
    }
}

