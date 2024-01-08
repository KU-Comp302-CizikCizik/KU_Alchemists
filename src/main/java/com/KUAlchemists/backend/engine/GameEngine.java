package com.KUAlchemists.backend.engine;

import com.KUAlchemists.backend.enums.*;
import com.KUAlchemists.backend.enums.ApplicationMode;
import com.KUAlchemists.backend.enums.GameMode;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.states.GameEngineState;
import com.KUAlchemists.backend.states.State;

import java.util.ArrayList;

public class GameEngine {

    // singleton instance
    private static GameEngine INSTANCE;


    // player list that has initially two Player objects
    private static final ArrayList<Player> playerList = new ArrayList<>();

    private ApplicationMode mode; // OFFLINE or ONLINE

    private GameRound currentRound;
    private GameTour currentTour;

    // current player
    private static Player currentPlayer;

    // current player index
    private int currentPlayerIndex = 0;

    private GameMode currentGameMode;


    private State state;

    /**
     * Constructor for GameEngine
     */
    private GameEngine(){
        currentRound = GameRound.FIRST_ROUND;
        currentTour = GameTour.FIRST_TOUR;
    }

    /**
     * Get the singleton instance of GameEngine
     * @return the singleton instance of GameEngine
     */
    public static GameEngine getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GameEngine();
        }
        return INSTANCE;
    }


    /**
     * Add a player to the player list
     * @param player the player to be added
     */
    public void addPlayer(Player player){
        playerList.add(player);
    }
    /**
    * Remove a player from the player list
    * @param player the player to be removed
    */
    public void removePlayer(Player player){
        playerList.remove(player);
    }

    /**
     * Get the player list
     * @return playerList
     */

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Get the current player
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayer(int index){
        return playerList.get(index);
    }

    /**
     * Set the current player
     * @param player the player to be set as current player
     */
    public void setCurrentPlayer(Player player) {
        playerList.set(currentPlayerIndex, player);
        GameEngine.currentPlayer = player;
    }

    /**
     * Get the current player index
     * @return the current player index
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Set the current player index
     * @param currentPlayerIndex the index to be set as current player index
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * Get the next player
     * @return the next player
     */
    public void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % playerList.size();
        currentPlayer = playerList.get(currentPlayerIndex);
    }
    

    public Player getPlayer(String name){
        for (Player player : playerList) {
            if (name.equals(player.getName())) {
                return player;
            }
        }
        return null;
    }

    /**
     * Proceed with the next tour
     * @return the next tour
     */
    public ArrayList<Integer> nextTour() {
        ArrayList<Integer> round_tour_info = new ArrayList<>();
        round_tour_info.add(currentRound.getRound());
        round_tour_info.add(currentTour.getTour());
        nextPlayer();
        //if it is not the first player, do not proceed, all player should play their turns/tours
        if(GameEngine.getInstance().getCurrentPlayerIndex() != 0)return round_tour_info;

        if (currentTour == GameTour.THIRD_TOUR) {
            nextRound();
            currentTour = GameTour.FIRST_TOUR;
        }
        else{
            currentTour = GameTour.getNextTour(currentTour);
        }
        round_tour_info.set(0,currentRound.getRound());
        round_tour_info.set(1,currentTour.getTour());
        return round_tour_info;

    }

    /**
     * Proceed with the next round
     */
    public void nextRound(){
        if(currentRound == GameRound.THIRD_ROUND){
            return;
        }
        currentRound = GameRound.getNextRound(currentRound);
    }

    // application mode methods

    /**
     * Get the application mode
     * @return the application mode
     */
    public ApplicationMode getApplicationMode() {
        return mode;
    }

    /**
     * Set the application mode
     * @param mode the application mode to be set
     */

    public void setApplicationMode(ApplicationMode mode) {
        this.mode = mode;
    }



    public Integer getPlayerIndex(Player player) {
        return playerList.indexOf(player);
    }

    public GameMode getCurrentGameMode() {
        return currentGameMode;

    }

    public void setUserTypeOfCurrentPlayer(UserType type){
        currentPlayer.setUserType(type);
    }

    public UserType getUserType() {
        UserType userType = currentPlayer.getUserType();
        return userType;
    }

    public void setGameMode(GameMode gameMode) {
        currentGameMode = gameMode;
    }

    public void setGameRound(GameRound gameRound) {
        currentRound = gameRound;
    }

    public void setGameTour(GameTour gameTour) {
        currentTour = gameTour;
    }

    public State getState() {
        return new GameEngineState(playerList);
    }
}
