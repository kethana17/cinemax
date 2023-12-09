package com.hexaware.cinemax.services;

import com.hexaware.cinemax.dto.UserDTO;
import com.hexaware.cinemax.entities.User;
import com.hexaware.cinemax.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Arrange
        UserDTO userDTO = new UserDTO("john", "john@example.com", "password");
        User savedUser = new User("john", "john@example.com", "password");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserDTO result = userService.createUser(userDTO);

        // Assert
        assertEquals("john", result.getUsername());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("password", result.getPassword());
    }

    @Test
    void testGetUserById() {
        // Arrange
        int userId = 1;
        User user = new User("john", "john@example.com", "password");
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        // Act
        UserDTO result = userService.getUserById(userId);

        // Assert
        assertEquals("john", result.getUsername());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("password", result.getPassword());
    }

    @Test
    void testAuthenticateUser() {
        // Arrange
        String username = "john";
        String password = "password";
        User user = new User("john", "john@example.com", "password");
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(user);

        // Act
        UserDTO result = userService.authenticateUser(username, password);

        // Assert
        assertEquals("john", result.getUsername());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("password", result.getPassword());
    }
}
