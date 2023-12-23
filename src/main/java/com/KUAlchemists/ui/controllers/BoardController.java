package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.BoardHandler;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

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
    

    @FXML
    void debunkPopUp(ActionEvent event) {
        SceneLoader.getInstance().loadDebunk();
    }

    @FXML
    void deductionBoardPopUp(ActionEvent event) {

        SceneLoader.getInstance().loadDeductionBoard();
    }

    @FXML
    void endorsePopUp(ActionEvent event) {
        SceneLoader.getInstance().loadEndorse();
    }

    @FXML
    void ingredientStoragePopUp(ActionEvent event) {
        SceneLoader.getInstance().loadIngredientStorage();

    }

    @FXML
    void potionBrewingPopUp(ActionEvent event) {SceneLoader.getInstance().loadPotionBrewing();
    }

    @FXML
    void publicationTrackPopUp(ActionEvent event) {
        SceneLoader.getInstance().loadPublicationTrack();
    }

    @FXML
    void publishTheoryPopUp(ActionEvent event) {
        SceneLoader.getInstance().loadPublishTheory();
    }

    @FXML
    void buyArtifactPopUp(ActionEvent event) {
        SceneLoader.getInstance().loadBuyArtifact();
    }

    @FXML
    void forageIngredientPopUp(ActionEvent event) {
        String ingredient = ForageForIngredientHandler.getInstance().forageForIngredient();
        if(ingredient == null){
            SceneLoader.getInstance().loadGenericPopUp("No enough action points");
        }
        else{
            String message = "You have foraged " + ingredient + "!";
            setActionPoint(GameEngine.getInstance().getCurrentPlayerIndex()+1,GameEngine.getInstance().getCurrentPlayer().getActionPoints());
            SceneLoader.getInstance().loadForageIngredient(message, ingredient+"-ingredient.jpg");
        }

    }


    @FXML
    void useArtifactPopUp(ActionEvent event) {

        SceneLoader.getInstance().loadUseArtifact();
    }

    @FXML
    void pausePopUp(ActionEvent event) {
        SceneLoader.getInstance().loadPause();
    }

    @FXML
    void helpPopUp(ActionEvent event) {
        SceneLoader.getInstance().loadHelp();
    }

    @FXML
    void sellPotionPopUp(ActionEvent event) {

        System.out.println("not implemented Yet");
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
        ArrayList<Integer> round_tour_info = BoardHandler.getInstance().endTheTour();
        System.out.println(round_tour_info);
        //For game over screen, we have extra variable GAMEOVER_ROUND(-1);, check for round to be -1 or not
        //use for updating the UI
        //round_tour_info[0] = round
        //round_tour_info[1] = tour
        changeRound(); // we may create another method with more comprehensive name for the task, updating round & tour, string of buttons, etc.

    }

    public BoardController() {

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
