package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.MenuHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {
    private MenuHandler menuHandler;
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
