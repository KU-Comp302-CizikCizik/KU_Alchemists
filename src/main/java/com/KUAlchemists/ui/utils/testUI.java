package com.KUAlchemists.ui.utils;

import com.KUAlchemists.ui.controllers.BoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class testUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BoardUI.fxml"));
        Parent root = loader.load();

        // Access the controller if needed
        BoardController controller = loader.getController();

        Scene scene = new Scene(root, 1080, 720);

        primaryStage.setTitle("FXML Example");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}