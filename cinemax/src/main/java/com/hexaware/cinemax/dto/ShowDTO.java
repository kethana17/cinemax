package com.hexaware.cinemax.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ShowDTO {

    private int id;
    @NotBlank(message = "Show name is required")
    private String showName;
    @NotNull(message = "Show date and time are required")
    private LocalDateTime showDateTime;
    @NotBlank(message = "Theatre name is required")
    private String theatreName;
    @NotBlank(message = "Movie name is required")
    private String movieName;

    public ShowDTO() {
        // Default constructor
    }

    public ShowDTO(int id, String showName, LocalDateTime showDateTime, String theatreName, String movieName) {
        this.id = id;
        this.showName = showName;
        this.showDateTime = showDateTime;
        this.theatreName = theatreName;
        this.movieName = movieName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
