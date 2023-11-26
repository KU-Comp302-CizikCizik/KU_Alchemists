package com.KUAlchemists.backend.controllers;

import com.KUAlchemists.backend.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.Properties;

/**
 * This class is responsible for handling login requests.
 */
public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label loginMessageLabel;

    public void loginButtonOnAction(ActionEvent event) {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();

        if (username != null && password != null) {
            //String loginResult = login(username, password);
            //loginMessageLabel.setText(loginResult);
            if (username.equals("admin") && password.equals("admin")) {
                loginMessageLabel.setText("Login successful!");
            } else {
                loginMessageLabel.setText("Invalid username or password.");
            }
        } else {
            loginMessageLabel.setText("Please enter username and password.");
        }


    }

    private void validateUser() {

    }

    /**
     * The LoginService that this controller uses to validate users.
     */
    private LoginService loginService;

    /**
     * Constructor for LoginController.
     *
     */
    public LoginController() {

    }

    /**
     * This method is called by the JavaFX framework when the controller is initialized.
     * @param properties
     */
    public void setProperties(Properties properties) {
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

