package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.services.LoginService;

import java.util.Properties;

/**
 * This class is responsible for handling login requests.
 */
public class LoginHandler {

    private final LoginService loginService;
    public LoginHandler(Properties properties) {
        loginService = new LoginService(properties);
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
            GameEngine.getInstance().updateGameState(Gamestate.MENU);
            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }
}
