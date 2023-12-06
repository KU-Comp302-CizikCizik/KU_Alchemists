package com.KUAlchemists.backend.engine;

import com.KUAlchemists.backend.exceptions.GameInitializationException;
import com.KUAlchemists.backend.handlers.DeductionBoardHandler;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
import com.KUAlchemists.backend.managers.EventManager;
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
        initEventObservers();
        initGameObjects();
        initPlayerAssets();
        initAlchemicalOfIngredients();
        initDeductionBoard();
    }

    private void initEventObservers() {
        EventManager.getInstance().registerPotionBrewingObserver(DeductionBoardHandler.getInstance());
    }


    private void initGameObjects() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameEngine.getInstance().addPlayer(player1);
        GameEngine.getInstance().addPlayer(player2);
        GameEngine.getInstance().setCurrentPlayer(player1);
    }

    private void initPlayerAssets() {
        for(Player player : GameEngine.getInstance().getPlayerList()){
            player.setGold(10);
            ForageForIngredientHandler.getInstance().forageForIngredient(player);
            ForageForIngredientHandler.getInstance().forageForIngredient(player);
        }

    }


    private void initStateObservers() {
        StateManager.getInstance().registerStateObserver(SceneManager.getInstance());
    }


    private void initAlchemicalOfIngredients() {



    }


    private void initDeductionBoard(){


    }




}
