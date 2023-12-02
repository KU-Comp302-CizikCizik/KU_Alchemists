package com.KUAlchemists.backend.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Loader {

    private static FXMLLoader loader;

    /**
     * This method is used to load the fxml file.
     * @param fxml_path The path to the fxml file.
     * @return The root node of the fxml file.
     */
    public static Parent loadFXML(String fxml_path) {
        loader.setLocation(Loader.class.getClassLoader().getResource(fxml_path));
        try {
            return FXMLLoader.load(Loader.class.getClassLoader().getResource(fxml_path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to load the fxml file for the first time.
     * @param fxml_path The path to the fxml file.
     * @return The root node of the fxml file.
     */
    public static Parent loadFXMLFirstTime(String fxml_path){
        loader = new FXMLLoader();
        loader.setRoot(new BorderPane());

        loader.setLocation(Loader.class.getClassLoader().getResource(fxml_path));
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
