package com.KUAlchemists.ui;

import com.KUAlchemists.ui.controllers.InventoryStorageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class IngredientStorageUI extends Application {
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("IngeridentStorageUI.fxml"));
        Parent parent = fxmlLoader.load();

        // Get the controller from the FXMLLoader
        InventoryStorageController controller = fxmlLoader.getController();
        // Set the properties

        // Initialize the stage
        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(parent, 700,500));


        primaryStage.show();
    }

}
