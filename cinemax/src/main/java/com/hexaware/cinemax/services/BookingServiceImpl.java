// BookingServiceImpl.java (Service Implementation)
package com.hexaware.cinemax.services;

import com.hexaware.cinemax.dto.BookingDTO;
import com.hexaware.cinemax.entities.Booking;
import com.hexaware.cinemax.entities.Show;
import com.hexaware.cinemax.entities.User;  // Import User entity
import com.hexaware.cinemax.repositories.BookingRepository;
import com.hexaware.cinemax.repositories.ShowRepository;
import com.hexaware.cinemax.repositories.UserRepository;  // Import UserRepository

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;  // Autowire UserRepository

    @Override
    public void bookSeat(BookingDTO bookingDTO) {
        // Fetch the Show entity by ID from the repository
        Show show = showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        // Fetch the User entity by ID from the repository
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the seats are available (you can implement your own logic here)

        // Convert the list of seat numbers to a comma-separated string
        String seatNumbers = String.join(",", bookingDTO.getSeatNumbers());

        // Create a new Booking entity with the comma-separated string of seat numbers
        Booking booking = new Booking();
        booking.setShow(show);
        booking.setUser(user);
        booking.setSeatNumbers(seatNumbers);

        // Save the booking entity to the database
        bookingRepository.save(booking);
    }
    @Override
    public List<String> getSeatNumbersByShowId(int showId) {
        List<String> bookings = bookingRepository.findSeatNumbersByShowId(showId);
        return bookings.stream()
                .flatMap((String numbers) -> Arrays.stream(numbers.split(",")))
                .collect(Collectors.toList());
    }
    @Override
    public List<String> getSeatNumbersByUserId(int userId) {
        // Fetch all bookings for the user from the repository
        List<String> userBookings = bookingRepository.findSeatNumbersByUserId(userId);

        // Convert the list of seat numbers to a flat list
        return userBookings.stream()
                .flatMap((String numbers) -> Arrays.stream(numbers.split(",")))
                .collect(Collectors.toList());
    }





    // Implement other methods for updating and retrieving bookings
}
