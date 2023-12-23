package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.PlayerSeal;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int gold;
    private String status;
    private int sicknessLevel;
    private int reputation;
    private ArrayList<Theory> publishedTheories;
    private DeductionBoard deductionBoard;

    private int actionPoints;

    private PlayerSeal seal;

    private String name;


    public Player(){
        this("");
    }

    public Player(String name){
        this.gold = 0;
        this.status = "Healthy"; // Default status
        this.sicknessLevel = 0;
        this.reputation = 0;
        this.publishedTheories = new ArrayList<>();
        this.deductionBoard = new DeductionBoard();
        this.name = name;
        this.actionPoints = 3;
        this.seal = PlayerSeal.getRandomSeal();
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
        if(this.sicknessLevel < 0)this.sicknessLevel = 0;
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
        this.publishedTheories = (ArrayList<Theory>) publishedTheories;
    }

    public DeductionBoard getDeductionBoard() {
        return deductionBoard;
    }

    public void setDeductionBoard(DeductionBoard deductionBoard) {
        this.deductionBoard = deductionBoard;
    }

    public int getSickness_level() {
        return sicknessLevel;
    }

    public void setPublishedTheories(ArrayList<Theory> publishedTheories) {
        this.publishedTheories = publishedTheories;
    }

    public String getName() {
        return name;
    }

    public void deduceActionPoints(int actionPoints) {
        this.actionPoints -= actionPoints;

    }

    public Integer getActionPoints() {
        return actionPoints;
    }

    public void setSeal(PlayerSeal seal){
        this.seal = seal;
    }

    public PlayerSeal getSeal(){
        return seal;
    }
}
