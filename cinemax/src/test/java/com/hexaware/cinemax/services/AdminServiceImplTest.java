package com.hexaware.cinemax.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.cinemax.dto.AdminDTO;
import com.hexaware.cinemax.entities.Admin;
import com.hexaware.cinemax.repositories.AdminRepository;

@SpringBootTest
class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    void testAddAdmin() {
        // Mocking data
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdminName("John Doe");
        adminDTO.setAdminUsername("john.doe");
        adminDTO.setAdminPassword("password123");

        Admin existingAdmin = new Admin();
        existingAdmin.setAdminUsername("john.doe");

        // Mocking behavior
        when(adminRepository.findByAdminUsername("john.doe")).thenReturn(existingAdmin);

        // Test
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            adminService.addAdmin(adminDTO);
        });

        assertEquals("Admin with the given username already exists", exception.getMessage());

        // Verify that save method is not called
        verify(adminRepository, times(0)).save(any(Admin.class));
    }

    @Test
    void testVerifyAdminCredentials() {
        // Mocking data
        String username = "john.doe";
        String password = "password123";

        Admin admin = new Admin();
        admin.setAdminUsername("john.doe");
        admin.setAdminPassword("password123");

        // Mocking behavior
        when(adminRepository.findByAdminUsername("john.doe")).thenReturn(admin);

        // Test
        boolean result = adminService.verifyAdminCredentials(username, password);

        assertTrue(result);

        // Verify that findByAdminUsername method is called once
        verify(adminRepository, times(1)).findByAdminUsername("john.doe");
    }
}