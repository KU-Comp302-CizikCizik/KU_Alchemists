package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;

public class BoardHandler {

    public String forageIngredientPopUp() {
        System.out.println("Forage ingredient button pressed");

        //this method should return drawen ingredient
        return "birdfeet";
    }
    public void endTheRound() {
        GameEngine.getInstance().nextPlayer();
        System.out.println(GameEngine.getInstance().getCurrentPlayerIndex());
    }
}
