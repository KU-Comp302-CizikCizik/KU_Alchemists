package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AvailableRoomsController {

    @FXML
    private VBox roomListContainer; // VBox to contain the list of rooms

    // Initialize method (called after all @FXML annotated members are injected)
    public void initialize() {
        // Example of populating the list, replace with dynamic data from your game server
        addRoomToList("Room 1");
        addRoomToList("Room 2");
        // ... more rooms
    }

    // Method to add a room to the list
    private void addRoomToList(String roomName) {
        HBox roomEntry = new HBox(10); // HBox for each room entry
        roomEntry.getChildren().add(new Text(roomName)); // Add room name

        Button joinButton = new Button("Join");
        joinButton.setOnAction(e -> joinRoom(roomName)); // Set action on join button
        roomEntry.getChildren().add(joinButton); // Add join button

        roomListContainer.getChildren().add(roomEntry); // Add entry to the container
    }

    // Method to handle joining a room
    private void joinRoom(String roomName) {
        // Logic to join the specified room
        System.out.println("Joining " + roomName);
    }
}
