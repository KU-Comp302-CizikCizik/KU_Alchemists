package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.BoardHandler;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javafx.util.Duration;
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
    private Button endRoundButton;


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
    private Text roundTitle;

    @FXML
    private Text tourTitle;

    private Integer currentRound;
    

    @FXML
    void debunkPopUp(ActionEvent event) {
        SceneLoader.getInstance().loadDebunk();
    }

    @FXML
    void deductionBoardPopUp(ActionEvent event) {

        SceneLoader.getInstance().loadDeductionBoard();
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
        Integer round = round_tour_info.get(0);
        Integer tour = round_tour_info.get(1);
        roundTitle.setText("Round " + round.toString());
        tourTitle.setText("Tour " + tour.toString());
        //For game over screen, we have extra variable GAMEOVER_ROUND(-1);, check for round to be -1 or not
        //use for updating the UI
        //round_tour_info[0] = round
        //round_tour_info[1] = tour

        //load the round when round is changed
        if(round != currentRound) {
            if (round == 3) {
                currentRound = 3;
                loadRound3();
            } else if (round == 2) {
                currentRound = 2;
                lodRound2();
            } else if (round == 1) {
                currentRound = 1;
                loadRound1();
            }
        }

        //check whether the tour is last
        if(tour == 3){
            endRoundButton.setText("End The Round");
            endRoundButton.setEffect(new DropShadow(30, Color.WHITE));
        } else if (tour == 1) {
            if(round == 3 && GameEngine.getInstance().getCurrentPlayerIndex() == 0){
                SceneLoader.getInstance().loadFinalScore();
            }
            endRoundButton.setText("End The Tour");
            endRoundButton.setEffect(null);
        }

        changeRound(); // we may create another method with more comprehensive name for the task, updating round & tour, string of buttons, etc.

    }
    private void disableButtons(Button button) {
        button.setDisable(true);
        button.setOpacity(0.5);
    }

    private void loadRound1() {
        disableButtons(sellPotionButton);
        disableButtons(publishTheoryButton);
        disableButtons(debunkButton);
    }

    private void activateButtons(Button button) {
        button.setDisable(false);

        Timeline timeline = new Timeline();

        // KeyFrame defines the values at specific points in time
        KeyFrame startFrame = new KeyFrame(Duration.ZERO, e -> {
            // Set the initial opacity (0.0 for completely transparent)
            button.setOpacity(0.5);
        });

        KeyFrame endFrame = new KeyFrame(Duration.seconds(1), e -> {
            // Set the final opacity (1.0 for fully opaque)
            button.setOpacity(1.0);
        });

        KeyFrame startGlow = new KeyFrame(Duration.seconds(1.5), e -> {
            // Set the final opacity (1.0 for fully opaque)
            button.setEffect(new Glow(0.3));
        });

        KeyFrame endGlow = new KeyFrame(Duration.seconds(2.5), e -> {
            // Set the final opacity (1.0 for fully opaque)
            button.setEffect(null);
        });

        // Add the KeyFrames to the Timeline
        timeline.getKeyFrames().addAll(startFrame, endFrame,startGlow,endGlow);
        timeline.play();
    }

    private void lodRound2() {
        activateButtons(sellPotionButton);
        activateButtons(publishTheoryButton);

    }

    private void loadRound3() {
        activateButtons(debunkButton);
    }

    public BoardController() {
        currentRound = -1;
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
