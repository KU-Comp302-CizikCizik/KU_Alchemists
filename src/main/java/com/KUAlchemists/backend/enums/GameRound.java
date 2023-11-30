package com.KUAlchemists.backend.enums;
/**
 * This enum represents the different rounds of the game. Depending on the round, different actions are available.
 */
public enum GameRound {

    FIRST_ROUND,
    SECOND_ROUND,
    THIRD_ROUND,
    SCOREBOARD;

    public static GameRound gameRound = FIRST_ROUND;
}
