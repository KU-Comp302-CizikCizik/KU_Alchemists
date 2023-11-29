package com.KUAlchemists.ui;

import com.KUAlchemists.ui.controllers.IngredientCardController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class testUI extends Application {


    public static void main(String[] args) {
        Application.launch(testUI.class);
    }

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("IngredientCardUI.fxml"));
        fxmlLoader.setRoot(new BorderPane());
        Parent parent = fxmlLoader.load();

        // Get the controller from the FXMLLoader
        IngredientCardController controller = fxmlLoader.getController();
        // Set the properties

        // Initialize the stage
        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(parent, 320, 240));
        primaryStage.show();
    }
}
