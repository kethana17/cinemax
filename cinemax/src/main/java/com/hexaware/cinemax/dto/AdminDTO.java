package com.hexaware.cinemax.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminDTO {

	@NotBlank(message = "Admin name is required")
	@Size(min = 2, max = 50, message = "Admin name must be between 2 and 50 characters")
    private String adminName;
	@NotBlank(message = "Admin Username is required")
    private String adminUsername;
	@NotBlank(message = "Admin password is required")
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$")
    private String adminPassword;

    public AdminDTO() {
        // Default constructor
    }

    public AdminDTO(String adminName, String adminUsername, String adminPassword) {
        this.adminName = adminName;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    // Remove the getAdminId and setAdminId methods

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @Override
    public String toString() {
        return "AdminDTO [adminName=" + adminName +
                ", adminUsername=" + adminUsername + ", adminPassword=" + adminPassword + "]";
    }
}
