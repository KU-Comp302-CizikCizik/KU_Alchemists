package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.*;

import java.util.ArrayList;

public class BuyArtifactService {

    private ArtifactShop artifactShop;

    public BuyArtifactService() {
        this.artifactShop = new ArtifactShop();
    }

    /**
     * Handles the logic for a player to buy an artifact from the shop.
     *
     * @param player       The player who is buying the artifact.
     * @param artifactName The name of the artifact to buy.
     * @return true if the artifact was successfully bought; otherwise, false.
     */
    public void buyArtifact(Player player, String artifactName) {
        Artifact artifact = artifactShop.getArtifact(artifactName);

        int artifactCost = artifact.getCost();
        if (player.getGold() >= artifactCost) {
            player.setGold(player.getGold() - artifactCost);

            ArtifactStorage storage = Board.getInstance().getArtifactStorage(player);
            storage.addArtifact(artifact);
            artifactShop.removeArtifactFromSale(artifact); // Remove the artifact from the shop

            // Artifact storage çalışyor mu diye test ettim. Çalışıyor.

//            for(Artifact a : storage.getArtifactsList()){
//                System.out.println(a.getName());
//            }
        }
    }

    public ArrayList<String> getArtifacts(){
        ArrayList<String> artifacts = new ArrayList<>();
        for(Artifact a : artifactShop.getArtifactsForSale()){
            artifacts.add(a.getName());
        }
        return artifacts;
    }
    public ArrayList<String> getBoughtArtifacts(){
        ArrayList<String> artifacts = new ArrayList<>();
        for(Artifact a : artifactShop.getBoughtArtifacts()){
            artifacts.add(a.getName());
        }
        // method çalışıyor
//        for(String b : artifacts) {
//            System.out.println(b);
//        }

        return artifacts;
    }
}