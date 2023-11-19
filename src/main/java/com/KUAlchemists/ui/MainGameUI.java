package com.KUAlchemists.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGameUI extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainGameUI.fxml"));
        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(root, 320, 240));
        primaryStage.show();
    }
}
