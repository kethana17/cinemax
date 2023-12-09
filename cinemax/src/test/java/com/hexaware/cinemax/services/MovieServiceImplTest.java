package com.hexaware.cinemax.services;

import com.hexaware.cinemax.dto.MovieDTO;
import com.hexaware.cinemax.entities.Movie;
import com.hexaware.cinemax.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMovie() {
        // Arrange
        MovieDTO movieDTO = new MovieDTO("Inception", "Sci-Fi", "Christopher Nolan", 148, 4.5);
        Movie movieEntity = new Movie("Inception", "Sci-Fi", "Christopher Nolan", 148, 4.5);
        when(movieRepository.save(any(Movie.class))).thenReturn(movieEntity);

        // Act
        MovieDTO result = movieService.addMovie(movieDTO);

        // Assert
        assertNotNull(result);
        assertEquals(movieDTO.getTitle(), result.getTitle());
        assertEquals(movieDTO.getGenre(), result.getGenre());
        assertEquals(movieDTO.getDirector(), result.getDirector());
        assertEquals(movieDTO.getDuration(), result.getDuration());
        assertEquals(movieDTO.getRating(), result.getRating());
        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    @Test
    void testGetAllMovies() {
        // Arrange
        List<Movie> movieEntities = Arrays.asList(
                new Movie("Inception", "Sci-Fi", "Christopher Nolan", 148, 4.5),
                new Movie("Titanic", "Romance", "James Cameron", 195, 4.0)
        );
        when(movieRepository.findAll()).thenReturn(movieEntities);

        // Act
        List<MovieDTO> result = movieService.getAllMovies();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void testRemoveMovie() {
        // Arrange
        int movieId = 1;

        // Act
        movieService.removeMovie(movieId);

        // Assert
        verify(movieRepository, times(1)).deleteById(movieId);
    }

    @Test
    void testRemoveMovieByName() {
        // Arrange
        String movieName = "Inception";
        Movie movieEntity = new Movie("Inception", "Sci-Fi", "Christopher Nolan", 148, 4.5);
        when(movieRepository.findByTitle(movieName)).thenReturn(movieEntity);

        // Act
        movieService.removeMovieByName(movieName);

        // Assert
        verify(movieRepository, times(1)).delete(movieEntity);
    }
}
