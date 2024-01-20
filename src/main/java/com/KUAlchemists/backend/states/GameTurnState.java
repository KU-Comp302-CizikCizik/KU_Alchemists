package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.subjects.GameTurnData;

public class GameTurnState extends State {

    private int gameTurn;

    public GameTurnState(int gameTurn) {
        this.gameTurn = gameTurn;
    }
    @Override
    public void update(StateUpdater stateUpdater) {
        stateUpdater.updateGameTurn(this);
    }

    public int getGameTurn() {
        return gameTurn;
    }
}
