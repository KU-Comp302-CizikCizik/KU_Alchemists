package com.KUAlchemists.backend.engine;

import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.ui.LoginPageUI;
import com.KUAlchemists.ui.MainGameUI;
import javafx.application.Application;

import java.util.ArrayList;

public class GameEngine {

    // singleton instance
    public static GameEngine Instance;

    // player list
    private static ArrayList<Player> playerList = new ArrayList<>();

    // current player
    private static Player currentPlayer;

    // current player index

    private static int currentPlayerIndex = 0;

    //TO-DO: Add switching player mechanism

    /**
     * Constructor for GameEngine
     */
    private GameEngine(){
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

}
