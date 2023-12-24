package com.KUAlchemists.backend.managers;

import com.KUAlchemists.backend.enums.GameMode;

public class GameModeManager {
    private static GameModeManager instance = null;
    private GameMode gameMode;

    private GameModeManager() {
    }

    public static GameModeManager getInstance() {
        if (instance == null) {
            instance = new GameModeManager();
        }
        return instance;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }
}
