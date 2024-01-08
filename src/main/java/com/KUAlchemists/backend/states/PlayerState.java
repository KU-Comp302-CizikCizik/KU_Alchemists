package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.states.State;

public class PlayerState extends State {
    // player attributes

    private int gold;
    private int id;
    private UserType userType;

    private Player player;

    public PlayerState(int id, int gold, UserType userType, Player player){
        this.id = id;
        this.gold = gold;
        this.userType = userType;
        this.player = player;
    }

    public int getGold(){
        return gold;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update(StateUpdater stateUpdater) {
        stateUpdater.updatePlayer(this);
    }
}
