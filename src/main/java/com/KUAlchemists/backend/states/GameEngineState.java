package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameEngineState extends State {
    // game engine attributes
    private CopyOnWriteArrayList<Player> playerArrayList;

    public GameEngineState(CopyOnWriteArrayList<Player> playerArrayList){
        this.playerArrayList = playerArrayList;
    }

    public CopyOnWriteArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    @Override
    public void update(StateUpdater stateUpdater) {
        stateUpdater.updateGameEngine(this);
    }
}
