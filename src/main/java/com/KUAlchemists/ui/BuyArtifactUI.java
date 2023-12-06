package com.KUAlchemists.ui;

import com.KUAlchemists.ui.controllers.BuyArtifactController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class BuyArtifactUI extends Application {
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("BuyArtifactUI.fxml"));
        Parent parent = fxmlLoader.load();

        // Get the controller from the FXMLLoader
        BuyArtifactController controller = fxmlLoader.getController();
        // Set the properties

        // Initialize the stage
        int height = 650;
        int width = 900;
        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(parent, width,height));
        primaryStage.setMaxHeight(height);
        primaryStage.setMinHeight(height);
        primaryStage.setMaxWidth(height);
        primaryStage.setMinWidth(width);



        primaryStage.show();
    }

}