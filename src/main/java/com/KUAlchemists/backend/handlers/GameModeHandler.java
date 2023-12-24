package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.services.GameModeService;

public class GameModeHandler {
    private GameModeService gameModeService;
    private static GameModeHandler INSTANCE = null;
    private GameModeHandler() {
        gameModeService = new GameModeService();
    }
    public static GameModeHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameModeHandler();
        }
        return INSTANCE;
    }

    /*
    * This method is called when a player wants to set the game mode.
    * @param gameMode the game mode to be set (0 for two players, 1 for three players, 2 for four players)
     */
    public void setGameMode(Integer gameMode) {
        gameModeService.setGameMode(gameMode);
    }

    public Integer getGameMode() {
        return gameModeService.getGameMode();
    }
}
