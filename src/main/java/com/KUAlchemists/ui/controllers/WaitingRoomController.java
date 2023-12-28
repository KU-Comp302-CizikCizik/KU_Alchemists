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

        // Example setup - you will need to link this with your game's player joining logic
        // showPlayerInSquare(1); // Call this when player 1 joins
        // showPlayerInSquare(2); // Call this when player 2 joins, and so on...
        //showPlayerInSquare(3);
        // showPlayerInSquare(4);
    }

    // Method to display a player in a square
    public void showPlayerInSquare(int playerNumber) {
        switch(playerNumber) {
            case 1:
                player1Square.setVisible(true);
                break;
            case 2:
                player2Square.setVisible(true);
                break;
            case 3:
                player3Square.setVisible(true);
                break;
            case 4:
                player4Square.setVisible(true);
                checkIfRoomIsFull();
                break;
            default:
                // Handle unexpected player number
                break;
        }
    }

    // Method to check if the room is full
    private void checkIfRoomIsFull() {
        // Assuming the room is full when all four players have joined
        if (player1Square.isVisible() && player2Square.isVisible() &&
                player3Square.isVisible() && player4Square.isVisible()) {
            startGameButton.setDisable(false); // Enable the start game button
        }
    }

    @FXML
    void startGameButtonAction() {
        // Logic to start the game
    }
}
