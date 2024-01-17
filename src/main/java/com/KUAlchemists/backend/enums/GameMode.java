package com.KUAlchemists.backend.enums;

public enum GameMode {
    TWO_PLAYER(2),
    THREE_PLAYER(3),
    FOUR_PLAYER(4),

    ONLINE_GAME(1);

    private int value;

    GameMode(int value){
        this.value = value;
    }

    public int getNumberOfPlayers(){
        return value;
    }

    public String getNumberOfPlayerString(){
        return Integer.toString(value);
    }

    public static GameMode getGameModeByString(String numberOfPlayers){
        for(GameMode mode : GameMode.values()){
            if(mode.getNumberOfPlayerString().equals(numberOfPlayers)){
                return mode;
            }
        }
        throw new EnumConstantNotPresentException(GameMode.class, numberOfPlayers);
    }


}