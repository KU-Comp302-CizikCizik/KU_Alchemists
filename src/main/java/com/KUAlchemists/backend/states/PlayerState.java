package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.DeductionBoard;
import com.KUAlchemists.backend.models.Theory;

import java.util.ArrayList;
import java.util.Map;

public class PlayerState extends State {
    // player attributes

    private int gold;
    private int id;

    private int sicknessLevel;

    private String status;

    private int reputation;

    private int actionPoints;

    private ArrayList<Theory> publishedTheories;

    private ArrayList<TheorySeal> theorySeals;

    private DeductionBoard deductionBoard;

    private Map<String, Boolean> activatedArtifacts;


    private int score;


    public PlayerState(int id, int gold, int sicknessLevel,
                       String status,
                       int reputation,
                       int actionPoints,
                       ArrayList<Theory> publishedTheories,
                       ArrayList<TheorySeal> theorySeals,
                       DeductionBoard deductionBoard,
                       int score,
                       Map<String, Boolean> activatedArtifacts){
        this.id = id;
        this.gold = gold;
        this.sicknessLevel = sicknessLevel;
        this.status = status;
        this.reputation = reputation;
        this.actionPoints = actionPoints;
        this.publishedTheories = publishedTheories;
        this.theorySeals = theorySeals;
        this.deductionBoard = deductionBoard;
        this.score = score;
        this.activatedArtifacts = activatedArtifacts;
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

    public int getSicknessLevel() {
        return sicknessLevel;
    }

    public String getStatus() {
        return status;
    }

    public int getReputation() {
        return reputation;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public ArrayList<Theory> getPublishedTheories() {
        return publishedTheories;
    }

    public ArrayList<TheorySeal> getTheorySeals() {
        return theorySeals;
    }

    public DeductionBoard getDeductionBoard() {
        return deductionBoard;
    }

    public int getScore() {
        return score;
    }


    public Map<String, Boolean> getActivatedArtifacts() {
        return activatedArtifacts;
    }
}
