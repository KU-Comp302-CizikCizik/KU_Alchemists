package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.GameMode;

public class NumberOfPlayerSelectHandler {


    private static NumberOfPlayerSelectHandler INSTANCE;

    private NumberOfPlayerSelectHandler(){

    }

    public static NumberOfPlayerSelectHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new NumberOfPlayerSelectHandler();
        }
        return INSTANCE;
    }

    public void setNumberOfPlayers(String numberOfPlayers){
        //String format: "1", "2", "3", "4", see GameMode class
        GameMode gameMode = GameMode.getGameModeByString(numberOfPlayers);
        GameEngine.getInstance().initializeGame(gameMode);

    }


}
