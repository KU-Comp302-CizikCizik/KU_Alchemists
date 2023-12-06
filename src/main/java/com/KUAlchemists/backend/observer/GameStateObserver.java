package com.KUAlchemists.backend.observer;

import com.KUAlchemists.backend.enums.Gamestate;

public interface GameStateObserver {

    void onGameStateChange(Gamestate gameState);
}
