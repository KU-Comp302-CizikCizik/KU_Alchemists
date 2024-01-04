package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.LoginHandler;
import com.KUAlchemists.ui.MainApplicationUI;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
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

    /**
     * The handler for login requests.
     * */


    /**
     * Constructor for LoginController.
     *
     */

    /**
     * This method is called when the login button is pressed.
     * @param event The event that triggered this method.
     */
    public void loginButtonOnAction(ActionEvent event) {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();
        Boolean loginResult = LoginHandler.getInstance().login(username, password);
        if (loginResult) {
            loginMessageLabel.setText("Login Successful! ");
            SceneLoader.getInstance().loadMenu();
        } else {
            SceneLoader.getInstance().loadGenericPopUp("Wrong username or password!");
        }
    }



    /**
     * This method is called by the JavaFX framework when the controller is initialized.
     * @param properties
     */
    public static void setProperties(Properties properties) {
        LoginHandler.getInstance().createService(properties);
    }

}

