package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.services.DebunkTheoryService;
import com.KUAlchemists.backend.engine.GameEngine;

public class DebunkTheoryHandler {

    private final DebunkTheoryService debunkTheoryService;

    public DebunkTheoryHandler(DebunkTheoryService debunkTheoryService) {
        this.debunkTheoryService = debunkTheoryService;
    }

    /**
     * Handles a request to debunk a theory.
     *
     * @param theoryId The ID of the theory to be debunked.
     * @return A string message indicating the result of the debunking attempt.
     */
    public String handleDebunkRequest(String theoryId) {
        String playerName = GameEngine.getInstance().getCurrentPlayer().getName(); // Assuming GameEngine can provide the current player
        boolean success = debunkTheoryService.debunkTheory(playerName, theoryId);

        if (success) {
            return "Theory debunked successfully! Reputation increased.";
        } else {
            return "Failed to debunk theory.";
        }
    }

    // Other handler methods...
}
