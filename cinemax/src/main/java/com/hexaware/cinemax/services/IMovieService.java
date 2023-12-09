package com.hexaware.cinemax.services;

import com.hexaware.cinemax.dto.MovieDTO;

import java.util.List;

public interface IMovieService {

    // Add a new movie
    MovieDTO addMovie(MovieDTO movieDTO);

    // Get all movies
    List<MovieDTO> getAllMovies();

    // Remove a movie by ID
    void removeMovie(int id);
    // Remove a movie by Name

    void removeMovieByName(String name);

}
