package com.KUAlchemists.ui;
import com.KUAlchemists.ui.controllers.DeductionBoardController;
import com.KUAlchemists.ui.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.KUAlchemists.ui.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;


public class TestUI extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Properties prop = new Properties();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("deductionboard.fxml"));

       // DeductionBoardController.setProperties(prop);
        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.show();
    }
}