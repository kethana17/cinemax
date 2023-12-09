// BookingDTO.java (DTO)
package com.hexaware.cinemax.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookingDTO {

	@NotNull(message = "Show ID is required")
	@Min(value = 1, message = "Show ID must be greater than or equal to 1")
    private int showId;
	@NotNull(message = "User ID is required")
    @Min(value = 1, message = "User ID must be greater than or equal to 1")
    private int userId;
	@NotEmpty(message = "Seat numbers are required")
    @Size(min = 1, message = "At least one seat number must be provided")
    private List<String> seatNumbers;  // Represents multiple seat numbers for a booking

    public BookingDTO() {
        // Default constructor
    }

    public BookingDTO(int showId, int userId, List<String> seatNumbers) {
        this.showId = showId;
        this.userId = userId;
        this.seatNumbers = seatNumbers;
    }

    // Getters and setters for all fields

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }
}
