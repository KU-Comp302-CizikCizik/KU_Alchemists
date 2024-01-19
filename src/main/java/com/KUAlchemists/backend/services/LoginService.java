package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.repositories.UserRepository;

import java.util.Properties;

/**
 * This class is responsible for validating users.
 */
public class LoginService {

    private UserRepository userRepository;

    /**
     * Constructor for LoginService.
     * @param properties The properties that the LoginService needs.
     */
    public LoginService(Properties properties) {
        this.userRepository = new UserRepository(properties);
    }

    /**
     * This method is called when a user attempts to log in.
     * @param username  The username of the user.
     * @param password  The password of the user.
     * @return A message indicating whether the login was successful or not.
     * @see UserRepository#getUserPassword(String)
     */
    public boolean validateUser(String username, String password) {
        String storedPassword = userRepository.getUserPassword(username);
        // Here you would compare the hashed password, the following is a placeholder
        return storedPassword != null && storedPassword.equals(password);
//        return true;
    }

    /**
     * This method is used for setter injection in tests.
     * @param userRepository The UserRepository to inject.
     */
    void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
