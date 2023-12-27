package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.AvatarSelectHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class AvatarSelectController {
    private boolean p=false;
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
    private ArrayList<ImageView> pictures;

    private int currentPlayer = 1; // Player 1 is the first player to select avatar

    @FXML
    void initialize() {
        pictures = new ArrayList<>(Arrays.asList(image_1, image_2, image_3, image_4, image_5, image_6, image_7, image_8));
        txt.setText("Player 1 Your turn:");
    }
    @FXML
    void clicked(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        Glow selectGlow = new Glow(1.7f);
        for (ImageView image : pictures) {
            if (image.equals(clickedImage)) {
                image.setEffect(selectGlow);
                AvatarSelectHandler.getInstance().handleSetAvatar(image.getId());
                int numberOfPlayers = GameEngine.getInstance().getPlayerList().size();
                System.out.println("Current player: " + currentPlayer);
                System.out.println("Number of players: " + numberOfPlayers);
                if (currentPlayer < numberOfPlayers) {
                    currentPlayer++;
                    resetSelectionUI(image);
                    txt.setText("Player " + currentPlayer + " Your turn:");
                } else {
                    System.out.println("All players have selected");
                    // All players have selected, proceed to the main game
                    SceneLoader.getInstance().loadBoard();
                }

               // p = !p; // Toggle player turn
            } else {
                image.setEffect(null); // Remove glow from other images
            }
        }
    }


    private void resetSelectionUI(ImageView selectedImage){
        for (ImageView image : pictures) {
            if (!image.equals(selectedImage)) {
                image.setEffect(null); // Remove glow from all images except the selected one
            }
        }
    }

    @FXML
    void glow(MouseEvent event) {



    }

}
