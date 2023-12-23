package com.KUAlchemists.backend.engine;

import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.GameRound;
import com.KUAlchemists.backend.enums.PlayerSeal;
import com.KUAlchemists.backend.exceptions.GameInitializationException;
import com.KUAlchemists.backend.handlers.DeductionBoardHandler;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.managers.SceneManager;
import com.KUAlchemists.backend.managers.StateManager;
import com.KUAlchemists.backend.models.*;

import java.util.ArrayList;
import java.util.Random;

public class GameInitializer {

    private static boolean isGameInitialized = false;
    private GameRound gameRound;

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
        gameRound = GameRound.FIRST_ROUND;
        initStateObservers();
        initEventObservers();
        initGameObjects();
        initAlchemicalOfIngredients();
        initPlayerAssets();
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
        ArrayList<Alchemical> possibleAlchemicals = new ArrayList<>();


        //RED = POSITIVE_BIG, GREEN = POSITIVE_BIG, BLUE = POSITIVE_BIG
        possibleAlchemicals.add(new Alchemical(Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG));
        possibleAlchemicals.add(new Alchemical(Aspect.NEGATIVE_SMALL,Aspect.POSITIVE_SMALL,Aspect.NEGATIVE_BIG));
        possibleAlchemicals.add(new Alchemical(Aspect.POSITIVE_BIG,Aspect.POSITIVE_SMALL,Aspect.NEGATIVE_SMALL));
        possibleAlchemicals.add(new Alchemical(Aspect.NEGATIVE_SMALL,Aspect.POSITIVE_BIG,Aspect.POSITIVE_SMALL));
        possibleAlchemicals.add(new Alchemical(Aspect.POSITIVE_SMALL,Aspect.NEGATIVE_SMALL,Aspect.POSITIVE_BIG));
        possibleAlchemicals.add(new Alchemical(Aspect.NEGATIVE_BIG,Aspect.NEGATIVE_BIG,Aspect.NEGATIVE_BIG));
        possibleAlchemicals.add(new Alchemical(Aspect.NEGATIVE_BIG,Aspect.NEGATIVE_SMALL,Aspect.POSITIVE_SMALL));
        possibleAlchemicals.add(new Alchemical(Aspect.POSITIVE_SMALL,Aspect.NEGATIVE_BIG,Aspect.NEGATIVE_SMALL));

        ArrayList<Ingredient> ingredientsList = Board.getInstance().getDeck().getInstance().getIngredientsList();
        Random rand = new Random();

        int length = possibleAlchemicals.size();
        for(int i =0;i<ingredientsList.size();i++){
            int index = rand.nextInt(length);
            ingredientsList.get(i).setAlchemical(possibleAlchemicals.get(index));
            possibleAlchemicals.remove(index);
            length--;
        }

        Board.getInstance().getDeck().getInstance().setIngredientList(ingredientsList);

    }




}
