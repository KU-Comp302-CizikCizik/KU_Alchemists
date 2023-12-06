package com.KUAlchemists.backend.engine;

import com.KUAlchemists.backend.exceptions.GameInitializationException;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
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
        initPlayerAssets();
        initAlchemicalOfIngredients();
        initDeductionBoard();
    }

    private void initAlchemicalOfIngredients() {


    }


    private void initPlayerAssets() {
        for(Player player : GameEngine.getInstance().getPlayerList()){
            player.setGold(10);
            ForageForIngredientHandler.getInstance().forageForIngredient(player);
            ForageForIngredientHandler.getInstance().forageForIngredient(player);
        }
        
    }

    private void initGameObjects() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameEngine.getInstance().addPlayer(player1);
        GameEngine.getInstance().addPlayer(player2);

    }

    private void initStateObservers() {
        StateManager.getInstance().registerStateObserver(SceneManager.getInstance());
    }


    private void initDeductionBoard(){


    }




}
