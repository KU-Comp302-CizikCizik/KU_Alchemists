package com.KUAlchemists.ui.controllers;
import com.KUAlchemists.backend.engine.GameEngine;

import com.KUAlchemists.backend.enums.ApplicationMode;
import com.KUAlchemists.backend.enums.GameStatus;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.BoardHandler;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
import com.KUAlchemists.backend.handlers.SoundEffectHandler;
import com.KUAlchemists.backend.managers.EventManager;

import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.observer.GameStatusObserver;
import com.KUAlchemists.backend.observer.GameTurnObserver;
import com.KUAlchemists.backend.observer.OnlinePlayersUpdateObserver;
import com.KUAlchemists.backend.observer.PlayerObserver;
import com.KUAlchemists.backend.sound.SoundContrasts;
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
import java.util.Map;

public class BoardController  implements PlayerObserver, GameTurnObserver, GameStatusObserver, OnlinePlayersUpdateObserver {

    @FXML
    private AnchorPane avatar1Pane;

    @FXML
    private AnchorPane avatar2Pane;

    @FXML
    private AnchorPane avatar3Pane;

    @FXML
    private  AnchorPane avatar4Pane;

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

    @FXML
    private Button helpButton;

    @FXML
    private  Button pauseButton;

    @FXML
    private Button forageIngredientButton;

    @FXML
    private Button buyArtifactButton;

    @FXML
    private Button useArtifactButton;

    @FXML
    private Button ingredientStorageButton;


    private Integer currentRound;
    private Integer currentTour;;

    private ArrayList<AvatarCardController> playerControllers;

    private ArrayList<Pane> cardBoxList;


