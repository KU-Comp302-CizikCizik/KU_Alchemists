package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.managers.StateManager;

public class GameModeService {

        public GameModeService() {
        }
        public void setGameMode(Integer gameMode) {
            if (gameMode.equals(0)) {
                StateManager.getInstance().updateGameState(Gamestate.SINGLEPLAYER);
            } else if (gameMode.equals(1)) {
                StateManager.getInstance().updateGameState(Gamestate.MULTIPLAYER);
            }
        }

}
