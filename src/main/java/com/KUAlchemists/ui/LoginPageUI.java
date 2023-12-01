package com.KUAlchemists.ui;

import com.KUAlchemists.ui.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class LoginPageUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load properties from config.properties
        Properties prop = new Properties();
        InputStream inputStream = null;
        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LoginPageUI.fxml"));
        fxmlLoader.setRoot(new BorderPane());
        Parent parent = fxmlLoader.load();

        // Get the controller from the FXMLLoader
        LoginController controller = fxmlLoader.getController();
        // Set the properties
        controller.setProperties(prop);

        // Initialize the stage
        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(parent, 600, 400));
        primaryStage.show();
    }
}
