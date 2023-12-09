package com.hexaware.cinemax.services;

import com.hexaware.cinemax.dto.UserDTO;

public interface IUserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(int userId);

	UserDTO authenticateUser(String username, String password);

    // Add more methods as needed for updating, deleting, or retrieving users
}