    @FXML
    void initialize() {
        EventManager.getInstance().registerGameTurnObserver(this);
        EventManager.getInstance().registerGameStatusObserver(this);
        BoardHandler.getInstance().registerPlayerObserver(this);
        EventManager.getInstance().registerOnlinePlayersUpdateObserver(this);

        //TO-DO: set the avatar cards
        playerControllers = new ArrayList<>();
        cardBoxList = new ArrayList<>();
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
        currentRound = 1;
        currentTour = 1;

        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            disableInteractions();
            enableInteractions();
        }
        else{
            disableInteractions();
            if(GameEngine.getInstance().getUserType() == UserType.HOST){
                enableInteractions();
            }
        }

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
        SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.PAGE_SOUND);

    }

    @FXML
    void deductionBoardClicked(ActionEvent event) {
        SceneLoader.getInstance().loadDeductionBoard();
        SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.PAGE_SOUND);
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
            SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.CLAIM_SOUND);
        }    }

    @FXML
    void helpButtonClicked(ActionEvent event) {
        SceneLoader.getInstance().loadHelp();
    }

    @FXML
    void ingredientStorageClicked(ActionEvent event) {
        SceneLoader.getInstance().loadIngredientStorage();
        SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.PAGE_SOUND);
    }

    @FXML
    void pauseButtonClicked(ActionEvent event) {
        SceneLoader.getInstance().loadPause();
    }

    @FXML
    void potionBrewingClicked(ActionEvent event) {
        SceneLoader.getInstance().loadPotionBrewing();
        SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.POTION_SOUND);
    }

    @FXML
    void publicationTrackClicked(ActionEvent event) {
        SceneLoader.getInstance().loadPublicationTrack();
        SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.PAGE_SOUND);
    }
    @FXML
    void publishTheoryClicked(ActionEvent event) {
        SceneLoader.getInstance().loadPublishTheory();
        SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.PAGE_SOUND);
    }

    @FXML
    void sellPotionClicked(ActionEvent event) {
        SceneLoader.getInstance().loadSellPotion();
        SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.COIN_SOUND);

    }

    @FXML
    void useArtifactClicked(ActionEvent event) {
        SceneLoader.getInstance().loadUseArtifact();
        SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.PAGE_SOUND);
    }
    @FXML
    public void endRoundButtonClicked() {
        //check whether final round or not
        if (isFinalRound() && isCurrentPlayerOrClientLast()){
            handleFinalRound();
        } else {
            handleNonFinalRound();
            SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.BOARD_FLIP);

        }
    }

    private boolean isFinalRound() {
        return currentRound == 3 && currentTour == 3;
    }

    private boolean isCurrentPlayerOrClientLast() {
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.ONLINE){
            return GameEngine.getInstance().getCurrentClientID() == getLastPlayerID();
        }
        else{
            return GameEngine.getInstance().getCurrentPlayerIndex() == getLastPlayerIndex();
        }
    }


    private void handleNonFinalRound() {
        if (GameEngine.getInstance().getApplicationMode() == ApplicationMode.ONLINE) {
            disableInteractions();
        }
        changeRound();
    }


    private void handleFinalRound() {
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.ONLINE){
            BoardHandler.getInstance().notifyOtherClientsForFinalScoring();
        }
        Platform.runLater(() -> SceneLoader.getInstance().loadFinalScoring());
    }


    private int getLastPlayerIndex() {
        return GameEngine.getInstance().getPlayerList().size() - 1;
    }

    private int getLastPlayerID() {
        return GameEngine.getInstance().getPlayerList().get(GameEngine.getInstance().getPlayerList().size() - 1).getId();
    }


    public void changeRound() {
        ArrayList<Integer> round_tour_info;
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            round_tour_info = BoardHandler.getInstance().endOfflineTour();
        }
        else{
            round_tour_info = BoardHandler.getInstance().endOnlineTour();
        }
        Integer round = round_tour_info.get(0);
        Integer tour = round_tour_info.get(1);
        currentRound = round;
        currentTour = tour;

        roundTitle.setText("Round " + round.toString());
        tourTitle.setText("Tour " + tour.toString());
        //For game over screen, we have extra variable GAMEOVER_ROUND(4);, check for round to be 4 or not
        //use for updating the UI
        //round_tour_info[0] = round
        //round_tour_info[1] = tour

        //check whether there is a notification from wisdom idol
        loadWisdomIdolNotificationIfExists();


        //check whether the tour is last
        if(tour == 3) {
            endRoundButton.setEffect(new DropShadow(30, Color.WHITE));
        }else {
            endRoundButton.setEffect(null);
        }

        changeAvatars();

        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            enableInteractions();
        }

    }

    private void loadWisdomIdolNotificationIfExists() {
        boolean isThereWisdomIdolNotification = BoardHandler.getInstance().isThereWisdomIdolNotification();

        if (isThereWisdomIdolNotification) {
            HashMap<Player, ArrayList<Object>> notificationMap  = BoardHandler.getInstance().getNotificationMap();
            if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
                if(notificationMap.containsKey(GameEngine.getInstance().getCurrentPlayer())){
                    SceneLoader.getInstance().loadWisdomIdol();
                    return;
                }
            }
            else{
                for(Map.Entry<Player, ArrayList<Object>> entry : notificationMap.entrySet()){
                    if(entry.getKey().getId() == GameEngine.getInstance().getCurrentPlayer().getId()){
                        SceneLoader.getInstance().loadWisdomIdol();
                        return;
                    }
                }
            }
        }
    }

    private void checkWisdomIdol(){
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
        int currentIndex = -1;
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.ONLINE){
            currentIndex = GameEngine.getInstance().getCurrentClientID();
        }
        else{
            currentIndex = GameEngine.getInstance().getCurrentPlayerIndex();
        }
        if(length== 4){
            changeFourPlayerAvatars(currentIndex);
        }else if(length == 3){
            changeThreePlayerAvatars(currentIndex);
        }else{
            changeTwoPlayerAvatars(currentIndex);
        }
       }

    private void clearPanes() {
        avatar1Pane.getChildren().clear();
        avatar2Pane.getChildren().clear();
        avatar3Pane.getChildren().clear();
        avatar4Pane.getChildren().clear();
    }

    private void changeFourPlayerAvatars(int currentIndex) {

        clearPanes();

        avatar1Pane.getChildren().add(cardBoxList.get(currentIndex));
        avatar3Pane.getChildren().add(cardBoxList.get((currentIndex+1)%4));
        avatar4Pane.getChildren().add(cardBoxList.get((currentIndex+2)%4));
        avatar2Pane.getChildren().add(cardBoxList.get((currentIndex+3)%4));
    }

    private void changeThreePlayerAvatars(int currentIndex) {

        clearPanes();

        avatar1Pane.getChildren().add(cardBoxList.get(currentIndex));
        avatar3Pane.getChildren().add(cardBoxList.get((currentIndex+1)%3));
        avatar2Pane.getChildren().add(cardBoxList.get((currentIndex+2)%3));
    }

    private void changeTwoPlayerAvatars(int currentIndex) {

        clearPanes();

        avatar1Pane.getChildren().add(cardBoxList.get(currentIndex));
        avatar4Pane.getChildren().add(cardBoxList.get((currentIndex+1)%2));
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

        KeyFrame endFrame = new KeyFrame(Duration.seconds(0.5), e -> {
            // Set the final opacity (1.0 for fully opaque)
            button.setOpacity(1.0);
        });

        KeyFrame startGlow = new KeyFrame(Duration.seconds(0.6), e -> {
            // Set the final opacity (1.0 for fully opaque)
            button.setEffect(new Glow(0.3));
        });

        KeyFrame endGlow = new KeyFrame(Duration.seconds(1), e -> {
            // Set the final opacity (1.0 for fully opaque)
            button.setEffect(null);
        });

        // Add the KeyFrames to the Timeline
        timeline.getKeyFrames().addAll(startFrame, endFrame,startGlow,endGlow);
        timeline.play();
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


    private void enableInteractions() {
        if(currentRound == 1){
            loadRound1();
        }
        else if(currentRound == 2){
            loadRound1();
            loadRound2();
        }
        else{
            loadRound1();
            loadRound2();
            loadRound3();
        }
        loadDefaultActions();

    }

    private void loadRound1() {
        enableButtons(forageIngredientButton);
        enableButtons(ingredientStorageButton);
        enableButtons(buyArtifactButton);
        enableButtons(potionBewingButton);
    }

    private void loadRound2() {
        activateButtons(sellPotionButton);
        activateButtons(publishTheoryButton);
        activateButtons(publicationTrackButton);

    }

    private void loadRound3() {

    }

    private void disableInteractions() {
        disableButtons(publishTheoryButton);
        disableButtons(publicationTrackButton);
        disableButtons(deductionBoardButton);
        disableButtons(potionBewingButton);
        disableButtons(sellPotionButton);
        disableButtons(pauseButton);
        disableButtons(ingredientStorageButton);
        disableButtons(buyArtifactButton);
        disableButtons(useArtifactButton);
        disableButtons(endRoundButton);
        disableButtons(forageIngredientButton);

    }

    private void loadDefaultActions() {
        //actions that are available regardless of the round
        enableButtons(ingredientStorageButton);
        enableButtons(useArtifactButton);
        enableButtons(endRoundButton);
        enableButtons(helpButton);
        enableButtons(pauseButton);
        enableButtons(deductionBoardButton);
    }

    private void enableButtons(Button button) {
        button.setDisable(false);
        button.setOpacity(1.0);
    }

    private void disableButtons(Button button) {
        button.setDisable(true);
        button.setOpacity(0.5);
    }

    @Override
    public void onGameTurnChanged(int id) {
        //Updat the avaliable actions & UI accordingly
        if(BoardHandler.getInstance().isItCurrentPlayerTurn()){
            //update the UI
            Platform.runLater(() -> {
                enableInteractions();
            });
        }

        //Platform.runLater(() -> EventManager.getInstance().registerOnlinePlayersUpdateObserver(this));

        Platform.runLater(() -> {
            changeAvatars();
        });

    }

    private void updateUI() {


    }


    @Override
    public void onGameStatusChanged(GameStatus status) {
        if(status == GameStatus.END_GAME){
           SceneLoader.getInstance().loadFinalScoring();
        }
    }

    @Override
    public void onUpdateOnlinePlayer() {
        BoardHandler.getInstance().removeAllPlayerObservers();
        BoardHandler.getInstance().registerPlayerObserver(this);
        for(int i =0;i<playerControllers.size();i++){
            playerControllers.get(i).setActionPoint(BoardHandler.getInstance().getPlayerActionPoints(i));
            playerControllers.get(i).setGoldPoint(BoardHandler.getInstance().getPlayerGold(i));
            playerControllers.get(i).setReputationPoint(BoardHandler.getInstance().getPlayerReputation(i));
        }
    }
}
