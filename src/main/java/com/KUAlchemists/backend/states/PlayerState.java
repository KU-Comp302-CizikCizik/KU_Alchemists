package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.models.Player;

public class PlayerState extends State {
    // player attributes

    private int gold;
    private int id;
    private UserType userType;

    public PlayerState(int id, int gold, UserType userType){
        this.id = id;
        this.gold = gold;
        this.userType = userType;
    }

    public int getGold(){
        return gold;
    }

    public int getId() {
        return id;
    }

    @Override
    public void update(StateUpdater stateUpdater) {
        stateUpdater.updatePlayer(this);
    }
}
