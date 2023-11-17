package com.KUAlchemists.backend.controllers;

import com.KUAlchemists.backend.services.LoginService;

import java.util.Properties;

/**
 * This class is responsible for handling login requests.
 */
public class LoginController {

    /**
     * The LoginService that this controller uses to validate users.
     */
    private final LoginService loginService;

    /**
     * Constructor for LoginController.
     * @param properties The properties that the LoginService needs.
     */
    public LoginController(Properties properties) {
        this.loginService = new LoginService(properties);
    }

    /**
     * This method is called when a user attempts to log in.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A message indicating whether the login was successful or not.
     * @see LoginService#validateUser(String, String)
     */
    public String login(String username, String password) {
        if (loginService.validateUser(username, password)) {
            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }
}

