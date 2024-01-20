package com.KUAlchemists.backend.initializers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.GameMode;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.*;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.*;
import com.KUAlchemists.backend.network.NetworkHandler;
import com.KUAlchemists.ui.controllers.WaitingRoomController;

import java.util.ArrayList;
import java.util.Random;

public class OnlineGameInitializer implements OnlineInitializer{

    @Override
    public void onlineInitialize(int port, String ipAddress, UserType userType) {
        GameEngine.getInstance().setGameMode(GameMode.ONLINE_GAME);
        initGame();
        initNetwork(port, ipAddress,userType);

    }

    private void initNetwork(int port, String ipAddress, UserType userType) {
        if(userType == UserType.HOST){
            startServer(port);
        }
        else if(userType == UserType.CLIENT){
            connectServer(port, ipAddress);
        }
        else{
            System.err.println("Error: Invalid user type");
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


    public void startServer(int port){
        NetworkHandler.getInstance().handleStartServer(port);
        GameEngine.getInstance().getCurrentPlayer().setIDInitializedbyHost(true);
    }

    public void connectServer(int port, String ipAddress){
        NetworkHandler.getInstance().handleConnect(ipAddress, port);
        GameEngine.getInstance().getCurrentPlayer().setIDInitializedbyHost(false); // client's id is not initialized by host yet
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
       Player player = new Player();
       player.setPlayerID(0);
       GameEngine.getInstance().addPlayer(player);
       GameEngine.getInstance().setCurrentPlayer(player);

    }

    private void initPlayerAssets() {
        GameEngine.getInstance().getCurrentPlayer().setGold(10);
        ForageForIngredientHandler.getInstance().forageForIngredient(GameEngine.getInstance().getCurrentPlayer());
        ForageForIngredientHandler.getInstance().forageForIngredient(GameEngine.getInstance().getCurrentPlayer());

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
            for(int j =0;j<3;j++) {
                ingredientsList.get(i).setAlchemical(possibleAlchemicals.get(index));
                i++;
            }
            possibleAlchemicals.remove(index);
            length--;
        }

        Board.getInstance().getDeck().getInstance().setIngredientList(ingredientsList);

    }




}
