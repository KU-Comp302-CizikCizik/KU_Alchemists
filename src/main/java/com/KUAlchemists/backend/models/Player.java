package com.KUAlchemists.backend.models;


import java.util.ArrayList;

public class Player {

    private Inventory playerInventory;
    private int gold;
    private String status;
    private int sickness_level;
    private int reputation;
    private ArrayList<Theory> publishedTheories;
    private DeductionBoard playersDeductionBoard;

    public Player(){
        this.playerInventory = new Inventory(this);
        this.gold = 0;
        this.status = "";
        this.sickness_level = 0;
        this.reputation = 0;
        this.publishedTheories = new ArrayList<>();
        this.playersDeductionBoard = new DeductionBoard();
    }


    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public int getGold() {
        return gold;
    }

    public String getStatus() {
        return status;
    }

    public int getSickness_level() {
        return sickness_level;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }


    public DeductionBoard getPlayersDeductionBoard() {
        return playersDeductionBoard;
    }

    public void setPlayersDeductionBoard(DeductionBoard playersDeductionBoard) {
        this.playersDeductionBoard = playersDeductionBoard;
    }

    public ArrayList<Theory> getPublishedTheories() {
        return publishedTheories;
    }

    public void setPublishedTheories(ArrayList<Theory> publishedTheories) {
        this.publishedTheories = publishedTheories;
    }
}
