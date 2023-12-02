package com.KUAlchemists.backend.utils;

import com.KUAlchemists.ui.controllers.Controller;
import com.KUAlchemists.ui.controllers.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Loader {

    public static Parent loadFXML(String fxml_path) {
        FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getClassLoader().getResource(fxml_path));
        fxmlLoader.setRoot(new BorderPane());
        try {
            Parent parent = fxmlLoader.load();
            return parent;
        } catch (IOException e) {
            throw new RuntimeException("loadFML issue " + fxml_path + "\n" + e);
        }
    }

    public static Controller getFXMLController(String fxml_path) {
        FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getClassLoader().getResource(fxml_path));
        fxmlLoader.setRoot(new BorderPane());
        try {
            Parent parent = fxmlLoader.load();
            return fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException("getFXMLController issue " + fxml_path + "\n" + e);
        }

    }
}
