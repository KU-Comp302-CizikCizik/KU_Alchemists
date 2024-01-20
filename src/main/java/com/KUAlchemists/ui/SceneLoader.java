package com.KUAlchemists.ui;

import com.KUAlchemists.ui.controllers.GenericWindowController;
import com.KUAlchemists.ui.controllers.LoginController;
import com.KUAlchemists.ui.utils.UIConstants;
import com.KUAlchemists.ui.utils.UILoader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    public void setOnKeyPressedEventHandler(EventHandler<KeyEvent> eventHandler) {
        MainApplicationUI.scene.setOnKeyPressed(eventHandler);
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

        FXMLLoader loader = UILoader.getLoader();
        setOnKeyPressedEventHandler(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER:
                        LoginController loginController = loader.getController();
                        loginController.loginButtonOnAction(null);
                        break;
                }
            }
        });

    }

    public void loadGameMode() {
        root = UILoader.loadFXML(UIConstants.ONLINE_OFFLINE_UI_FXML);
        Scene oldScene = MainApplicationUI.stage.getScene();
        MainApplicationUI.scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        MainApplicationUI.stage = (Stage) oldScene.getWindow();
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.centerOnScreen();
        MainApplicationUI.stage.show();
    }

    public void loadOnlineGameRoomScreen() {
        root = UILoader.loadFXML(UIConstants.ONLINE_GAME_ROOM_UI_FXML);
        Scene oldScene = MainApplicationUI.stage.getScene();
        MainApplicationUI.scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        MainApplicationUI.stage = (Stage) oldScene.getWindow();
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.centerOnScreen();
        MainApplicationUI.stage.show();
    }
    public void loadNumberOfPlayersScreen(){
        root = UILoader.loadFXML(UIConstants.NUMBER_OF_PLAYERS_UI_FXML);
        Scene oldScene = MainApplicationUI.stage.getScene();
        MainApplicationUI.scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        MainApplicationUI.stage = (Stage) oldScene.getWindow();
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.centerOnScreen();
        MainApplicationUI.stage.show();
    }

    public void loadWaitingRoomScreen(){
        root = UILoader.loadFXML(UIConstants.WAITING_ROOM_UI_FXML);
        Scene oldScene = MainApplicationUI.stage.getScene();
        MainApplicationUI.scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        MainApplicationUI.stage = (Stage) oldScene.getWindow();
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.centerOnScreen();
        MainApplicationUI.stage.show();
    }


    public void loadAvatarSelectScreen(){
        root = UILoader.loadFXML(UIConstants.AVATAR_SELECT_UI_FXML);
        Scene oldScene = MainApplicationUI.stage.getScene();
        MainApplicationUI.scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        MainApplicationUI.stage = (Stage) oldScene.getWindow();
        MainApplicationUI.stage.setScene(MainApplicationUI.scene);
        MainApplicationUI.stage.centerOnScreen();
        MainApplicationUI.stage.show();

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

    public void loadIngredientStorage() {
        loadPopUp(UIConstants.INGREDIENTSTORAGE_UI_FXML);
    }


    public void loadPublicationTrack() {
        loadPopUp(UIConstants.PUBLICATIONTRACK_UI_FXML);
    }

    public void loadDeductionBoard() {
        loadPopUp(UIConstants.DEDUCTIONBOARD_FXML);
    }

    public void loadHelp() {
        loadPopUp(UIConstants.HELP_UI_FXML);
    }

    public void loadFinalScoring() {
        loadPopUp(UIConstants.FINAL_SCORING_UI_FXML);
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
    public void loadPopUpUndecorated(String fxml_path) { //This opens a popup without close, or any other buttons on th top.
        Dialog<Void> dialog = new Dialog<>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UILoader.class.getClassLoader().getResource(fxml_path));
            dialog.initStyle(StageStyle.UNDECORATED);
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
        setOnKeyPressedEventHandler(null);
        MainApplicationUI.stage.centerOnScreen();
        MainApplicationUI.stage.show();

    }

    public void loadFinalScore(){
        loadGenericPopUp("Temporary final score window");
    }

    public void loadBuyArtifact(){
        loadPopUp(UIConstants.BUYARTIFACT_UI_FXML);
    }
    public void loadPublishAlchemyPart(){
        loadPopUp(UIConstants.PUBLISH_THEORY_ALCHEMY_FXML);
    }
    public void loadUseArtifact(){
        loadPopUp(UIConstants.USEARTIFACT_UI_FXML);
    }
    public void loadElixirOfInsight(){
        loadPopUp(UIConstants.ELIXIR_OF_INSIGHT);
    }
    public void loadPotionBrewing(){
        loadPopUp(UIConstants.POTIONBREW_UI_FXML);
    }
    public void loadMakeExperiment(){
        loadPopUp(UIConstants.MAKE_EXPERIMENT_UI_FXML);
    }
    public void loadPublishTheory(){
        loadPopUp(UIConstants.PUBLISH_THEORY_UI_FXML);
    }
    public void loadExperienceResult(){
        loadPopUp(UIConstants.EXPERIENCE_RESULT_UI_FXML);
    }

    public void loadSellPotion(){
        loadPopUp(UIConstants.SELL_POTION_UI_FXML);
    }

    public void loadDebunk() { loadPopUp(UIConstants.DEBUNK_UI_FXML); }

    public void loadMagicMortarArtifact(){
        loadPopUpUndecorated(UIConstants.MAGIC_MORTAR_UI_FXML);
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

    public void loadEndorse() {
        loadPopUp(UIConstants.ENDORSE_UI_FXML);
    }

    public void loadWisdomIdol() {
        loadPopUp(UIConstants.WISDOMIDOL_UI_FXML);
    }

}
