package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.PlayerSeal;
import com.KUAlchemists.backend.enums.TheorySeal;

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

    //To indicate its color on endorse UI, each player has only one, and it is randomly assigned
    private PlayerSeal seal;

    //To indicate the seal of the theory, each player has multiple, they put seals on theories
    private ArrayList<TheorySeal> theorySeals;

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
        this.seal = PlayerSeal.RED;
        //this.seal = PlayerSeal.getRandomSeal(); //random seal for indicating the player's color on endorsement
        this.theorySeals = TheorySeal.getSeals(); //default seals
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

    public ArrayList<Theory> getPublishedTheories() {
        return publishedTheories;
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

    public void setPlayerSeal(PlayerSeal seal){
        this.seal = seal;
    }

    public PlayerSeal getPlayerSeal(){
        return seal;
    }

    public void setTheorySeals(ArrayList<TheorySeal> theorySeals){
        this.theorySeals = theorySeals;
    }

    public ArrayList<TheorySeal> getTheorySeals(){
        return theorySeals;
    }

    public void removeTheorySeal(TheorySeal seal) {
        this.theorySeals.remove(seal);
    }
}
