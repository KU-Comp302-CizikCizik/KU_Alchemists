package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.network.Client;
import com.KUAlchemists.backend.network.Server;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
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

    @FXML
    void createRoom(ActionEvent event) {
        // opens a new window for the host to wait for players to join
        SceneLoader.getInstance().loadWaitingRoomScreen();

        // Start the server in a new thread so it doesn't block the UI
        new Thread(() -> {
            server = new Server(7777); // Use an appropriate port for your game
            try {
                server.startServer();
            } catch (IOException e) {
                e.printStackTrace(); // Handle this exception properly
            }
        }).start();
    }

    @FXML
    void joinRoom(ActionEvent event) {
        // opens a new window for the player to enter the room code
        // if the room code is valid, the player will be added to the room
        // if the room code is invalid, the player will be notified
        SceneLoader.getInstance().loadAvailableRoomsScreen();

        TextInputDialog dialog = new TextInputDialog("localhost");
        dialog.setTitle("Join Game Room");
        dialog.setHeaderText("Enter the IP address of the host:");
        dialog.setContentText("IP Address:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(ipAddress -> {
            client = new Client(ipAddress, 7777); // Use the same port as the server
            try {
                client.connect();
                client.listenForMessages(); // Start listening for messages from the server
                client.send(new Player("Player")); // Send the player's name to the server
                // Once connected, change UI to the game room

                SceneLoader.getInstance().loadWaitingRoomScreen();
            } catch (IOException e) {
                e.printStackTrace(); // Handle this exception properly
            }
        });
    }
}








