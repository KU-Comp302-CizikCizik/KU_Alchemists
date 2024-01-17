package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.GameStatus;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.WaitingRoomHandler;
import com.KUAlchemists.backend.network.NetworkHandler;
import com.KUAlchemists.backend.observer.GameStatusObserver;
import com.KUAlchemists.ui.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class WaitingRoomController implements GameStatusObserver {

    @FXML
    Button startButton;

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
        WaitingRoomHandler.getInstance().startGameForAllPlayers();
        SceneLoader.getInstance().loadBoard();
    }

    @Override
    public void onGameStatusChanged(GameStatus status) {
        SceneLoader.getInstance().loadBoard();
    }
}
