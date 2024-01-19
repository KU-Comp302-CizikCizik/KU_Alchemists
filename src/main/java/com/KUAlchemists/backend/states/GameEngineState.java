package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameEngineState extends State {
    // game engine attributes
    private ArrayList<Player> playerArrayList;

    public GameEngineState(ArrayList<Player> playerArrayList){
        this.playerArrayList = playerArrayList;
    }

    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    @Override
    public void update(StateUpdater stateUpdater) {
        stateUpdater.updateGameEngine(this);
    }
}
