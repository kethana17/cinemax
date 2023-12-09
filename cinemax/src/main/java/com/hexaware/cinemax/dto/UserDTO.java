// UserDTO.java (DTO)
package com.hexaware.cinemax.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$",message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
    private String password;

    public UserDTO() {
        // Default constructor
    }

    public UserDTO(String username, String email,String password) {
        this.username = username;
        this.email = email;
        this.password=password;
    }

    // Getters and setters for all fields

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
