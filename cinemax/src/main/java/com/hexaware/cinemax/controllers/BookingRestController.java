// BookingController.java
package com.hexaware.cinemax.controllers;

import com.hexaware.cinemax.dto.BookingDTO;
import com.hexaware.cinemax.exceptions.BookingNotFoundException;
import com.hexaware.cinemax.services.IBookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingRestController.class);

    @Autowired
    private IBookingService bookingService;

    @PostMapping("/book-seat")
    public ResponseEntity<String> bookSeat(@RequestBody BookingDTO bookingDTO) {
    	logger.info("Seat booked successfully for showId: {} and userId: {}", bookingDTO.getShowId(), bookingDTO.getUserId());
        bookingService.bookSeat(bookingDTO);
        return ResponseEntity.ok("Seat booked successfully");
    }

    @GetMapping("/seat-numbers/{showId}")
    public ResponseEntity<List<String>> getSeatNumbersByShowId(@PathVariable int showId) {
        List<String> seatNumbers;
        try {
            seatNumbers = bookingService.getSeatNumbersByShowId(showId);
            logger.info("Retrieved seat numbers for showId {}: {}", showId, seatNumbers);
        } catch (BookingNotFoundException e) {
            logger.error("Booking not found for showId: {}", showId);
            throw e;
        }
        return ResponseEntity.ok(seatNumbers);
    }

    @GetMapping("/user-seat-numbers/{userId}")
    public ResponseEntity<List<String>> getSeatNumbersByUserId(@PathVariable int userId) {
        List<String> seatNumbers;
        try {
            seatNumbers = bookingService.getSeatNumbersByUserId(userId);
            logger.info("Retrieved seat numbers for userId {}: {}", userId, seatNumbers);
        } catch (BookingNotFoundException e) {
            logger.error("Booking not found for userId: {}", userId);
            throw e;
        }
        return ResponseEntity.ok(seatNumbers);
    }
}