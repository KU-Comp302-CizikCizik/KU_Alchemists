package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.GameMode;
import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.managers.GameModeManager;
import com.KUAlchemists.backend.managers.StateManager;

public class GameModeService {

        public GameModeService() {
        }
        public void setGameMode(Integer gameMode) {
            // TWO_PLAYER, THREE_PLAYER, FOUR_PLAYER
            switch (gameMode) {
                case 0:
                    GameModeManager.getInstance().setGameMode(GameMode.TWO_PLAYER);
                    break;
                case 1:
                    GameModeManager.getInstance().setGameMode(GameMode.THREE_PLAYER);
                    break;
                case 2:
                    GameModeManager.getInstance().setGameMode(GameMode.FOUR_PLAYER);
                    break;
            }
        }

    public Integer getGameMode() {
        switch (GameModeManager.getInstance().getGameMode()) {
            case TWO_PLAYER:
                return 0;
            case THREE_PLAYER:
                return 1;
            case FOUR_PLAYER:
                return 2;
        }
        return null;
    }
}
