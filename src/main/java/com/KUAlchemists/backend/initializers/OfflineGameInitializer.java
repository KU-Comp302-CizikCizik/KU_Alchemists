package com.KUAlchemists.backend.initializers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.GameMode;
import com.KUAlchemists.backend.enums.GameRound;
import com.KUAlchemists.backend.enums.GameTour;
import com.KUAlchemists.backend.exceptions.GameInitializationException;
import com.KUAlchemists.backend.handlers.DebunkTheoryHandler;
import com.KUAlchemists.backend.handlers.DeductionBoardHandler;
import com.KUAlchemists.backend.handlers.EndorseHandler;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;
import java.util.Random;

public class OfflineGameInitializer implements OfflineInitializer{

    private int numberOfPlayers;
    private static boolean isOfflineGameInitialized = false;
    public OfflineGameInitializer(GameMode gameMode){
        GameEngine.getInstance().setGameMode(gameMode);
        numberOfPlayers = gameMode.getNumberOfPlayers();
        offlineInitialize();

    }
    @Override
    public void offlineInitialize() {
        if (!isOfflineGameInitialized) {
            isOfflineGameInitialized = true;
            GameEngine.getInstance().setGameRound(GameRound.FIRST_ROUND);
            GameEngine.getInstance().setGameTour(GameTour.FIRST_TOUR);
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
        initAlchemicalOfIngredients();
        initGameObjects();
        initBoardStorages();
        initPlayerAssets();

    }

    private void initBoardStorages() {
        Board.getInstance().createEmptyStoragesForAllPlayers();
    }


    private void initEventObservers() {
        EventManager.getInstance().registerPotionBrewingObserver(DeductionBoardHandler.getInstance());
        EventManager.getInstance().registerPublicationTrackObserver(EndorseHandler.getInstance());
        EventManager.getInstance().registerPublicationTrackObserver(DebunkTheoryHandler.getInstance());

    }


    private void initGameObjects() {
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player();
            GameEngine.getInstance().addPlayer(player);
        }
        GameEngine.getInstance().setCurrentPlayer(GameEngine.getInstance().getPlayerList().get(0));
    }

    private void initPlayerAssets() {
        for(Player player : GameEngine.getInstance().getPlayerList()){
            ForageForIngredientHandler.getInstance().forageForIngredient(player);
            ForageForIngredientHandler.getInstance().forageForIngredient(player);
        }

    }


    private void initStateObservers() {
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
            for(int j =0;j<3;j++){
                ingredientsList.get(i).setAlchemical(possibleAlchemicals.get(index));
                i++;
            }
            possibleAlchemicals.remove(index);

            length--;
        }

        Board.getInstance().getDeck().getInstance().setIngredientList(ingredientsList);

    }


}
