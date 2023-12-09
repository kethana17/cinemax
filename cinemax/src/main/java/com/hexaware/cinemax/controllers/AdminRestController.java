package com.hexaware.cinemax.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hexaware.cinemax.dto.AdminDTO;
import com.hexaware.cinemax.exceptions.AdminNotFoundException;
import com.hexaware.cinemax.services.IAdminService;


@RestController
@RequestMapping("/api/admins")
public class AdminRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    private IAdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<String> addAdmin(@RequestBody AdminDTO adminDTO) {
        try {
            adminService.addAdmin(adminDTO);
            logger.info("Admin added successfully: {}", adminDTO.getAdminUsername());
            return new ResponseEntity<>("Admin added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
        	logger.error("Error adding admin: {}", e.getMessage());
            return new ResponseEntity<>("Error adding admin: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyAdminCredentials(
            @RequestParam String username,
            @RequestParam String password) {
        boolean isValidCredentials = adminService.verifyAdminCredentials(username, password);
        try {
        if (isValidCredentials) {
        	logger.info("Credentials are valid for username: {}", username);
            return new ResponseEntity<>("Credentials are valid", HttpStatus.OK);
        } else {
        	 logger.warn("Invalid credentials for username: {}", username);
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
        }catch (AdminNotFoundException e) {
            logger.error("Admin not found for username: {}", username);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found", e);
        }
    }

}
