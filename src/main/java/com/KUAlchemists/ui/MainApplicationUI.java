package com.KUAlchemists.ui;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Gamestate;
import javafx.application.Application;
import javafx.scene.Scene;
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
