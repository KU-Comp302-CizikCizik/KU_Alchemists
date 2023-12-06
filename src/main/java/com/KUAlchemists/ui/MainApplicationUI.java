package com.KUAlchemists.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApplicationUI extends Application {

    public static Scene scene = null;
    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        SceneLoader.getInstance().loadLogin();

    }
}
