package com.KUAlchemists.backend.utils;

import com.KUAlchemists.ui.controllers.Controller;
import com.KUAlchemists.ui.controllers.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

public class Loader {

    private static FXMLLoader loader;

    public static Parent loadFXML(String fxml_path) {
        loader.setLocation(Loader.class.getClassLoader().getResource(fxml_path));
        try {
            return FXMLLoader.load(Loader.class.getClassLoader().getResource(fxml_path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Parent loadFXMLFirstTime(String fxml_path){
        if(loader == null) {
            loader = new FXMLLoader();
            loader.setRoot(new BorderPane());
        }
        loader.setLocation(Loader.class.getClassLoader().getResource(fxml_path));
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
