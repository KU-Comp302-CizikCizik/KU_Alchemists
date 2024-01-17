package com.KUAlchemists.backend.enums;

public enum GameRound {

    FIRST_ROUND(1),
    SECOND_ROUND(2),
    THIRD_ROUND(3),

    GAMEOVER_ROUND(4);

    /*  Constructor for GameRound enum
        @param round: the round number
    */
    public final int round;
    /*  Constructor for GameRound enum
        @param round: the round number
    */
    GameRound(int round) {
        this.round = round;
    }

    /*  Getter for round
        @return round: the round number
    */
    public int getRound() {
        return round;
    }


    public static GameRound getNextRound (GameRound currentRound){
        switch(currentRound){
            case FIRST_ROUND:
                return SECOND_ROUND;
            case SECOND_ROUND:
                return THIRD_ROUND;
            default:
                return null;
        }
    }
}
