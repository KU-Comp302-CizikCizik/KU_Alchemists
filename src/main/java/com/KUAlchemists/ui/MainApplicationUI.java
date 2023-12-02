package com.KUAlchemists.ui;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.managers.SceneManager;
import com.KUAlchemists.backend.utils.GameConstants;
import com.KUAlchemists.backend.utils.Loader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApplicationUI extends Application {

    public static Scene scene = null;
    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        GameEngine.getInstance().updateGameState(Gamestate.LOGIN);
    }
}
