package com.KUAlchemists.backend.observer;

import com.KUAlchemists.backend.enums.GameStatus;

public interface GameStatusObserver extends Observer {

    void onGameStatusChanged(GameStatus status);

}
