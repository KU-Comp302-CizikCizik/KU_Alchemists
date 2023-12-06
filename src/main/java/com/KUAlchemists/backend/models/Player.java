package com.KUAlchemists.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int gold;
    private String status;
    private int sicknessLevel;
    private int reputation;
    private ArrayList<Theory> publishedTheories;
    private DeductionBoard deductionBoard;



    private String name;


    public Player(){
        this.gold = 0;
        this.status = "Healthy"; // Default status
        this.sicknessLevel = 0;
        this.reputation = 0;
        this.publishedTheories = new ArrayList<>();
        this.deductionBoard = new DeductionBoard();
    }

    public Player(String name){
        this.gold = 0;
        this.status = "Healthy"; // Default status
        this.sicknessLevel = 0;
        this.reputation = 0;
        this.publishedTheories = new ArrayList<>();
        this.deductionBoard = new DeductionBoard();
        this.name = name;
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
}
