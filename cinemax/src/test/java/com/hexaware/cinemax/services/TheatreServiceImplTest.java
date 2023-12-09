package com.hexaware.cinemax.services;

import com.hexaware.cinemax.dto.TheatreDTO;
import com.hexaware.cinemax.entities.Theatre;
import com.hexaware.cinemax.repositories.TheatreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TheatreServiceImplTest {

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private TheatreServiceImpl theatreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTheatre() {
        // Arrange
        TheatreDTO theatreDTO = new TheatreDTO("Sample Theatre", "Sample Location", 10, 15);
        Theatre savedTheatre = new Theatre(1,"Sample Theatre", "Sample Location", 10, 15);
        when(theatreRepository.save(any(Theatre.class))).thenReturn(savedTheatre);

        // Act
        TheatreDTO result = theatreService.addTheatre(theatreDTO);

        // Assert
        assertEquals("Sample Theatre", result.getName());
        assertEquals("Sample Location", result.getLocation());
        assertEquals(10, result.getNumberOfRows());
        assertEquals(15, result.getNumberOfColumns());
        verify(theatreRepository, times(1)).save(any(Theatre.class));
    }

    @Test
    void testGetAllTheatres() {
        // Arrange
        Theatre theatre1 = new Theatre(1,"Theatre1", "Location1", 8, 12);
        Theatre theatre2 = new Theatre(2,"Theatre2", "Location2", 10, 15);
        when(theatreRepository.findAll()).thenReturn(Arrays.asList(theatre1, theatre2));

        // Act
        List<TheatreDTO> result = theatreService.getAllTheatres();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Theatre1", result.get(0).getName());
        assertEquals("Location1", result.get(0).getLocation());
        assertEquals(8, result.get(0).getNumberOfRows());
        assertEquals(12, result.get(0).getNumberOfColumns());

        assertEquals("Theatre2", result.get(1).getName());
        assertEquals("Location2", result.get(1).getLocation());
        assertEquals(10, result.get(1).getNumberOfRows());
        assertEquals(15, result.get(1).getNumberOfColumns());
        verify(theatreRepository, times(1)).findAll();
    }

    @Test
    void testRemoveTheatreById() {
        // Arrange
        int theatreId = 1;

        // Act
        theatreService.removeTheatreById(theatreId);

        // Assert
        verify(theatreRepository, times(1)).deleteById(theatreId);
    }

    @Test
    void testRemoveTheatreByName() {
        // Arrange
        String theatreName = "Sample Theatre";
        Theatre theatreEntity = new Theatre(1,"Sample Theatre", "Sample Location", 10, 15);
        when(theatreRepository.findByName(theatreName)).thenReturn(theatreEntity);

        // Act
        theatreService.removeTheatreByName(theatreName);

        // Assert
        verify(theatreRepository, times(1)).findByName(theatreName);
        verify(theatreRepository, times(1)).delete(theatreEntity);
    }
}
