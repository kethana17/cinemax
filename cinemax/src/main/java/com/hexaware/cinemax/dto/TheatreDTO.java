package com.hexaware.cinemax.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class TheatreDTO {

	@NotBlank(message = "Theatre name is required")
    private String name;
	@NotBlank(message = "Theatre location is required")
    private String location;
	@Positive(message = "Number of rows should be a positive integer")
    private int numberOfRows;
	@Positive(message = "Number of columns should be a positive integer")
    private int numberOfColumns;

    public TheatreDTO() {
        // Default constructor
    }

    public TheatreDTO(String name, String location, int numberOfRows, int numberOfColumns) {
        this.name = name;
        this.location = location;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    // Getters and setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }
}
