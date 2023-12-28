package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OnlineGameRoomScreenController {

    @FXML
    private Button createRoomButton;

    @FXML
    private Button joinRoomButton;

    @FXML
    void createRoom(ActionEvent event) {
        // opens a new window for the host to wait for players to join
        SceneLoader.getInstance().loadWaitingRoomScreen();
    }

    @FXML
    void joinRoom(ActionEvent event) {
        // opens a new window for the player to enter the room code
        // if the room code is valid, the player will be added to the room
        // if the room code is invalid, the player will be notified
        SceneLoader.getInstance().loadAvailableRoomsScreen();
    }
}








