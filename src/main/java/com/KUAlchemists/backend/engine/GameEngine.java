package com.KUAlchemists.backend.engine;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;

public class GameEngine {

    // singleton instance
    public static GameEngine INSTANCE;

    // player list
    private static final ArrayList<Player> playerList = new ArrayList<>();

    // current player
    private static Player currentPlayer;

    // current player index
    private static int currentPlayerIndex = 0;

    private static final Board board = Board.getInstance();

    /**
     * Constructor for GameEngine
     */
    private GameEngine(){

    }

    /**
     * Get the singleton instance of GameEngine
     * @return the singleton instance of GameEngine
     */
    public static GameEngine getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new GameEngine();
        }
        return INSTANCE;
    }


    /**
     * Add a player to the player list
     * @param player the player to be added
     */
    public static void addPlayer(Player player){
        playerList.add(player);
    }
    /**
    * Remove a player from the player list
    * @param player the player to be removed
    */
    public static void removePlayer(Player player){
        playerList.remove(player);
    }

    /**
     * Get the player list
     * @return playerList
     */

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Get the current player
     * @return the current player
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Set the current player
     * @param currentPlayer the player to be set as current player
     */
    public static void setCurrentPlayer(Player currentPlayer) {
        GameEngine.currentPlayer = currentPlayer;
    }

    /**
     * Get the current player index
     * @return the current player index
     */
    public static int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Set the current player index
     * @param currentPlayerIndex the index to be set as current player index
     */
    public static void setCurrentPlayerIndex(int currentPlayerIndex) {
        GameEngine.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * Get the next player
     * @return the next player
     */
    public static void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % playerList.size();
        currentPlayer = playerList.get(currentPlayerIndex);
        //TODO: update Board UI with new currentPlayer
    }

    public static Player getPlayer(int index){
        return playerList.get(index);
    }

}
