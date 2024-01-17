package com.KUAlchemists.backend.observer;

public interface PlayerObserver extends Observer {
    void onPlayerStatusChanged(String status, int id);
    void onPlayerSicknessLevelChanged(int sicknessLevel, int id);
    void onPlayerReputationChanged(int reputation, int id);
    void onPlayerGoldChanged(int gold, int id);
    void onPlayerActionPointsChanged(int actionPoints, int id);
    void onPlayerNameChanged(String name, int id);
}
