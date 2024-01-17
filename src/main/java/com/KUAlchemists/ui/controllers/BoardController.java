package com.KUAlchemists.ui.controllers;
import com.KUAlchemists.backend.engine.GameEngine;

import com.KUAlchemists.backend.handlers.BoardHandler;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
import com.KUAlchemists.backend.handlers.NetworkHandler;

import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.observer.PlayerObserver;
import com.KUAlchemists.ui.SceneLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardController  implements PlayerObserver {

    @FXML
    private AnchorPane avatar1Pane;

    @FXML
    private AnchorPane avatar2Pane;

    @FXML
    private AnchorPane avatar3Pane;

    @FXML
    private AnchorPane avatar4Pane;

    @FXML
    private Text roundTitle;

    @FXML
    private Text tourTitle;

    @FXML
    private Button endRoundButton;

    @FXML
    private Button publishTheoryButton;
    @FXML
    private Button sellPotionButton;

    @FXML
    private Button deductionBoardButton;

    @FXML
    private Button potionBewingButton;

    @FXML
    private Button publicationTrackButton;


    private Integer currentRound;
    private Integer currentTour;;

    private ArrayList<AvatarCardController> playerControllers;

    private ArrayList<Pane> cardBoxList;



    @FXML
    void initialize() {
        //TO-DO: set the avatar cards
        playerControllers = new ArrayList<>();
        cardBoxList = new ArrayList<>();
        BoardHandler.getInstance().registerPlayerObserver(this);
        ArrayList<String> avatars = BoardHandler.getInstance().getAvatarStrings();

        if(avatars.size() == 2){
            setTwoPlayerGame(avatars);
        }
        else if(avatars.size() == 3){
            setThreePlayerGame(avatars);
        }
        else if(avatars.size() == 4){
            setFourPlayerGame(avatars);
        }
        else{
            System.out.println("Error: Invalid number of players");
        }

        //Assuming the game starts with round 1
        loadRound1();
    }


    private Pane setAvatarCard(String avatarName, int playerIndex){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("AvatarCard.fxml"));
        try {
            Pane cardBox = fxmlLoader.load();
            AvatarCardController controller = fxmlLoader.getController();
            playerControllers.add(controller);
            controller.setAvatarCardImage(avatarName);
            controller.setActionPoint(BoardHandler.getInstance().getPlayerActionPoints(playerIndex));
            controller.setGoldPoint(BoardHandler.getInstance().getPlayerGold(playerIndex));
            controller.setReputationPoint(BoardHandler.getInstance().getPlayerReputation(playerIndex));

            cardBoxList.add(cardBox);
            return cardBox;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTwoPlayerGame(ArrayList<String> avatars) {
        avatar1Pane.getChildren().add(setAvatarCard(avatars.get(0), 0));
        avatar4Pane.getChildren().add(setAvatarCard(avatars.get(1), 1));
    }
    private void setThreePlayerGame(ArrayList<String> avatars) {
        avatar1Pane.getChildren().add(setAvatarCard(avatars.get(0), 0));
        avatar2Pane.getChildren().add(setAvatarCard(avatars.get(1), 1));
        avatar3Pane.getChildren().add(setAvatarCard(avatars.get(2), 2));
    }

    private void setFourPlayerGame(ArrayList<String> avatars) {
        avatar1Pane.getChildren().add(setAvatarCard(avatars.get(0), 0));
        avatar2Pane.getChildren().add(setAvatarCard(avatars.get(1), 1));
        avatar4Pane.getChildren().add(setAvatarCard(avatars.get(2), 2));
        avatar3Pane.getChildren().add(setAvatarCard(avatars.get(3), 3));
    }

    @FXML
    void buyArtifactClicked(ActionEvent event) {
        SceneLoader.getInstance().loadBuyArtifact();

    }

    @FXML
    void deductionBoardClicked(ActionEvent event) {
        SceneLoader.getInstance().loadDeductionBoard();
    }

    @FXML
    void forageIngredientClicked(ActionEvent event) {
        String ingredient = ForageForIngredientHandler.getInstance().forageForIngredient();
        if(ingredient == null){
            SceneLoader.getInstance().loadGenericPopUp("No enough action points");
        }
        else{
            String message = "You have foraged " + ingredient + "!";
            SceneLoader.getInstance().loadForageIngredient(message, ingredient+"-ingredient.jpg");
        }    }

    @FXML
    void helpButtonClicked(ActionEvent event) {
        SceneLoader.getInstance().loadHelp();
    }

    @FXML
    void ingredientStorageClicked(ActionEvent event) {
        SceneLoader.getInstance().loadIngredientStorage();
    }

    @FXML
    void pauseButtonClicked(ActionEvent event) {
        SceneLoader.getInstance().loadPause();
    }

    @FXML
    void potionBrewingClicked(ActionEvent event) {
        SceneLoader.getInstance().loadPotionBrewing();
    }

    @FXML
    void publicationTrackClicked(ActionEvent event) {
        SceneLoader.getInstance().loadPublicationTrack();
    }

    @FXML
    void publishTheoryClicked(ActionEvent event) {
        SceneLoader.getInstance().loadPublishTheory();
    }

    @FXML
    void sellPotionClicked(ActionEvent event) {
        SceneLoader.getInstance().loadSellPotion();
    }

    @FXML
    void useArtifactClicked(ActionEvent event) {
        SceneLoader.getInstance().loadUseArtifact();
    }
    @FXML
    public void endRoundButtonClicked() {
        //check whether final round or not
        if (currentRound == 3 && currentTour == 3 && GameEngine.getInstance().getCurrentPlayerIndex() == GameEngine.getInstance().getPlayerList().size()-1) {
            SceneLoader.getInstance().loadFinalScoring();
        }else{
            changeRound();
        }
    }



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
        if (round == 1) {
            loadRound1();
        } else if (round == 2) {
            lodRound2();
        }

        //check whether the tour is last
        if(tour == 3) {
            endRoundButton.setEffect(new DropShadow(30, Color.WHITE));
        }else {
            endRoundButton.setEffect(null);
        }
        changeAvatars();

        //check whether there is a notification from wisdom idol
        boolean isThereWisdomIdolNotification = BoardHandler.getInstance().isThereWisdomIdolNotification();
        if (isThereWisdomIdolNotification) {
           HashMap<Player, ArrayList<Object>> notificationMap  = BoardHandler.getInstance().getNotificationMap();
            if(notificationMap.containsKey(GameEngine.getInstance().getCurrentPlayer())){
                SceneLoader.getInstance().loadWisdomIdol();
            }
        }
    }

    private void changeAvatars() {
        int length = cardBoxList.size();
        if(length== 4){
            changeFourPlayerAvatars();
        }else if(length == 3){
            changeThreePlayerAvatars();
        }else{
            changeTwoPlayerAvatars();
        }
       }

    private void clearPanes() {
        avatar1Pane.getChildren().clear();
        avatar2Pane.getChildren().clear();
        avatar3Pane.getChildren().clear();
        avatar4Pane.getChildren().clear();
    }

    private void changeFourPlayerAvatars() {
        Integer currentPlayerIndex = GameEngine.getInstance().getCurrentPlayerIndex();

        clearPanes();

        avatar1Pane.getChildren().add(cardBoxList.get(currentPlayerIndex));
        avatar3Pane.getChildren().add(cardBoxList.get((currentPlayerIndex+1)%4));
        avatar4Pane.getChildren().add(cardBoxList.get((currentPlayerIndex+2)%4));
        avatar2Pane.getChildren().add(cardBoxList.get((currentPlayerIndex+3)%4));
    }

    private void changeThreePlayerAvatars() {
        Integer currentPlayerIndex = GameEngine.getInstance().getCurrentPlayerIndex();

        clearPanes();

        avatar1Pane.getChildren().add(cardBoxList.get(currentPlayerIndex));
        avatar3Pane.getChildren().add(cardBoxList.get((currentPlayerIndex+1)%3));
        avatar2Pane.getChildren().add(cardBoxList.get((currentPlayerIndex+2)%3));
    }

    private void changeTwoPlayerAvatars() {
        Integer currentPlayerIndex = GameEngine.getInstance().getCurrentPlayerIndex();

        clearPanes();

        avatar1Pane.getChildren().add(cardBoxList.get(currentPlayerIndex));
        avatar4Pane.getChildren().add(cardBoxList.get((currentPlayerIndex+1)%2));
    }

    private void disableButtons(Button button) {
        button.setDisable(true);
        button.setOpacity(0.5);
    }

    private void loadRound1() {
        disableButtons(sellPotionButton);
        disableButtons(publishTheoryButton);
        disableButtons(publicationTrackButton);
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
        activateButtons(publicationTrackButton);


    }

    private void loadRound3() {
    }

    public BoardController() {
        currentRound = -1;
    }

    @Override
    public void onPlayerStatusChanged(String status, int id) {

    }

    @Override
    public void onPlayerSicknessLevelChanged(int sicknessLevel, int id) {

    }

    @Override
    public void onPlayerReputationChanged(int reputation, int index) {
        Platform.runLater(() -> {
            // Assume playerIndex is available to determine which player's gold changed
            AvatarCardController controller = playerControllers.get(index);
            controller.setReputationPoint(reputation);
        });
    }

    @Override
    public void onPlayerGoldChanged(int newGold, int index) {
        Platform.runLater(() -> {
            // Assume playerIndex is available to determine which player's gold changed
            AvatarCardController controller = playerControllers.get(index);
            controller.setGoldPoint(newGold);
        });
    }

    @Override
    public void onPlayerActionPointsChanged(int actionPoints, int index) {
        Platform.runLater(() -> {
            // Assume playerIndex is available to determine which player's gold changed
            AvatarCardController controller = playerControllers.get(index);
            controller.setActionPoint(actionPoints);
        });

    }

    @Override
    public void onPlayerNameChanged(String name, int id) {

    }



    @FXML
    void mouseEnteredDeductionBoard(MouseEvent event) {
        deductionBoardButton.setEffect(new Glow(0.8));

    }

    @FXML
    void mouseEnteredPotionBrewing(MouseEvent event) {
        potionBewingButton.setEffect(new Glow(0.8));

    }

    @FXML
    void mouseEnteredPublicationTrack(MouseEvent event) {
        publicationTrackButton.setEffect(new Glow(0.8));

    }

    @FXML
    void mouseEnteredPublishTheory(MouseEvent event) {
        publishTheoryButton.setEffect(new Glow(0.8));

    }

    @FXML
    void mouseEnteredSellPotion(MouseEvent event) {
        sellPotionButton.setEffect(new Glow(0.8));

    }

    @FXML
    void mouseExitedPotionBrewing(MouseEvent event) {
        potionBewingButton.setEffect(null);

    }

    @FXML
    void mouseExitedPublicationTrack(MouseEvent event) {
        publicationTrackButton.setEffect(null);

    }

    @FXML
    void mouseExitedSellPotion(MouseEvent event) {
        sellPotionButton.setEffect(null);

    }

    @FXML
    void mouseExitedublishTheory(MouseEvent event) {
        publishTheoryButton.setEffect(null);

    }


    @FXML
    void mouseExitedDeductionBoard(MouseEvent event) {
        deductionBoardButton.setEffect(null);
    }
}
