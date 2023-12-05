package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.MenuHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

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
