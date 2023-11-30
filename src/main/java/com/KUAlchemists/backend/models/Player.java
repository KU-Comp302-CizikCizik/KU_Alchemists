package com.KUAlchemists.backend.models;

import java.util.List;
import java.util.ArrayList;
public class Player {

    private Inventory playerInventory;
    private int gold;
    private String status;
    private int sicknessLevel;
    private int reputation;
    private List<Theory> publishedTheories; // Assuming a player can have multiple published theories
    private DeductionBoard playersDeductionBoard;

    public Player(){
        this.playerInventory = new Inventory();
        this.gold = 0;
        this.status = "Healthy"; // Default status
        this.sicknessLevel = 0;
        this.reputation = 0;
        this.publishedTheories = new ArrayList<>();
        this.playersDeductionBoard = new DeductionBoard();
    }
    // Getters and Setters
    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSicknessLevel() {
        return sicknessLevel;
    }

    public void setSicknessLevel(int sicknessLevel) {
        this.sicknessLevel = sicknessLevel;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public List<Theory> getPublishedTheories() {
        return publishedTheories;
    }

    public void setPublishedTheories(List<Theory> publishedTheories) {
        this.publishedTheories = publishedTheories;
    }

    public DeductionBoard getPlayersDeductionBoard() {
        return playersDeductionBoard;
    }

    public void setPlayersDeductionBoard(DeductionBoard playersDeductionBoard) {
        this.playersDeductionBoard = playersDeductionBoard;
    }

}
