package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.NetworkHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class WaitingRoomController {

    @FXML
    Button startButton;

    // Initialize method (called after all @FXML annotated members are injected)
    public void initialize() {
        // Initially, disable the start game button until the room is full
        if (GameEngine.getInstance().getCurrentPlayer().getUserType() == UserType.CLIENT) {
            startButton.setDisable(true);
        }
    }


    @FXML
    void onStartGame() {
        // Logic to start the game
        if(GameEngine.getInstance().getPlayerList().size() < 2){
            SceneLoader.getInstance().loadGenericPopUp("There must be at least 2 players to start the game");
            return;
        }
        System.out.println("Start game button pressed");
        NetworkHandler.getInstance().handleSendData();

    }
}
