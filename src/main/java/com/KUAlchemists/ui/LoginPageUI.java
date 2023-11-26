package com.KUAlchemists.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class LoginPageUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LoginPageUI.fxml"));
        BorderPane root = fxmlLoader.load();
        //fxmlLoader.setRoot(new AnchorPane());
        fxmlLoader.setRoot(root);

        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(root, 320, 240));
        primaryStage.show();
}
}
