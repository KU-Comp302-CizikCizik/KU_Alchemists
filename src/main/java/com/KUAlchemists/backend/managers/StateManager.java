package com.KUAlchemists.backend.managers;

import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.observer.GameStateObserver;

import java.util.ArrayList;

public class StateManager {

    // Singleton
    private static StateManager Instance;


    private static ArrayList<GameStateObserver> gameStateObserverArrayList = new ArrayList<>();


    public static StateManager getInstance() {
        if (Instance == null) {
            Instance = new StateManager();
        }
        return Instance;
    }

    private StateManager(){

    }

    /**
     * Update the game state
     * @param gamestate the game state to be updated
     */
    public void updateGameState(Gamestate gamestate){
        notifyStateObservers(gamestate);
    }

    public void notifyStateObservers(Gamestate gamestate){
        for(GameStateObserver gameStateObserver : gameStateObserverArrayList){
            gameStateObserver.onGameStateChange(gamestate);
        }
    }


    public void registerStateObserver(GameStateObserver gameStateObserver){
        gameStateObserverArrayList.add(gameStateObserver);
    }

    public void unregisterStateObserver(GameStateObserver gameStateObserver){
        gameStateObserverArrayList.remove(gameStateObserver);
    }



}
