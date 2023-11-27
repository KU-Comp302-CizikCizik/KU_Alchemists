package com.KUAlchemists.ui;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import com.KUAlchemists.backend.controllers.LoginController;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class LoginPageUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        Properties prop = new Properties();
        InputStream inputStream = null;
        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        // Call setProperties to configure the controller



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LoginPageUI.fxml"));
        fxmlLoader.setRoot(new BorderPane());
        Parent parent = fxmlLoader.load();

        // Get the controller from the FXMLLoader
        LoginController controller = fxmlLoader.getController();
        controller.setProperties(prop);



        primaryStage.setTitle("KU Alchemists");
        primaryStage.setScene(new Scene(parent, 320, 240));
        primaryStage.show();
}
}
