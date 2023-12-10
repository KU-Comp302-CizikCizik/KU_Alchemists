package com.KUAlchemists.backend.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of artifacts available in the game.
 */
public class ArtifactStorage {

    private ArrayList<Artifact> artifactsList = new ArrayList<>();

    private ArrayList<Artifact> usedArtifactsList = new ArrayList<>();

    public ArtifactStorage() {
    }

    public void addArtifact(Artifact artifact) {
        artifactsList.add(artifact);
    }

    public void removeArtifact(Artifact artifact) {
        artifactsList.remove(artifact);
        this.usedArtifactsList.add(artifact);
    }

    public ArrayList<Artifact> getArtifactsList() {
        return artifactsList;
    }

    public Artifact getArtifact(String name) {
        for (Artifact artifact : artifactsList) {
            if (name.equals(artifact.getName())) {
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

    public List<Artifact> getArtifactList() {
        return this.artifactsList;
    }
}