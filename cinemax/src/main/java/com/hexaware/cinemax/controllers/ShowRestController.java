package com.hexaware.cinemax.controllers;

import com.hexaware.cinemax.dto.ShowDTO;
import com.hexaware.cinemax.exceptions.ShowNotFoundException;
import com.hexaware.cinemax.services.ShowServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowRestController {
	
   private static final Logger logger = LoggerFactory.getLogger(ShowRestController.class);

   @Autowired
   ShowServiceImpl showService;

    @PostMapping
    public ResponseEntity<ShowDTO> addShow(@RequestBody ShowDTO showDTO) {
    	logger.info("Adding a new show: {}", showDTO.getShowName());
        ShowDTO addedShow = showService.addShow(showDTO);
        logger.info("Show added successfully");
        return new ResponseEntity<>(addedShow, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ShowDTO>> getAllShows() {
    	logger.info("Retrieving all shows");
        List<ShowDTO> shows = showService.getAllShows();
        logger.info("Retrieved {} shows", shows.size());
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeShowById(@PathVariable int id) {
    	logger.info("Removing show with ID: {}", id);
        showService.removeShowById(id);
        logger.info("Show removed successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/byMovie/{movieName}")
    public ResponseEntity<List<ShowDTO>> getAllShowsByMovieName(@PathVariable String movieName) {
        logger.info("Retrieving shows by movie name: {}", movieName);
        List<ShowDTO> shows;
        try {
            shows = showService.getAllShowsByMovieName(movieName);
            logger.info("Retrieved {} shows for movie: {}", shows.size(), movieName);
        } catch (ShowNotFoundException e) {
            logger.error("No shows found for movie: {}", movieName);
            throw e;
        }
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    // Other methods for updating shows, retrieving shows by ID, etc.
}