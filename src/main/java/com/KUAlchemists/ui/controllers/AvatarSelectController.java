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
    private ArrayList<ImageView> pictures;

    private int currentPlayer = 1; // Player 1 is the first player to select avatar

    @FXML
    void initialize() {
        pictures = new ArrayList<>(Arrays.asList(avatar_1, avatar_2, avatar_3, avatar_4, avatar_5, avatar_6, avatar_7, avatar_8));
        txt.setText("Player 1 Your turn:");
    }
    @FXML
    void clicked(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        Glow selectGlow = new Glow(1.7f);
        for (ImageView image : pictures) {
            if (image.equals(clickedImage)) {
                image.setEffect(selectGlow);
                AvatarSelectHandler.getInstance().handleSetAvatar(image.getId(),currentPlayer);
                int numberOfPlayers = GameEngine.getInstance().getCurrentGameMode().getNumberOfPlayers();
                if (currentPlayer < numberOfPlayers) {
                    currentPlayer++;
                    resetSelectionUI(image);
                    txt.setText("Player " + currentPlayer + " Your turn:");
                } else {
                    System.out.println("All players have selected");
                    // All players have selected, proceed to the main game
                    SceneLoader.getInstance().loadBoard();
                }

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
        ImageView clickedImage = (ImageView) event.getSource();
        Glow selectGlow = new Glow(1.4f);
        clickedImage.setEffect(selectGlow);

    }

    @FXML void unglow(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        clickedImage.setEffect(null);
    }


}
