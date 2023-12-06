package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.BoardHandler;
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
    @FXML
    private TextField alchemist2ActionPointTextField;
    
    @FXML
    private TextField alchemist1ActionPointTextField;
    
    private BoardHandler boardhandler;

    @FXML
    void debunkPopUp(ActionEvent event) {
        boardhandler.openDebunkPopUp();
    }

    @FXML
    void deductionBoardPopUp(ActionEvent event) {
        boardhandler.openDeductionBoardPopUp();
    }

    @FXML
    void ingredientStoragePopUp(ActionEvent event) {
        boardhandler.openIngredientStoragePopUp();
    }

    @FXML
    void potionBrewingPopUp(ActionEvent event) {
        boardhandler.openPotionBrewingPopUp();
    }

    @FXML
    void publicationTrackPopUp(ActionEvent event) {
        boardhandler.openPublicationTrackPopUp();
    }

    @FXML
    void publishTheoryPopUp(ActionEvent event) {
        boardhandler.openPublishTheoryPopUp();
    }

    @FXML
    void buyArtifactPopUp(ActionEvent event) {
        boardhandler.openBuyArtifactPopUp();
    }

    @FXML
    void forageIngredientPopUp(ActionEvent event) {
        boardhandler.forageIngredientPopUp();
    }


    @FXML
    void useArtifactPopUp(ActionEvent event) {
        boardhandler.openUseArtifacy();
    }

    @FXML
    void pausePopUp(ActionEvent event) {
        boardhandler.openPausePopUp();
    }

    @FXML
    void helpPopUp(ActionEvent event) {
        boardhandler.openHelpPopUp();
    }

    @FXML
    void sellPotionPopUp(ActionEvent event) {
        boardhandler.openSellPotion();
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
    
    @FXML
    public void setActionPoint(Integer player, Integer actionPoint) {
        if (player == 1) {
            alchemist1ActionPointTextField.setText(Integer.toString(actionPoint));
        } else if (player == 2) {
            alchemist2ActionPointTextField.setText(Integer.toString(actionPoint));
        } else{
            throw new IllegalArgumentException("Invalid player number");
        }
    }

    @FXML
    public void endTheRound() {
        boardhandler.endTheRound();
    }

    public BoardController() {
        boardhandler = new BoardHandler();
    }
    
    @FXML
    public void initialize() {
        alchemist1GoldTextField.setText("10");
        alchemist1ReputationTextField.setText("0");
        alchemist2GoldTextField.setText("10");
        alchemist2ReputationTextField.setText("0");
        alchemist1ActionPointTextField.setText("3");
        alchemist2ActionPointTextField.setText("3");
    }
}
