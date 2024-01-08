package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.models.Player;

import java.io.Serializable;

public class PlayerState extends State{
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
}
