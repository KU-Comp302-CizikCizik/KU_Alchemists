package com.KUAlchemists.backend.handlers;

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
        //String format: "1", "2", "3", "4"
        System.out.println("Number of players: " + numberOfPlayers);
        System.out.println("Game mode: " + GameMode.getGameModeByString(numberOfPlayers) + " player mode");

    }


}
