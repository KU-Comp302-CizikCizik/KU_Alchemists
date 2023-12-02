package com.KUAlchemists.ui;

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

    public static Parent root;
    public static Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        root = Loader.loadFXML(GameConstants.LOGINPAGE_UI_FXML);
        scene = new Scene(root, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        stage.setTitle("KU Alchemists");
        stage.setScene(scene);
        stage.show();
    }
}
