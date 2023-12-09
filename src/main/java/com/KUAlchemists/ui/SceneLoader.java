package com.KUAlchemists.ui;

import com.KUAlchemists.ui.controllers.GenericWindowController;
import com.KUAlchemists.ui.controllers.LoginController;
import com.KUAlchemists.ui.utils.UIConstants;
import com.KUAlchemists.ui.utils.UILoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class SceneLoader {


    private static SceneLoader INSTANCE;

    private Parent root;


    private SceneLoader() {

    }

    public static SceneLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SceneLoader();
        }
        return INSTANCE;
    }

    public void loadMenu() {
        root = UILoader.loadFXML(UIConstants.MENU_UI_FXML);
        Scene oldScene = MainApplicationUI.stage.getScene();
        MainApplicationUI.scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        MainApplicationUI.stage = (Stage) oldScene.getWindow();
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.centerOnScreen();
        MainApplicationUI.stage.show();
    }


    public void loadLogin() {
        root = UILoader.loadFXMLFirstTime(UIConstants.LOGINPAGE_UI_FXML);
        MainApplicationUI.scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);

        // Load properties from config.properties
        Properties prop = new Properties();
        InputStream inputStream = null;
        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        // Set the properties
        LoginController.setProperties(prop);
        MainApplicationUI.stage.setTitle(UIConstants.GAME_TITLE);
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.centerOnScreen();
        MainApplicationUI.stage.show();

    }

    public void loadIngredientStorage() {
        loadPopUp(UIConstants.INGREDIENTSTORAGE_UI_FXML);
    }


    public void loadPublicationTrack() {
        loadPopUp(UIConstants.PUBLICATIONTRACK_UI_FXML);
        System.out.println("loadPublicationTrack");
    }

    public void loadDeductionBoard() {
        loadPopUp(UIConstants.DEDUCTIONBOARD_FXML);
    }

    public void loadHelp() {
        loadPopUp(UIConstants.HELP_UI_FXML);
    }

    public void loadPause() {
        Dialog<Void> dialog = new Dialog<>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UILoader.class.getClassLoader().getResource(UIConstants.Pause_UI_FXML));
            dialog.getDialogPane().setContent(loader.load());
            dialog.setResizable(false);
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        dialog.show();
    }


    public void loadPopUp(String fxml_path) {
        Dialog<Void> dialog = new Dialog<>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UILoader.class.getClassLoader().getResource(fxml_path));
            dialog.getDialogPane().setContent(loader.load());
            dialog.setResizable(false);
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        dialog.show();
    }


    public void loadBoard() {
        root = UILoader.loadFXML(UIConstants.BOARD_UI_FXML);
        Scene oldScene = MainApplicationUI.stage.getScene();
        MainApplicationUI.scene = new Scene(root, UIConstants.GAME_WINDOW_WIDTH, UIConstants.GAME_WINDOW_HEIGHT);
        MainApplicationUI.stage = (Stage) oldScene.getWindow();
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.centerOnScreen();
        MainApplicationUI.stage.show();

    }

    public void loadBuyArtifact(){
        loadPopUp(UIConstants.BUYARTIFACT_UI_FXML);
    }

    public void loadUseArtifact(){

        loadPopUp(UIConstants.USEARTIFACT_UI_FXML);
    }
    public void loadPotionBrewing(){
        loadPopUp(UIConstants.POTIONBREW_UI_FXML);
    }


    public void loadForageIngredient(String message, String ingredientImage) {
        Dialog<Void> dialog = new Dialog<>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UILoader.class.getClassLoader().getResource(UIConstants.FORAGEINGREDIENT_UI_FXML));
            dialog.getDialogPane().setContent(loader.load());
            dialog.setResizable(false);
            GenericWindowController controller = loader.getController();
            controller.setImage(ingredientImage);
            controller.setTextField(message);
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        dialog.show();
    }

    public void loadGenericPopUp(String message) {
        Dialog<Void> dialog = new Dialog<>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UILoader.class.getClassLoader().getResource(UIConstants.GENERIC_FXML));
            dialog.getDialogPane().setContent(loader.load());
            dialog.setResizable(false);
            GenericWindowController controller = loader.getController();
            controller.setTextField(message);
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        dialog.show();

    }
}
