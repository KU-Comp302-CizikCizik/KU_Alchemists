package com.KUAlchemists.backend.managers;


import com.KUAlchemists.backend.enums.GameStatus;
import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.GameStatusObserver;
import com.KUAlchemists.backend.observer.GameTurnObserver;
import com.KUAlchemists.backend.observer.PotionBrewingObserver;
import com.KUAlchemists.backend.observer.PublicationTrackObserver;
import com.KUAlchemists.backend.observer.OnlinePlayersUpdateObserver;
import com.KUAlchemists.backend.subjects.*;
import javafx.application.Platform;

public class EventManager {


    private static EventManager instance;

    private PotionBrewingData potionBrewingData;

    private PublicationTrackData publicationTrackData;

    private GameStatusData gameStatusData;

    private GameTurnData gameTurnData;

    private OnlinePlayersUpdateData onlinePlayersUpdateData;

    /**
     * This method is used to get the instance of the class.
     * @return
     */
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    /**
     * Constructor for the class.
     */
    private EventManager() {
        potionBrewingData = new PotionBrewingData();
        publicationTrackData = new PublicationTrackData();
        gameStatusData = new GameStatusData();
        gameTurnData = new GameTurnData();
        onlinePlayersUpdateData = new OnlinePlayersUpdateData();
    }

    /**
     * This method is used to notify the observers when a potion is brewed.
     * @param potion
     */
    private void notifyPotionBrewingObservers(Potion potion){
        potionBrewingData.onPotionBrewingActionPerformed(potion);
    }

    /**
     * This method is used to notify the observers when a potion is brewed.
     * @param potion
     */
    public void onPotionBrewingPerformed(Potion potion){
        notifyPotionBrewingObservers(potion);
    }

    /**
     * This method is used to register an observer.
     * @param potionBrewingObserver
     */
    public void registerPotionBrewingObserver(PotionBrewingObserver potionBrewingObserver){
        potionBrewingData.registerObserver(potionBrewingObserver);
    }

    /**
     * This method is used to remove an observer.
     * @param potionBrewingObserver
     */
    public void removePotionBrewingObserver(PotionBrewingObserver potionBrewingObserver){
        potionBrewingData.removeObserver(potionBrewingObserver);
    }


    /**
     * This method is used to notify the observers when a theory is selected.
     * @param publicationTrackObserver
     */
    public void registerPublicationTrackObserver(PublicationTrackObserver publicationTrackObserver){
        publicationTrackData.registerObserver(publicationTrackObserver);
    }

    /**
     * This method is used to remove an observer.
     * @param publicationTrackObserver
     */
    public void removePublicationTrackObserver(PublicationTrackObserver publicationTrackObserver){
        publicationTrackData.removeObserver(publicationTrackObserver);
    }

    /**
     * This method is used to notify the observers when a theory is selected.
     * @param theory
     */
    private void notifyPublicationTrackObservers(Theory theory){
        publicationTrackData.onTheorySelectedPerformed(theory);
    }

    /**
     * This method is used to notify the observers when a theory is selected.
     * @param theory
     */
    public void onTheorySelectedPerformed(Theory theory){
        notifyPublicationTrackObservers(theory);
    }



    /**
     * This method is used to notify the observers when a game status is changed.
     * @param gameStatus
     */
    private void notifyGameStatusObservers(GameStatus gameStatus) {
        gameStatusData.onGameStatusChanged(gameStatus);
    }

    /**
     * This method is used to notify the observers when a game status is changed.
     * @param gameStatus
     */
    public void onGameStatusChanged(GameStatus gameStatus) {
        Platform.runLater(() -> notifyGameStatusObservers(gameStatus));

    }

    /**
     * This method is used to register an observer.
     * @param gameStatusObserver
     */
    public void registerGameStatusObserver(GameStatusObserver gameStatusObserver) {
        gameStatusData.registerObserver(gameStatusObserver);
    }

    /**
     * This method is used to remove an observer.
     * @param gameStatusObserver
     */
    public void removeGameStatusObserver(GameStatusObserver gameStatusObserver) {
        gameStatusData.removeObserver(gameStatusObserver);
    }

    /**
     * This method is used to notify the observers when a game turn is changed.
     * @param gameTurn
     */
    private void notifyGameTurnObservers(int gameTurn) {
        gameTurnData.onGameTurnChangedPerformed(gameTurn);
    }

    /**
     * This method is used to notify the observers when a game turn is changed.
     * @param gameTurn
     */
    public void onGameTurnChanged(int gameTurn) {
        notifyGameTurnObservers(gameTurn);
    }

    /**
     * This method is used to register an observer.
     * @param gameTurnObserver
     */
    public void registerGameTurnObserver(GameTurnObserver gameTurnObserver) {
        gameTurnData.registerObserver(gameTurnObserver);
    }

    /**
     * This method is used to remove an observer.
     * @param gameTurnObserver
     */
    public void removeGameTurnObserver(GameTurnObserver gameTurnObserver) {
        gameTurnData.removeObserver(gameTurnObserver);
    }

    /**
     * This method is used to notify the observers when a player is updated.
     */
    public void registerOnlinePlayersUpdateObserver(OnlinePlayersUpdateObserver onlinePlayersUpdateObserver){
        onlinePlayersUpdateData.registerObserver(onlinePlayersUpdateObserver);
    }

    /**
     * This method is used to remove an observer.
     * @param onlinePlayersUpdateObserver
     */
    public void removeOnlinePlayersUpdateObserver(OnlinePlayersUpdateObserver onlinePlayersUpdateObserver){
        onlinePlayersUpdateData.removeObserver(onlinePlayersUpdateObserver);
    }

    /**
     * This method is used to notify the observers when a player is updated.
     */
    private void notifyOnlinePlayersUpdateObservers() {
        onlinePlayersUpdateData.updateOnlinePlayers();
    }

    /**
     * This method is used to notify the observers when a player is updated.
     */
    public void onUpdateOnlinePlayers() {
        notifyOnlinePlayersUpdateObservers();
    }





}
