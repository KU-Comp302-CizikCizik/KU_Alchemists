package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.ApplicationMode;
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
    @FXML
    private ImageView avatar_1;

    @FXML
    private ImageView avatar_2;

    @FXML
    private ImageView avatar_3;

    @FXML
    private ImageView avatar_4;

    @FXML
    private ImageView avatar_5;

    @FXML
    private ImageView avatar_6;

    @FXML
    private ImageView avatar_7;

    @FXML
    private ImageView avatar_8;

    @FXML
    private Text txt;
    @FXML
    private Button startGameButton;


    private ArrayList<ImageView> pictures;
    private ArrayList<ImageView> selectedImages = new ArrayList<>(); // List of selected images
    private int currentPlayer = 1; // Player 1 is the first player to select avatar

    @FXML
    void initialize() {
        pictures = new ArrayList<>(Arrays.asList(avatar_1, avatar_2, avatar_3, avatar_4, avatar_5, avatar_6, avatar_7, avatar_8));
        for (ImageView image : pictures) {
            image.setUserData(false); // Initially, no image is selected
        }
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.ONLINE){
            txt.setText("Select your avatar!");
        }
        else{
            txt.setText("Player 1 Your turn!");
        }

    }

    @FXML
    void clicked(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        // Check if this image is already selected
        if ((Boolean) clickedImage.getUserData()) {
            // display error message
            txt.setText("This avatar is already selected!");
            return; // Ignore click if the image is already selected
        }
        Glow selectGlow = new Glow(1.7f);

        clickedImage.setEffect(selectGlow);
        clickedImage.setUserData(true); // Mark this image as selected
        selectedImages.add(clickedImage); // Add to the list of selected images
        AvatarSelectHandler.getInstance().handleSetAvatar(clickedImage.getId(),currentPlayer-1); // for indexing -1
        int numberOfPlayers = GameEngine.getInstance().getCurrentGameMode().getNumberOfPlayers();


        if (currentPlayer < numberOfPlayers) {
            currentPlayer++;
            resetSelectionUI();
            txt.setText("Player " + currentPlayer + " Your turn!");


        } else {
            // All players have selected, proceed to the main game
            txt.setText("You can join the game now!");
            startGameButton.setDisable(false); // Enable the Start Game button
            startGameButton.setOpacity(0.90);

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
        ImageView clickedImage = (ImageView) event.getSource();
        Glow selectGlow = new Glow(1.4f);
        clickedImage.setEffect(selectGlow);

    }

    @FXML
    void loadBoard(ActionEvent event) {
        // Logic to load the game board
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.ONLINE) {
            SceneLoader.getInstance().loadWaitingRoomScreen();
        }
        else{
            SceneLoader.getInstance().loadBoard();
        }

    }

    @FXML void unglow(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        clickedImage.setEffect(null);
    }


}
