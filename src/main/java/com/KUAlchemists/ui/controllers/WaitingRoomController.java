package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class WaitingRoomController {

    @FXML
    private Rectangle player1Square;

    @FXML
    private Rectangle player2Square;

    @FXML
    private Rectangle player3Square;

    @FXML
    private Rectangle player4Square;

    @FXML
    private Button startGameButton;

    // Initialize method (called after all @FXML annotated members are injected)
    public void initialize() {
        // Initially, disable the start game button until the room is full
        startGameButton.setDisable(true);
    }

    @FXML
    void startGameButtonAction() {
        // Logic to start the game


    }
}
