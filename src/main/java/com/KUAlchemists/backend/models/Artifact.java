package com.KUAlchemists.backend.models;

import java.io.Serializable;

/**
 * Represents an Artifact card in the game with its unique abilities or effects.
 */
public class Artifact implements Serializable {
    private String name;
    private int cost;
    private String effect;
    private final int victoryPoints;
    private boolean activated = false;

    /**
     * Constructor for the Artifact class.
     *
     * @param name          The name of the artifact.
     * @param cost          The cost to buy the artifact.
     * @param effect        The description of the effect of the artifact.
     */
    public Artifact(String name, int cost, String effect, int victoryPoints) {
        this.name = name;
        this.cost = cost;
        this.effect = effect;
        this.victoryPoints = victoryPoints;
        this.activated = false;
    }
    // Getters and setters for each field
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public String getEffect() {
        return effect;
    }
    public void setEffect(String effect) {
        this.effect = effect;
    }
    public int getVictoryPoints() {
        return victoryPoints;
    }
    public boolean isActivated() {
        return activated;
    }
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
