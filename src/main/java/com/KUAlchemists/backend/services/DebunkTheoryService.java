package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Theory;

import java.util.List;

public class DebunkTheoryService {

    private final TheoryService theoryService;

    private final PlayerService playerService;

    private List<Theory> theories; // Replace this with your data access mechanism

    public DebunkTheoryService(TheoryService theoryService, PlayerService playerService, List<Theory> theories) {
        this.theoryService = theoryService;
        this.playerService = playerService;
        this.theories = theories;
    }

    /**
     * Attempts to debunk a theory.
     *
     * @param playerName   The name of the player attempting to debunk the theory.
     * @param theoryId     The ID of the theory to be debunked.
     * @return true if the theory was successfully debunked; false otherwise.
     */
    public boolean debunkTheory(String playerName, String theoryId) {
        Theory theory = theoryService.findTheoryById(theoryId);
        if (theory == null || theory.isDebunked()) {
            // Theory not found or already debunked; cannot proceed with debunking
            return false;
        }

        else {



            if (!theory.isDebunked()) {
                theory.setDebunked(true); // Mark the theory as debunked
                playerService.updatePlayerReputation(playerName, 2); // Increase player's reputation
                return true;
            } else {
                playerService.updatePlayerReputation(playerName, -1); // Decrease player's reputation
                return false;
            }
        }


    }





    // Other methods...

}