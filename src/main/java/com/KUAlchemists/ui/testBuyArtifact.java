package com.KUAlchemists.ui;

import com.KUAlchemists.ui.controllers.BuyArtifactController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class testBuyArtifact extends Application {


    public static void main(String[] args) {
        Application.launch(BuyArtifactUI.class);
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("BuyArtifactUI.fxml"));
        VBox cardBox = fxmlLoader.load();
        BuyArtifactController controller = fxmlLoader.getController();
//        controller.setIngredientCard("mushroom-ingredient.jpg");

        fxmlLoader.setRoot(new BorderPane());

        // Initialize the stage
        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(cardBox, 320, 240));
        primaryStage.show();
    }
}