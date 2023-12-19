package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;

import java.util.ArrayList;

public class BoardHandler {


    private static BoardHandler INSTANCE = null;

    private BoardHandler() {
    }

    public static BoardHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardHandler();
        }
        return INSTANCE;
    }

    /**
     * This method is called when a player wants to end the tour.
     * @return The next tour.
     * @see GameEngine#nextTour()
     */
    public ArrayList<Integer> endTheTour() {
        //TODO: reset action points in backend
        ArrayList<Integer> result = GameEngine.getInstance().nextTour();
        //if it is final tour, then end the round return -1 -1

        return result;
    }
}
