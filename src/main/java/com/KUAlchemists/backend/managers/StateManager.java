package com.KUAlchemists.backend.managers;

import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.observer.GameStateObserver;
import com.KUAlchemists.backend.subjects.GameStateData;

import java.util.ArrayList;

public class StateManager {

    // Singleton
    private static StateManager Instance;

    private GameStateData gameStateData;

    /**
     * Get the instance of the class.
     * @return the instance of the class
     */
    public static StateManager getInstance() {
        if (Instance == null) {
            Instance = new StateManager();
        }
        return Instance;
    }

    /**
     * Constructor for the class.
     */
    private StateManager(){
        gameStateData = new GameStateData();
    }

    /**
     * Update the game state
     * @param gamestate the game state to be updated
     */
    public void updateGameState(Gamestate gamestate){
        gameStateData.onGameStateChanged(gamestate);
    }


    /**
     * Register a game state observer
     * @param gameStateObserver the observer to be registered
     */
    public void registerStateObserver(GameStateObserver gameStateObserver){
        gameStateData.registerObserver(gameStateObserver);
    }

    /**
     * Remove a game state observer
     * @param gameStateObserver the observer to be removed
     */
    public void removeStateObserver(GameStateObserver gameStateObserver){
        gameStateData.removeObserver(gameStateObserver);
    }



}
