package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.PublicationTrackObserver;
import com.KUAlchemists.backend.services.DebunkTheoryService;

public class DebunkTheoryHandler implements PublicationTrackObserver {

    private final DebunkTheoryService debunkTheoryService;

    private static DebunkTheoryHandler INSTANCE;

    private Theory selectedTheory; //the theory that the player wants to debunk

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
    public String checkAspect(String Aspect) {
        //It takes the aspect as "blue" or "green" or "red" and returns the sign
        // as "bluePlus.png" or "blueMinus.png" or "greenPlus.png" or "greenMinus.png" or "redPlus.png" or "redMinus.png"
        if (Aspect.equals("blue")) {
            return "blueMinus.png";
        } else if (Aspect.equals("green")) {
            return "greenPlus.png";
        } else if (Aspect.equals("red")) {
            return "redPlus.png";
        }
        return "None";
    }


    public String getTheory() {
        //Before "Debunk button clicked" backend should know the clicked theory name
        //they should save it in a variable and return it here as "mushroom" , "scorpions", "birdfeet"
        return "mushroom";
    }

    @Override
    public void onTheorySelected(Theory theory) {
        selectedTheory = theory;
    }
}