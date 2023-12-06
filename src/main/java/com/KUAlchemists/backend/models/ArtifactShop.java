package com.KUAlchemists.backend.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ArtifactShop {

    private List<Artifact> artifactsForSale;

    public ArtifactShop() {
        this.artifactsForSale = new ArrayList<>();
        loadArtifactsFromResources(); // Load artifacts when the shop is created
    }

    private void loadArtifactsFromResources() {
        // Assuming there's a CSV file named artifacts.csv in the resources directory
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("artifacts.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int cost = Integer.parseInt(parts[1]);
                String effect = parts[2];
                int victoryPoints = Integer.parseInt(parts[3]);
                Artifact artifact = new Artifact(name, cost, effect, victoryPoints);
                artifactsForSale.add(artifact);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions properly in your code
        }
    }

    public List<Artifact> getArtifactsForSale() {
        return new ArrayList<>(artifactsForSale);
    }

    // Method to get an Artifact without removing it from the shop
    public Artifact getArtifact(String artifactName) {
        for (Artifact artifact : artifactsForSale) {
            if (artifact.getName().equalsIgnoreCase(artifactName)) {
                return artifact;
            }
        }
        return null; // Artifact not found
    }

    public void removeArtifactFromSale(Artifact artifact) {
        artifactsForSale.remove(artifact);
    }
}
