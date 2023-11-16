package com.KUAlchemists.backend.controllers;

import com.KUAlchemists.backend.services.LoginService;

import java.util.Properties;

public class LoginController {

    private LoginService loginService;

    public LoginController(Properties properties) {
        this.loginService = new LoginService(properties);
    }

    public String login(String username, String password) {
        if (loginService.validateUser(username, password)) {
            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }
}

