package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.OnlineGameRoomHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class OnlineGameRoomController {

    @FXML
    private Button createRoomButton;

    @FXML
    private Button joinRoomButton;


    @FXML
    void initialize() {
    }

    @FXML
    void createRoom(ActionEvent event) {
        int port = 7777; // ask user for port
        OnlineGameRoomHandler.getInstance().startAsHost(port);
        // opens a new window for the host to wait for players to join
        SceneLoader.getInstance().loadAvatarSelectScreen();

    }

    @FXML
    void joinRoom(ActionEvent event) {
        // opens a new window for the player to enter the room code
        // if the room code is valid, the player will be added to the room
        // if the room code is invalid, the player will be notified
        TextInputDialog dialog = new TextInputDialog("localhost");
        dialog.setTitle("Join Game Room");
        dialog.setHeaderText("Enter the IP address of the host:");
        dialog.setContentText("IP Address:");

        int port = 7777;

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(ipAddress -> {
            OnlineGameRoomHandler.getInstance().startAsClient(ipAddress,port);
        });
        SceneLoader.getInstance().loadAvatarSelectScreen();
    }
}








