package com.KUAlchemists.backend.observer;

import com.KUAlchemists.backend.enums.Gamestate;

public interface GameStateObserver extends Observer{

    void onGameStateChanged(Gamestate gameState);
}
