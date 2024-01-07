package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.models.Player;

import java.io.Serializable;

public class PlayerState extends State{
    // player attributes

    private int gold;
    private int id;

    public PlayerState(int id, int gold){
        this.id = id;
        this.gold = gold;
    }

    public int getGold(){
        return gold;
    }

    public int getId() {
        return id;
    }
}
