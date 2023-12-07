package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;

public class BoardHandler {


    private static BoardHandler INSTANCE = null;

    private BoardHandler() {
    }

    public static BoardHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardHandler();
        }
        return INSTANCE;
    }

    public void forageIngredientPopUp() {
        System.out.println("Forage ingredient button pressed");
    }
    public void endTheRound() {
        GameEngine.getInstance().nextPlayer();
    }
}
