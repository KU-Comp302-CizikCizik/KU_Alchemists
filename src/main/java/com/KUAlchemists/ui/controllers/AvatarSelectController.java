package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.AvatarSelectHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class AvatarSelectController {
    private boolean p = false;
    @FXML
    private ImageView image_1;
    @FXML
    private ImageView image_2;
    @FXML
    private ImageView image_3;
    @FXML
    private ImageView image_4;
    @FXML
    private ImageView image_5;
    @FXML
    private ImageView image_6;
    @FXML
    private ImageView image_7;
    @FXML
    private ImageView image_8;
    @FXML
    private Text txt;
    @FXML
    private Button startGameButton;


    private ArrayList<ImageView> pictures;
    private ArrayList<ImageView> selectedImages = new ArrayList<>(); // List of selected images
    private int currentPlayer = 1; // Player 1 is the first player to select avatar

    @FXML
    void initialize() {
        pictures = new ArrayList<>(Arrays.asList(image_1, image_2, image_3, image_4, image_5, image_6, image_7, image_8));
        for (ImageView image : pictures) {
            image.setUserData(false); // Initially, no image is selected
        }
        txt.setText("Player 1 Your turn:");
    }

    @FXML
    void clicked(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        // Check if this image is already selected
        if ((Boolean) clickedImage.getUserData()) {
            return; // Ignore click if the image is already selected
        }
        Glow selectGlow = new Glow(1.7f);
        clickedImage.setEffect(selectGlow);
        clickedImage.setUserData(true); // Mark this image as selected
        selectedImages.add(clickedImage); // Add to the list of selected images
        AvatarSelectHandler.getInstance().handleSetAvatar(clickedImage.getId());

        int numberOfPlayers = GameEngine.getInstance().getPlayerList().size();
        System.out.println("Current player: " + currentPlayer);
        System.out.println("Number of players: " + numberOfPlayers);

        if (currentPlayer < numberOfPlayers) {
            currentPlayer++;
            resetSelectionUI();
            txt.setText("Player " + currentPlayer + " Your turn:");
        } else {
            System.out.println("All players have selected");
            // All players have selected, proceed to the main game
            startGameButton.setDisable(false); // Enable the Start Game button

        }
    }

    private void resetSelectionUI() {
        for (ImageView image : pictures) {
            // Only reset images that are not in the selectedImages list
            if (!selectedImages.contains(image)) {
                image.setEffect(null);
            }
        }
    }

    @FXML
    void glow(MouseEvent event) {
        // Your implementation for glow effect on hover (if needed)
    }

    @FXML
    void loadBoard(ActionEvent event) {
        // Logic to load the game board
        SceneLoader.getInstance().loadBoard();
    }

}
