package com.KUAlchemists.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGameUI extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainGame.fxml"));
        //Parent root = fxmlLoader.load();
        //Scene scene = new Scene(root, 320, 240);
//        primaryStage.setScene(scene);

        primaryStage.setTitle("KU Alchemists");
        primaryStage.show();

    }
}
