package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;

public class DebunkTheoryService {

    private final TheoryService theoryService;

    private final PlayerService playerService;

    public DebunkTheoryService() {
        this.theoryService = new TheoryService();
        this.playerService = new PlayerService();


    }

    /**
     * Attempts to debunk a theory.
     *
     * @param player  the player attempting to debunk the theory.
     * @param theoryId     The ID of the theory to be debunked.
     * @return true if the theory was successfully debunked; false otherwise.
     */
    public boolean debunkTheory(Player player, String theoryId) {
        Theory theory = theoryService.findTheoryById(theoryId);
        if (theory == null || theory.isDebunked()) {
            // Theory not found or already debunked; cannot proceed with debunking
            return false;
        }

        else {



            if (!theory.isDebunked()) {
                theory.setDebunked(true); // Mark the theory as debunked
                player.setReputation(player.getReputation()+2);// Increase player's reputation
                return true;
            } else {
                player.setReputation(player.getReputation()-1);// Decrease player's reputation
                return false;
            }
        }
    }
}