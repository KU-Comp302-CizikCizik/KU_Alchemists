package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.BuyArtifactService;

/**
 * This class is responsible for handling buy artifact requests.
 */
public class BuyArtifactHandler {

    private final BuyArtifactService buyArtifactService;

    public BuyArtifactHandler(BuyArtifactService buyArtifactService) {
        this.buyArtifactService = buyArtifactService;
    }

    /**
     * Handles a request to buy an artifact.
     *
     * @param artifactName The name of the artifact to buy.
     * @return A string message indicating the result of the transaction.
     */
    public String handleBuyArtifactRequest(String artifactName) {
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        if (currentPlayer == null) {
            return "No current player available to buy an artifact.";
        }

        boolean success = buyArtifactService.buyArtifact(currentPlayer, artifactName);

        if (success) {
            return "Artifact purchased successfully!";
        } else {
            return "Failed to purchase artifact. Not enough gold or artifact not found.";
        }
    }
}
