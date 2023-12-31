package com.example.book.service;

import com.example.book.exception.InformationExistException;
import com.example.book.model.User;
import com.example.book.model.request.LoginRequest;
import com.example.book.repository.UserRepository;
import com.example.book.security.JWTUtils;
import com.example.book.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//user registration
@Service
public class UserService {

    // Dependency injection for UserRepository, PasswordEncoder, JWTUtils, and AuthenticationManager
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    // Constructor for UserService, injecting necessary dependencies
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    //Attempting to create a new user  by checking if user with same email address already exist in database
    /*
     * Create a new user.
     *
     * @param userObject The user object to be created
     * @return The created user
     * @throws InformationExistException if a user with the same email address already exists
     * @throws IllegalArgumentException if the userObject is null
     */
    public User createUser(User userObject) {
        if (userObject == null) {
            throw new IllegalArgumentException("User object cannot be null.");
        }
        // Check if a user with the same email address already exists
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            // Encode the user's password for security
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            // Save the user to the database
            return userRepository.save(userObject);
        } else {
            // Throw an exception if the email address is already in use
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() + " already exists");
        }
    }

    // Method to log in a user and return a JWT token
    /*
     * Log in a user and return a JWT token.
     *
     * @param loginRequest The login request containing the user's email address and password
     * @return Optional containing the JWT token if login is successful, empty otherwise
     */
    public Optional<String> loginUser(LoginRequest loginRequest) {
        // Create an authentication token with the provided email and password
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword());
        try {
            // Attempt authentication
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // Set the authenticated user in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Get user details from the authentication result
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            // Generate a JWT token for the user
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));
        } catch (Exception e) {
            // Return an empty optional if authentication fails
            return Optional.empty();
        }
    }
    /*
     * Find a user by their email address.
     *
     * @param emailAddress The email address of the user to find
     * @return The user with the specified email address, or null if not found
     */
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }
}

