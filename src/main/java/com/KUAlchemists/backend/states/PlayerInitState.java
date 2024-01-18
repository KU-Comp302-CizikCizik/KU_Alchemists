package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.models.Player;

public class PlayerInitState extends State {

    private Player player;

    public PlayerInitState(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }
    @Override
    public void update(StateUpdater stateUpdater) {
        stateUpdater.updatePlayerInit(this);
    }
}
