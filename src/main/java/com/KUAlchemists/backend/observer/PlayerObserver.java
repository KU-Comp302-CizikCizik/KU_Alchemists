package com.KUAlchemists.backend.observer;

public interface PlayerObserver extends Observer {
    void onPlayerStatusChanged(String status);
    void onPlayerSicknessLevelChanged(int sicknessLevel);
    void onPlayerReputationChanged(int reputation);
    void onPlayerGoldChanged(int gold);
    void onPlayerActionPointsChanged(int actionPoints);
    void onPlayerNameChanged(String name);
}
