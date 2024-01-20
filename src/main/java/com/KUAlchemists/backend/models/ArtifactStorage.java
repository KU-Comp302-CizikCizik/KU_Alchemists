package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.engine.GameEngine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of artifacts available in the game.
 */
public class ArtifactStorage implements Serializable {
    private ArrayList<Artifact> artifactsList = new ArrayList<>();
    private ArrayList<Artifact> usedArtifactsList = new ArrayList<>();
    public ArtifactStorage() {
    }
    public void addArtifact(Artifact artifact) {
        artifactsList.add(artifact);
    }
    public void removeArtifact(Artifact artifact) {
        this.artifactsList.remove(artifact);
        this.usedArtifactsList.add(artifact);
    }
    public Artifact getArtifactByName(String name) {
        for (Artifact artifact : artifactsList) {
            if (artifact.getName().equals(name)) {
                return artifact;
            }
        }
        return null;
    }
    // If artifacts have victory points and you need to calculate total, add a method like this
    public int calculateTotalVictoryPoints() {
        int total = 0;
        for (Artifact artifact : artifactsList) {
            total += artifact.getVictoryPoints();
        }
        return total;
    }
    public List<Artifact> getUsedArtifacts() {
        return this.usedArtifactsList;
    }
    public ArrayList<Artifact> getArtifactList() {
        return this.artifactsList;
    }
    /**
     * Returns the total number of artifacts in the storage.
     * @return
     */
    public int getArtifactsTotalNumber() {
        int total = 0;
        for (Artifact artifact : artifactsList) {
            total += 1;
        }
        return total;
    }

}