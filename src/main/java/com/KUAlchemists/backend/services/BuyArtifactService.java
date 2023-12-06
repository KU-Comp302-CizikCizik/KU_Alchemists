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
    public boolean buyArtifact(Player player, String artifactName) {
        Artifact artifact = artifactShop.getArtifact(artifactName);
        if (artifact == null) {
            return false; // Artifact not found in the shop
        }

        int artifactCost = artifact.getCost();
        if (player.getGold() >= artifactCost) {
            player.setGold(player.getGold() - artifactCost);
            // Assuming Board class has a method to get the player's artifact storage
            ArtifactStorage storage = Board.getInstance().getArtifactStorage(player);
            storage.addArtifact(artifact);
            artifactShop.removeArtifactFromSale(artifact); // Remove the artifact from the shop
            return true;
        } else {
            return false; // Not enough gold to buy the artifact
        }
    }
}