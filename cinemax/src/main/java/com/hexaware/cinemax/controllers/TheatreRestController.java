package com.hexaware.cinemax.controllers;

import com.hexaware.cinemax.dto.TheatreDTO;
import com.hexaware.cinemax.exceptions.TheatreNotFoundException;
import com.hexaware.cinemax.services.ITheatreService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TheatreRestController.class);

    @Autowired
    private ITheatreService theatreService;

    // Add a new theatre
    @PostMapping("/addTheatre")
    public ResponseEntity<TheatreDTO> addTheatre(@RequestBody TheatreDTO theatreDTO) {
    	logger.info("Adding a new theatre: {}", theatreDTO.getName());
        TheatreDTO createdTheatre = theatreService.addTheatre(theatreDTO);
        logger.info("Theatre added successfully");
        return new ResponseEntity<>(createdTheatre, HttpStatus.CREATED);
    }

    // Get all theatres
    @GetMapping("/getAllTheatres")
    public ResponseEntity<List<TheatreDTO>> getAllTheatres() {
    	logger.info("Retrieving all theatres");
        List<TheatreDTO> theatres = theatreService.getAllTheatres();
        logger.info("Retrieved {} theatres", theatres.size());
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    }

    // Remove a theatre by ID
    @DeleteMapping("/removeTheatre/{id}")
    public ResponseEntity<Void> removeTheatre(@PathVariable int id) {
        logger.info("Removing theatre with ID: {}", id);
        try {
            theatreService.removeTheatreById(id);
            logger.info("Theatre removed successfully");
        } catch (TheatreNotFoundException e) {
            logger.error("Theatre not found for ID: {}", id);
            throw e;
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Remove a theatre by name
    @DeleteMapping("/removeByName/{name}")
    public ResponseEntity<Void> removeTheatreByName(@PathVariable String name) {
        logger.info("Removing theatre by name: {}", name);
        try {
            theatreService.removeTheatreByName(name);
            logger.info("Theatre removed successfully");
        } catch (TheatreNotFoundException e) {
            logger.error("Theatre not found for name: {}", name);
            throw e;
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Get a theatre by ID
    @GetMapping("/{id}")
    public ResponseEntity<TheatreDTO> getTheatreById(@PathVariable int id) {
        logger.info("Retrieving theatre with ID: {}", id);
        try {
            TheatreDTO theatre = theatreService.getTheatreById(id);
            logger.info("Retrieved theatre with ID: {}", id);
            return new ResponseEntity<>(theatre, HttpStatus.OK);
        } catch (TheatreNotFoundException e) {
            logger.error("Theatre not found for ID: {}", id);
            throw e;
        }
    }

    // Get a theatre by name
    @GetMapping("/getByName/{name}")
    public ResponseEntity<TheatreDTO> getTheatreByName(@PathVariable String name) {
        logger.info("Retrieving theatre with name: {}", name);
        try {
            TheatreDTO theatre = theatreService.getTheatreByName(name);
            logger.info("Retrieved theatre with name: {}", name);
            return new ResponseEntity<>(theatre, HttpStatus.OK);
        } catch (TheatreNotFoundException e) {
            logger.error("Theatre not found for name: {}", name);
            throw e;
        }
    }
}
