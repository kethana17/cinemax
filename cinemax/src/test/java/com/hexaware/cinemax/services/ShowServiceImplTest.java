package com.hexaware.cinemax.services;

import com.hexaware.cinemax.dto.ShowDTO;
import com.hexaware.cinemax.entities.Show;
import com.hexaware.cinemax.entities.Movie;
import com.hexaware.cinemax.repositories.ShowRepository;
import com.hexaware.cinemax.repositories.TheatreRepository;
import com.hexaware.cinemax.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShowServiceImplTest {

    @Mock
    private ShowRepository showRepository;

    @Mock
    private TheatreRepository theatreRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private ShowServiceImpl showService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddShow() {
        // Sample data
        ShowDTO showDTO = new ShowDTO();
        showDTO.setShowName("Sample Show");
        showDTO.setShowDateTime(LocalDateTime.now());
        showDTO.setTheatreName("Sample Theatre");
        showDTO.setMovieName("Sample Movie");

        Show expectedShow = new Show();
        expectedShow.setShowName(showDTO.getShowName());
        expectedShow.setShowDateTime(showDTO.getShowDateTime());

        // Mocking repository methods
        when(theatreRepository.findByName("Sample Theatre")).thenReturn(null);
        when(movieRepository.findByTitle("Sample Movie")).thenReturn(null);
        when(showRepository.save(any(Show.class))).thenReturn(expectedShow);

        // Perform the service method
        ShowDTO result = showService.addShow(showDTO);

        // Verify repository method invocations
        verify(theatreRepository, times(1)).findByName("Sample Theatre");
        verify(movieRepository, times(1)).findByTitle("Sample Movie");
        verify(showRepository, times(1)).save(any(Show.class));

        // Assertions
        assertEquals(showDTO.getShowName(), result.getShowName());
        assertEquals(showDTO.getShowDateTime(), result.getShowDateTime());
        // Add more assertions based on your DTO and entity mapping
    }

    @Test
    void testGetAllShows() {
        // Sample data
        Show show1 = new Show();
        show1.setId(1);
        show1.setShowName("Show 1");
        Show show2 = new Show();
        show2.setId(2);
        show2.setShowName("Show 2");

        // Mocking repository method
        when(showRepository.findAll()).thenReturn(Arrays.asList(show1, show2));

        // Perform the service method
        List<ShowDTO> result = showService.getAllShows();

        // Verify repository method invocation
        verify(showRepository, times(1)).findAll();

        // Assertions
        assertEquals(2, result.size());
        // Add more assertions based on your DTO and entity mapping
    }

    @Test
    void testRemoveShowById() {
        // Sample data
        int showIdToRemove = 1;

        // Perform the service method
        showService.removeShowById(showIdToRemove);

        // Verify repository method invocation
        verify(showRepository, times(1)).deleteById(showIdToRemove);
    }

    @Test
    void testGetAllShowsByMovieName() {
        // Sample data
        String movieName = "Sample Movie";
        Show show1 = new Show();
        show1.setId(1);
        show1.setShowName("Show 1");
        show1.setMovie(new Movie());
        show1.getMovie().setTitle(movieName);

        // Mocking repository method
        when(showRepository.findByMovie_Title(movieName)).thenReturn(Arrays.asList(show1));

        // Perform the service method
        List<ShowDTO> result = showService.getAllShowsByMovieName(movieName);

        // Verify repository method invocation
        verify(showRepository, times(1)).findByMovie_Title(movieName);

        // Assertions
        assertEquals(1, result.size());
        // Add more assertions based on your DTO and entity mapping
    }

    // Add similar tests for other methods (removeShowById, getAllShowsByMovieName, etc.)
}
