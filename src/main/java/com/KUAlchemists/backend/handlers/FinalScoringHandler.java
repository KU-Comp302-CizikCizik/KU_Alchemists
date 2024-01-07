package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.ui.controllers.FinalScoringController;

public class FinalScoringHandler {
    private static FinalScoringHandler INSTANCE;

    public static FinalScoringHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new FinalScoringHandler();
        }
        return INSTANCE;
    }

    public String[] handlerGetPlayerFinalPosition(){
        return new String[]{"player1", "player2"};
    }
}
