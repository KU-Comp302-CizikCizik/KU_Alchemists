package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.adapters.OnlineInitializationAdapter;
import com.KUAlchemists.backend.initializers.OnlineGameInitializer;
import com.KUAlchemists.backend.network.Client;
import com.KUAlchemists.backend.handlers.NetworkHandler;
import com.KUAlchemists.backend.network.Server;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class OnlineGameRoomScreenController {

    @FXML
    private Button createRoomButton;

    @FXML
    private Button joinRoomButton;

    // Reference to the server, if this instance creates one
    private Server server;

    // Reference to the client, if this instance joins a server
    private Client client;

    private OnlineInitializationAdapter onlineInitializationAdapter;

    @FXML
    void initialize() {
        onlineInitializationAdapter = new OnlineInitializationAdapter(new OnlineGameInitializer());
    }

    @FXML
    void createRoom(ActionEvent event) {
        // opens a new window for the host to wait for players to join
        NetworkHandler.getInstance().handleStartServer(7777); // default port we should ask for port
        SceneLoader.getInstance().loadWaitingRoomScreen();
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

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(ipAddress -> {
            NetworkHandler.getInstance().handleConnect(ipAddress, 7777);
            // default port: 7777, we should decide, do we request port info from user?
        });
        NetworkHandler.getInstance().handleSendDataToServer();
        SceneLoader.getInstance().loadWaitingRoomScreen();
    }
}








