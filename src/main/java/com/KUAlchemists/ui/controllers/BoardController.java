package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.MainPageHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardController {
    @FXML
    private TextField alchemist1GoldTextField;

    @FXML
    private TextField alchemist1ReputationTextField;

    @FXML
    private TextField alchemist2GoldTextField;

    @FXML
    private TextField alchemist2ReputationTextField;

    @FXML
    private ImageView currentAvatarImage;

    @FXML
    private Button debunkButton;

    @FXML
    private Button deductionBoardButton;

    @FXML
    private Button helpButton;


    @FXML
    private Button ingredientStorageButton;

    @FXML
    private ImageView nextAvatarImage;

    @FXML
    private Button potionBrewingButton;

    @FXML
    private Button publicationTrackButton;

    @FXML
    private Button publishTheoryButton;

    @FXML
    private Button sellPotionButton;

    @FXML
    private Button useArtifactButton;

    @FXML
    private Button buyArtifactButton;

    @FXML
    private Button forageInrgedientButton;

    @FXML
    private Button pauseButton;




    private MainPageHandler mainPageHandler;

    @FXML
    void debunkPopUp(ActionEvent event) {
        mainPageHandler.openDebunkPopUp();
    }

    @FXML
    void deductionBoardPopUp(ActionEvent event) {
        mainPageHandler.openDeductionBoardPopUp();
    }

    @FXML
    void ingredientStoragePopUp(ActionEvent event) {
        mainPageHandler.openIngredientStoragePopUp();
    }

    @FXML
    void potionBrewingPopUp(ActionEvent event) {
        mainPageHandler.openPotionBrewingPopUp();
    }

    @FXML
    void publicationTrackPopUp(ActionEvent event) {
        mainPageHandler.openPublicationTrackPopUp();
    }

    @FXML
    void publishTheoryPopUp(ActionEvent event) {
        mainPageHandler.openPublishTheoryPopUp();
    }

    @FXML
    void buyArtifactPopUp(ActionEvent event) {
        mainPageHandler.openBuyArtifactPopUp();
    }

    @FXML
    void forageIngredientPopUp(ActionEvent event) {
        mainPageHandler.forageIngredientPopUp();
    }


    @FXML
    void useArtifactPopUp(ActionEvent event) {
        System.out.println("Artifact button pressed");
    }

    @FXML
    public void changeRound() {
        Image currentAvatarImg = currentAvatarImage.getImage();
        currentAvatarImage.setImage(nextAvatarImage.getImage());
        nextAvatarImage.setImage(currentAvatarImg);
    }

    @FXML
    public void setReputation(Integer player, Integer reputation) {
        if (player == 1) {
            alchemist1ReputationTextField.setText(Integer.toString(reputation));
        } else if (player == 2) {
            alchemist2ReputationTextField.setText(Integer.toString(reputation));
        } else{
            throw new IllegalArgumentException("Invalid player number");
        }
    }

    @FXML
    public void setGold(Integer player, Integer gold) {
        if (player == 1) {
            alchemist1GoldTextField.setText(Integer.toString(gold));
        } else if (player == 2) {
            alchemist2GoldTextField.setText(Integer.toString(gold));
        } else{
            throw new IllegalArgumentException("Invalid player number");
        }
    }

    public BoardController() {
        MainPageHandler mainPageHandler = new MainPageHandler();
    }
}
