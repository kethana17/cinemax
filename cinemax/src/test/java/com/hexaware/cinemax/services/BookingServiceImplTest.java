package com.hexaware.cinemax.services;

import com.hexaware.cinemax.dto.BookingDTO;
import com.hexaware.cinemax.entities.Booking;
import com.hexaware.cinemax.entities.Show;
import com.hexaware.cinemax.entities.User;
import com.hexaware.cinemax.repositories.BookingRepository;
import com.hexaware.cinemax.repositories.ShowRepository;
import com.hexaware.cinemax.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    public BookingServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookSeat() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setShowId(1);
        bookingDTO.setUserId(1);
        bookingDTO.setSeatNumbers(Arrays.asList("A1", "A2", "A3"));

        Show mockShow = new Show();
        User mockUser = new User();

        // Mock the behavior of showRepository.findById
        Mockito.when(showRepository.findById(1)).thenReturn(Optional.of(mockShow));

        // Mock the behavior of userRepository.findById
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

        // Mock the behavior of bookingRepository.save
        Mockito.when(bookingRepository.save(any())).thenReturn(new Booking());

        bookingService.bookSeat(bookingDTO);

        // Add assertions or verifications based on your requirements
        // For example, you can verify that the save method was called:
        Mockito.verify(bookingRepository, Mockito.times(1)).save(any());
    }

    // Other test methods...


    @Test
    void testGetSeatNumbersByShowId() {
        int showId = 1;
        List<String> mockBookings = Arrays.asList("A1,A2,A3", "B1,B2,B3");
        Mockito.when(bookingRepository.findSeatNumbersByShowId(showId)).thenReturn(mockBookings);

        List<String> result = bookingService.getSeatNumbersByShowId(showId);

        // Add assertions based on your requirements
        assertEquals(Arrays.asList("A1", "A2", "A3", "B1", "B2", "B3"), result);
    }

    @Test
    void testGetSeatNumbersByUserId() {
        int userId = 1;
        List<String> mockUserBookings = Arrays.asList("A1,A2,A3", "B1,B2,B3");
        Mockito.when(bookingRepository.findSeatNumbersByUserId(userId)).thenReturn(mockUserBookings);

        List<String> result = bookingService.getSeatNumbersByUserId(userId);

        // Add assertions based on your requirements
        assertEquals(Arrays.asList("A1", "A2", "A3", "B1", "B2", "B3"), result);
    }
}
