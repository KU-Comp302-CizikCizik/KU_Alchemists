package com.KUAlchemists.backend.engine;

import com.KUAlchemists.backend.exceptions.GameInitializationException;
import com.KUAlchemists.backend.managers.SceneManager;
import com.KUAlchemists.backend.managers.StateManager;
import com.KUAlchemists.backend.models.Player;

public class GameInitializer {

    private static boolean isGameInitialized = false;

    public GameInitializer() {
        if (!isGameInitialized) {
            isGameInitialized = true;
            initGame();
        }
        else{
            try {
                throw new GameInitializationException("Game is already initialized");
            } catch (GameInitializationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initGame() {
        initStateObservers();
        initGameObjects();
    }

    private void initGameObjects() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameEngine.getINSTANCE().addPlayer(player1);
        GameEngine.getINSTANCE().addPlayer(player2);

    }

    private void initStateObservers() {
        StateManager.getInstance().registerStateObserver(SceneManager.getInstance());


    }




}
