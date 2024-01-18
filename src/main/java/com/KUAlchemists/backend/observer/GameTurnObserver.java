package com.KUAlchemists.backend.observer;

public interface GameTurnObserver extends Observer{

    void onGameTurnChanged(int id);
}
