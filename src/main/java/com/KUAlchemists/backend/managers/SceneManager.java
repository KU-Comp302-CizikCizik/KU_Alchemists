package com.KUAlchemists.backend.managers;

import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.utils.GameConstants;
import com.KUAlchemists.backend.utils.Loader;
import com.KUAlchemists.ui.MainApplicationUI;
import com.KUAlchemists.ui.controllers.LoginController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SceneManager {

    // Singleton
    private static SceneManager Instance;


    /**
     * @return the instance
     */
    public static SceneManager getInstance() {
        if(Instance == null) {
            Instance = new SceneManager();
        }
        return Instance;
    }
    private SceneManager() {

    }

    public void changeScene(Gamestate to) {
        switch (to) {
            case LOGIN:
                loadLogin();
                break;
            case MENU:
                loadMenu();
                break;
            default:
                break;
        }

    }

    private void loadMenu() {
        MainApplicationUI.root = Loader.loadFXML(GameConstants.MAINGAME_UI_FXML);
        Scene oldScene = MainApplicationUI.stage.getScene();
        MainApplicationUI.scene = new Scene(MainApplicationUI.root, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        MainApplicationUI.stage = (Stage) oldScene.getWindow();
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.show();
    }

    private void loadLogin() {
        MainApplicationUI.root = Loader.loadFXML(GameConstants.LOGINPAGE_UI_FXML);
        MainApplicationUI.scene = new Scene(MainApplicationUI.root, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);

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

        // Get the controller from the FXMLLoader
        LoginController controller = (LoginController) Loader.getFXMLController(GameConstants.LOGINPAGE_UI_FXML);
        // Set the properties
        controller.setProperties(prop);
        MainApplicationUI.stage.setTitle(GameConstants.GAME_TITLE);
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.show();

    }


}
