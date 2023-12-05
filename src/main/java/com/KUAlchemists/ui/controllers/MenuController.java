package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.MenuHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

public class MenuController {
    @FXML
    private Button startButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button rulesButton;

    private final MenuHandler menuHandler;

    public MenuController() {

        menuHandler = new MenuHandler();
    }
    @FXML
    void openRules(ActionEvent event) {
        menuHandler.openRules();
        System.out.println("Rules");

    }

    @FXML
    void openSettings(ActionEvent event) {
        menuHandler.openSettings();
    }

    @FXML
    void startGame(ActionEvent event) {
        menuHandler.startGame();
    }

}
