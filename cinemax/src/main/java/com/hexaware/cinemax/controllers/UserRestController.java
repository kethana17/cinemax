// UserRestController.java (Controller)
package com.hexaware.cinemax.controllers;

import com.hexaware.cinemax.dto.UserDTO;
import com.hexaware.cinemax.exceptions.UserNotFoundException;
import com.hexaware.cinemax.services.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private IUserService userService;

    @GetMapping("/welcome")
    public String hello() {
    	logger.info("Endpoint accessed: /welcome");
    	return "welcome";
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    	try {
    	logger.info("Creating user with username: {}", userDTO.getUsername());
        UserDTO createdUser = userService.createUser(userDTO);
        logger.info("User created successfully");
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    	} catch(Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user", e);
    	}
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
    	try {
    	logger.info("Retrieving user with ID: {}", userId);
        UserDTO user = userService.getUserById(userId);
        logger.info("User retrieved successfully");
        return new ResponseEntity<>(user, HttpStatus.OK);
    	} catch (UserNotFoundException e) {
            logger.error("User with ID {} not found", userId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    	}
    }

    @GetMapping("/authenticate/{username}/{password}")
    public ResponseEntity<UserDTO> authenticateUser(
            @PathVariable String username,
            @PathVariable String password
    ) {
    	try {
    	logger.info("Authenticating user with username: {}", username);
        UserDTO authenticatedUser = userService.authenticateUser(username, password);
        logger.info("User authentication successful");
        return ResponseEntity.ok(authenticatedUser);
    }
    	 catch (Exception e) {
             logger.error("Authentication failed for user with username: {}", username);
             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed", e);
         }
    }
}
