package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;

public class BoardHandler {

    public void forageIngredientPopUp() {
        System.out.println("Forage ingredient button pressed");
    }
    public void endTheRound() {
        GameEngine.getInstance().nextPlayer();
        System.out.println(GameEngine.getInstance().getCurrentPlayerIndex());
    }
}
