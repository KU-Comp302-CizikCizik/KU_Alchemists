package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.states.ArtifactShopState;
import com.KUAlchemists.backend.states.State;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ArtifactShop implements Serializable {
    private ArrayList<Artifact> artifactsForSale;
    private ArrayList<Artifact> createdArtifacts;

    private HashSet<String> boughtArtifacts = new HashSet<>();
    private static ArtifactShop instance = null;
    public ArtifactShop() {
        this.artifactsForSale = new ArrayList<>();
        this.createdArtifacts = new ArrayList<>();
        loadArtifactsFromResources(); // Load artifacts when the shop is created
    }
    public static ArtifactShop getInstance() {
        if (instance == null) {
            instance = new ArtifactShop();
        }
        return instance;
    }
    private void loadArtifactsFromResources() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("artifacts.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0].trim();
                    int cost = Integer.parseInt(parts[1].trim());
                    String effect = parts[2].trim();
                    int victoryPoints = Integer.parseInt(parts[3].trim());
                    Artifact artifact = new Artifact(name, cost, effect, victoryPoints);
                    artifactsForSale.add(artifact);
                    createdArtifacts.add(artifact);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle exceptions properly here
        }
    }
    public List<Artifact> getArtifactsForSale() {
        return this.artifactsForSale;
    }
    public Artifact getArtifact(String artifactName) {
        if (boughtArtifacts.contains(artifactName)) {
            return null; // Return null if the artifact is already bought
        }
        for (Artifact artifact : createdArtifacts) {
            if (artifact.getName().equals(artifactName)) {
                return artifact;
            }
        }
        return null;
    }
    public void removeArtifactFromSale(Artifact artifact) {
        artifactsForSale.remove(artifact);
        boughtArtifacts.add(artifact.getName()); // Mark the artifact as bought
    }


    public State getState(){
        return new ArtifactShopState();
    }

    public void addArtifactToSale(Artifact discardedMagicMortar) {
        artifactsForSale.add(discardedMagicMortar);
    }
}
