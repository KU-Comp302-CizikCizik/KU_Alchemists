package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.enums.GameStatus;

public class GameStatusState extends State {

    private GameStatus status;

    public GameStatusState(GameStatus status){
        this.status = status;
    }
    @Override
    public void update(StateUpdater stateUpdater) {
        stateUpdater.updateGameStatus(this);
    }

    public GameStatus getStatus() {
        return status;
    }
}
