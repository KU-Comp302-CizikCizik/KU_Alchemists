package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.BoardHandler;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.observer.PlayerObserver;
import com.KUAlchemists.ui.SceneLoader;
import javafx.application.Platform;
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

public class BoardController implements PlayerObserver {
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

    private int currentRound;
    private int currentTour;


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
        ArrayList<Integer> round_tour_info = BoardHandler.getInstance().endTheTour();
        Integer round = round_tour_info.get(0);
        Integer tour = round_tour_info.get(1);
        currentRound = round;
        currentTour = tour;

        roundTitle.setText("Round " + round.toString());
        tourTitle.setText("Tour " + tour.toString());
        //For game over screen, we have extra variable GAMEOVER_ROUND(-1);, check for round to be -1 or not
        //use for updating the UI
        //round_tour_info[0] = round
        //round_tour_info[1] = tour

        //load the round when round is changed
        if (round == 3) {
            loadRound3();
        } else if (round == 2) {
            lodRound2();
        } else if (round == 1) {
            loadRound1();
        }

        if(tour == 3) {
            endRoundButton.setText("End The Round");
            endRoundButton.setEffect(new DropShadow(30, Color.WHITE));
        }else {
            endRoundButton.setText("End The Tour");
            endRoundButton.setEffect(null);
        }

        //check whether the tour is last

        changeAvatars();
    }

    private void changeAvatars() {
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
        //check whether final round or not
        if (currentRound == 3 && currentTour == 3 && GameEngine.getInstance().getCurrentPlayerIndex() == 1) {
          SceneLoader.getInstance().loadFinalScore();
        }else{
            changeRound();
        }
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
        if(!button.isDisable()){
            return;
        }
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

        KeyFrame startGlow = new KeyFrame(Duration.seconds(1.1), e -> {
            // Set the final opacity (1.0 for fully opaque)
            button.setEffect(new Glow(0.3));
        });

        KeyFrame endGlow = new KeyFrame(Duration.seconds(2), e -> {
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
        BoardHandler.getInstance().registerPlayerObserver(this);
        setGold(1, BoardHandler.getInstance().getPlayerGold(0));
        setGold(2, BoardHandler.getInstance().getPlayerGold(1));
        setReputation(1, BoardHandler.getInstance().getPlayerReputation(0));
        setReputation(2, BoardHandler.getInstance().getPlayerReputation(1));
        setActionPoint(1, BoardHandler.getInstance().getPlayerActionPoints(0));
        setActionPoint(2, BoardHandler.getInstance().getPlayerActionPoints(1));
    }

    @Override
    public void onPlayerStatusChanged(String status) {

    }

    @Override
    public void onPlayerSicknessLevelChanged(int sicknessLevel) {

    }

    @Override
    public void onPlayerReputationChanged(int reputation) {
        Platform.runLater(() -> {
            // Assume playerIndex is available to determine which player's gold changed
            setReputation(GameEngine.getInstance().getCurrentPlayerIndex()+1, reputation);
        });
    }

    @Override
    public void onPlayerGoldChanged(int newGold) {
        Platform.runLater(() -> {
            // Assume playerIndex is available to determine which player's gold changed
            setGold(GameEngine.getInstance().getCurrentPlayerIndex()+1, newGold);
        });
    }

    @Override
    public void onPlayerActionPointsChanged(int actionPoints) {
        Platform.runLater(() -> {
            // Assume playerIndex is available to determine which player's gold changed
            setActionPoint(GameEngine.getInstance().getCurrentPlayerIndex()+1, actionPoints);
        });

    }

    @Override
    public void onPlayerNameChanged(String name) {

    }
}
