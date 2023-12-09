package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.DebunkTheoryService;

public class DebunkTheoryHandler {

    private final DebunkTheoryService debunkTheoryService;

    private static DebunkTheoryHandler INSTANCE;

    public DebunkTheoryHandler() {
        this.debunkTheoryService = new DebunkTheoryService();
    }

    public static DebunkTheoryHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DebunkTheoryHandler();
        }
        return INSTANCE;
    }

    /**
     * Handles a request to debunk a theory.
     *
     * @param theoryId The ID of the theory to be debunked.
     * @return A string message indicating the result of the debunking attempt.
     */
    public String handleDebunkRequest(String theoryId) {
        Player player = GameEngine.getInstance().getCurrentPlayer(); //GameEngine provides the current player
        boolean success = debunkTheoryService.debunkTheory(player, theoryId);

        if (success) {
            return "Theory debunked successfully! Reputation increased.";
        } else {
            return "Failed to debunk theory.";
        }
    }

    // Other handler methods...
}
