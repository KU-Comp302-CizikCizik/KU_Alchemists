package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.GameMode;
import com.KUAlchemists.backend.initializers.OfflineGameInitializer;
import com.KUAlchemists.backend.initializers.OfflineInitializer;

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
        OfflineGameInitializer offlineGameInitializer = new OfflineGameInitializer(gameMode); //we are not going to need to access this object again

    }


}
