package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.repositories.UserRepository;

import java.util.Properties;

public class LoginService {

    private UserRepository userRepository;

    public LoginService(Properties properties) {
        this.userRepository = new UserRepository(properties);
    }

    public boolean validateUser(String username, String password) {
        String storedPassword = userRepository.getUserPassword(username);
        // Here you would compare the hashed password, the following is a placeholder
        return storedPassword != null && storedPassword.equals(password);
    }

    // Package-private setter for testing purposes
    void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
