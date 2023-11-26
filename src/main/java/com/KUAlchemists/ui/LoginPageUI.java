package com.KUAlchemists.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class LoginPageUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LoginPageUI.fxml"));
        fxmlLoader.setRoot(new BorderPane());
        Parent parent = fxmlLoader.load();




        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(parent, 320, 240));
        primaryStage.show();
}
}
