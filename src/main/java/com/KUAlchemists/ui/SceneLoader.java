package com.KUAlchemists.ui;

import com.KUAlchemists.ui.controllers.LoginController;
import com.KUAlchemists.ui.utils.UIConstants;
import com.KUAlchemists.ui.utils.UILoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SceneLoader {

    private Parent root;

    public void loadMenu() {
        root = UILoader.loadFXML(UIConstants.MENU_UI_FXML);
        Scene oldScene = MainApplicationUI.stage.getScene();
        MainApplicationUI.scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        MainApplicationUI.stage = (Stage) oldScene.getWindow();
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.show();
    }

    public void loadLogin() {
        root = UILoader.loadFXMLFirstTime(UIConstants.LOGINPAGE_UI_FXML);
        MainApplicationUI.scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);

        // Load properties from config.properties
        Properties prop = new Properties();
        InputStream inputStream = null;
        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        // Set the properties
        LoginController.setProperties(prop);
        MainApplicationUI.stage.setTitle(UIConstants.GAME_TITLE);
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.show();

    }
}