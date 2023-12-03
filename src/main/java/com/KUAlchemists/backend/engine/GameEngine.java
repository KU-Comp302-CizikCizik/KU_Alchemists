package com.KUAlchemists.backend.engine;

import com.KUAlchemists.backend.enums.GameRound;
import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.ui.LoginPageUI;
import com.KUAlchemists.ui.MainGameUI;
import javafx.application.Application;

import java.util.ArrayList;

public class GameEngine {

    // singleton instance
    public static GameEngine Instance;

    // player list that has initially two Player objects
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
        Player player1 = new Player();
        Player player2 = new Player();
        playerList.add(player1);
        playerList.add(player2);
        Gamestate.gamestate = Gamestate.LOGIN;
        update();
    }

    /**
     * Get the singleton instance of GameEngine
     * @return the singleton instance of GameEngine
     */
    public static GameEngine getInstance(){
        if(Instance == null){
            Instance = new GameEngine();
        }
        return Instance;
    }

    /**
     * Update the game state
     */
    private void update() {
        switch (Gamestate.gamestate){
            case LOGIN:
                Application.launch(LoginPageUI.class);
                break;
            case MENU:
                break;
            case GAME:
                Application.launch(MainGameUI.class);
                break;
            case DASHBOARD:
                break;
            case INVENTORY:
                break;
            case POTION_BREWING:
                break;
            case PUBLICATION:
                break;
            case DEDUCTION:
                break;
            case DEBUNK:
                break;
            case GAME_LOG:
                break;
            case ENDGAME:
                break;

        }
    }

    /**
     * Update the game state
     * @param gamestate the game state to be updated
     */
    public void update(Gamestate gamestate){
        Gamestate.gamestate = gamestate;
        update();
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

    public static Player getPlayer(String name){
        for (Player player : playerList) {
            if (name.equals(player.getName())) {
                return player;
            }
        }
        return null;
    }

}
