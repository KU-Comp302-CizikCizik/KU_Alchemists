package com.KUAlchemists.backend.engine;

import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;

public class GameEngine {

    // singleton instance
    private static GameEngine INSTANCE;

    // player list that has initially two Player objects
    private static final ArrayList<Player> playerList = new ArrayList<>();



    // current player
    private static Player currentPlayer;

    // current player index
    private static int currentPlayerIndex = 0;


    /**
     * Constructor for GameEngine
     */
    private GameEngine(){

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

    /**
     * Set the current player
     * @param currentPlayer the player to be set as current player
     */
    public void setCurrentPlayer(Player currentPlayer) {
        GameEngine.currentPlayer = currentPlayer;
    }

    public void updateCurrentPlayer(Player player){
        playerList.set(currentPlayerIndex, player);
        currentPlayer = player;
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
        GameEngine.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * Get the next player
     * @return the next player
     */
    public void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % playerList.size();
        currentPlayer = playerList.get(currentPlayerIndex);
        //TODO: update Board UI with new currentPlayer
    }

    public Player getPlayer(int index){
        return playerList.get(index);
    }

    public Player getPlayer(String name){
        for (Player player : playerList) {
            if (name.equals(player.getName())) {
                return player;
            }
        }
        return null;
    }

}
