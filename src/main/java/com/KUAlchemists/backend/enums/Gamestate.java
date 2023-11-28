package com.KUAlchemists.backend.enums;

/**
 * This enum represents the different states of the game.
 */
public enum Gamestate {

    LOGIN,
    GAME,
    DASHBOARD,
    INVENTORY,
    MIXING_INGREDIENT,
    PUBLICATION,
    DEDUCTION,
    DEBUNK,
    GAME_LOG,
    ENDGAME;


    public static Gamestate gamestate = LOGIN;

}

