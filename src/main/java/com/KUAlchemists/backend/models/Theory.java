package com.KUAlchemists.backend.models;

import java.util.List;

public class Theory {
    private Ingredient ingredient; // The ingredient this theory is about
    private Alchemical predictedAlchemical; // The predicted alchemical properties of the ingredient
    private boolean isPublished; // Indicates if the theory has been published
    private boolean isEndorsed; // Indicates if the theory has been endorsed by other players
    private int reputationAward; // The reputation points awarded for publishing this theory
    private List<Player> endorsers; // List of players who have endorsed this theory


    // Constructor
    public Theory(Ingredient ingredient, Alchemical predictedAlchemical) {
        this.ingredient = ingredient;
        this.predictedAlchemical = predictedAlchemical;
        this.isPublished = false;
        this.isEndorsed = false;
        //this.reputationAward = 0;
    }
    public Theory() {

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

    public boolean isEndorsed() {
        return isEndorsed;
    }

    public int getReputationAward() {
        return reputationAward;
    }

    public List<Player> getEndorsers() {
        return endorsers;
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

    public void setEndorsed(boolean isEndorsed) {
        this.isEndorsed = isEndorsed;
    }

    public void setReputationAward(int reputationAward) {
        this.reputationAward = reputationAward;
    }

    public void setEndorsers(List<Player> endorsers) {
        this.endorsers = endorsers;
    }
}
