package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.*;

import java.util.ArrayList;

public class BuyArtifactService {
    private ArtifactShop artifactShop = ArtifactShop.getInstance();


    public BuyArtifactService() {
    }
    /**
     * Handles the logic for a player to buy an artifact from the shop.
     * @param player       The player who is buying the artifact.
     * @param artifactName The name of the artifact to buy.
     */
    public void buyArtifact(Player player, String artifactName) {

        Artifact artifact = artifactShop.getArtifact(artifactName);
        int artifactCost = artifact.getCost();
        // Implemented a code that iterates through the artifacts of the player.
        // If the artifact is already in the player's storage, then do not add it.
        // If the artifact is not in the player's storage, then add it.
        // If the artifact is not in the player's storage, then subtract the cost of the artifact from the player's gold.
        // If the artifact is not in the player's storage, then remove the artifact from the shop.
        if(player.getGold() >= artifactCost && !Board.getInstance().getArtifactStorage(player).getArtifactList().contains(artifact) && player.getActionPoints() >= 1){
            player.setGold(player.getGold() - artifactCost);
            player.deduceActionPoints(1);
            Board.getInstance().addArtifactToStorage(player, artifact);
            artifactShop.removeArtifactFromSale(artifact);
            }
    }
    public ArrayList<String> getArtifacts(){
        ArrayList<String> artifacts = new ArrayList<>();
        for(Artifact a : artifactShop.getArtifactsForSale()){
            artifacts.add(a.getName());
        }
        return artifacts;
    }
    //created a method that returns all the artifacts that are bought by players.
    public ArrayList<String> getBoughtArtifacts(){
        ArrayList<String> artifacts = new ArrayList<>();
        for(Player p : GameEngine.getInstance().getPlayerList()){
            for(Artifact a : Board.getInstance().getArtifactStorage(p).getArtifactList()){
                artifacts.add(a.getName());
            }
        }
        return artifacts;
    }
}