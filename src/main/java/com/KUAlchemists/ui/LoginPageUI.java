package com.KUAlchemists.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginPageUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("../../../../resources/LoginPageUI.fxml"));
        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(root, 320, 240));
        primaryStage.show();
}
}
