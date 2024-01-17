package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.NetworkHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class WaitingRoomController {

    @FXML
    Button startGameButton;

    @FXML
    Text numberofplayers;

    // Initialize method (called after all @FXML annotated members are injected)
    public void initialize() {
        // Initially, disable the start game button until the room is full
        if (GameEngine.getInstance().getCurrentPlayer().getUserType() == UserType.CLIENT) {
            startGameButton.setDisable(true);
        }
        initNumberofPlayersText();
    }

    private void initNumberofPlayersText() {
        String text = Integer.toString(GameEngine.getInstance().getPlayerList().size());
        text += " players joined the room";
        numberofplayers.setText(text);
    }

    @FXML
    void onStartGame() {
        // Logic to start the game
        System.out.println("Start game button pressed");
        NetworkHandler.getInstance().handleSendData();

    }
}
