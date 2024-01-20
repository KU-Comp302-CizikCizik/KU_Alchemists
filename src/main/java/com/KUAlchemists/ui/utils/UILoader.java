package com.KUAlchemists.ui.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class UILoader {
    private static FXMLLoader loader;

    /**
     * This method is used to load the fxml file.
     * @param fxml_path The path to the fxml file.
     * @return The root node of the fxml file.
     */
    public static Parent loadFXML(String fxml_path) {
        loader.setLocation(UILoader.class.getClassLoader().getResource(fxml_path));
        try {
            return FXMLLoader.load(UILoader.class.getClassLoader().getResource(fxml_path));
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
        loader.setRoot(new AnchorPane());

        loader.setLocation(UILoader.class.getClassLoader().getResource(fxml_path));
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static FXMLLoader getLoader() {
        return loader;
    }
}
