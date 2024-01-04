package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.MenuHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import java.awt.event.KeyEvent;

public class MenuController {
    @FXML
    private Button startButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button rulesButton;


    public MenuController() {

    }
    @FXML
    void openRules(ActionEvent event) {
        MenuHandler.getInstance().openRules();
        System.out.println("Rules");

    }

    @FXML
    void openSettings(ActionEvent event) {
        MenuHandler.getInstance().openSettings();
    }

    @FXML
    void startGame(ActionEvent event) {
        SceneLoader.getInstance().loadGameMode();
    }

}
