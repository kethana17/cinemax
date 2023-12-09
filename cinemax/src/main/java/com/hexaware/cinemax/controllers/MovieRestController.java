package com.hexaware.cinemax.controllers;

import com.hexaware.cinemax.dto.MovieDTO;
import com.hexaware.cinemax.exceptions.MovieNotFoundException;
import com.hexaware.cinemax.services.IMovieService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {
	
    private static final Logger logger = LoggerFactory.getLogger(MovieRestController.class);

    @Autowired
    private IMovieService movieService;

    // Add a new movie
    @PostMapping("/addMovie")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDTO) {
    	logger.info("Adding a new movie: {}", movieDTO.getTitle());
        MovieDTO createdMovie = movieService.addMovie(movieDTO);
        logger.info("Movie added successfully");
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    // Get all movies
    @GetMapping("/getAllMovies")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        logger.info("Retrieving all movies");
        List<MovieDTO> movies;
        try {
            movies = movieService.getAllMovies();
            logger.info("Retrieved {} movies", movies.size());
        } catch (MovieNotFoundException e) {
            logger.error("No movies found");
            throw e;
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    // Remove a movie by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMovie(@PathVariable int id) {
        logger.info("Removing movie with ID: {}", id);
        try {
            movieService.removeMovie(id);
            logger.info("Movie removed successfully");
        } catch (MovieNotFoundException e) {
            logger.error("Movie not found for ID: {}", id);
            throw e;
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Remove a movie by name
    @DeleteMapping("/removeByName/{name}")
    public ResponseEntity<Void> removeMovieByName(@PathVariable String name) {
        logger.info("Removing movie with name: {}", name);
        try {
            movieService.removeMovieByName(name);
            logger.info("Movie removed successfully");
        } catch (MovieNotFoundException e) {
            logger.error("Movie not found for name: {}", name);
            throw e;
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Get a movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable int id) {
        logger.info("Retrieving movie with ID: {}", id);
        try {
            MovieDTO movie = movieService.getMovieById(id);
            logger.info("Retrieved movie with ID: {}", id);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            logger.error("Movie not found for ID: {}", id);
            throw e;
        }
    }

    // Get a movie by title
    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<MovieDTO> getMovieByTitle(@PathVariable String title) {
        logger.info("Retrieving movie with title: {}", title);
        try {
            MovieDTO movie = movieService.getMovieByTitle(title);
            logger.info("Retrieved movie with title: {}", title);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            logger.error("Movie not found for title: {}", title);
            throw e;
        }
    }
}
