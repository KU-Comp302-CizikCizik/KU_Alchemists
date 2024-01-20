package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.PlayerSeal;
import com.KUAlchemists.backend.enums.TheorySeal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Theory implements Serializable {
    private Ingredient ingredient; // The ingredient this theory is about
    private Alchemical predictedAlchemical; // The predicted alchemical properties of the ingredient
    private boolean isPublished; // Indicates if the theory has been published
    private boolean isDebunked; // Indicates if the theory has been debunked.
    private int reputationAward; // The reputation points awarded for publishing this theory
    private String theoryID; // UI connection
    private HashMap<Player,TheorySeal> playerTheorySealsMap;  // New: Theory seals associated with the theory
    private PlayerSeal playerSeal;        // New: Player seal indicating the endorsing player

    public Theory(Ingredient ingredient, Alchemical predictedAlchemical, HashMap<Player,TheorySeal> playerTheorySealsMap) {
        this.ingredient = ingredient;
        this.predictedAlchemical = predictedAlchemical;
        this.isPublished = false;
        this.isDebunked = false;
        this.playerTheorySealsMap = playerTheorySealsMap;
        // Set other properties as needed
    }
    // Getters
    public Ingredient getIngredient() {
        return ingredient;
    }

    public Alchemical getPredictedAlchemical() {
        return predictedAlchemical;
    }

    public boolean isPublished() {
        return isPublished;
    }


    public boolean isDebunked() {
        return isDebunked;
    }

    public int getReputationAward() {
        return reputationAward;
    }


    // Setters
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setPredictedAlchemical(Alchemical predictedAlchemical) {
        this.predictedAlchemical = predictedAlchemical;
    }

    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }


    public void setDebunked(boolean isDebunked) {
        this.isDebunked = isDebunked;
    }

    public void setReputationAward(int reputationAward) {
        this.reputationAward = reputationAward;
    }


    public String getId() {
        return theoryID;
    }

    // Setter for the theory ID
    public void setId(String id) {
        this.theoryID = id;
    }


    public void addEndorser(Player currentPlayer, TheorySeal theorySeal) {
        playerTheorySealsMap.put(currentPlayer,theorySeal);
    }

    public HashMap<Player,TheorySeal> getPlayerTheorySealsMap() {
        return playerTheorySealsMap;
    }

    public ArrayList<TheorySeal> getTheorySeals() {
        ArrayList<TheorySeal> theorySeals = new ArrayList<>();
        for (Player player : playerTheorySealsMap.keySet()) {
            theorySeals.add(playerTheorySealsMap.get(player));
        }
        return theorySeals;
    }

    public ArrayList<Player> getEndorsers() {
        ArrayList<Player> endorsers = new ArrayList<>();
        for (Player player : playerTheorySealsMap.keySet()) {
            endorsers.add(player);
        }
        return endorsers;
    }
}
