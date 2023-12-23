package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.observer.PlayerObserver;

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
        return GameEngine.getInstance().nextTour();
    }

    public void registerPlayerObserver(PlayerObserver playerObserver) {
        Player player1 = GameEngine.getInstance().getPlayer(0);
        player1.registerObserver(playerObserver);
        Player player2 = GameEngine.getInstance().getPlayer(1);
        player2.registerObserver(playerObserver);
    }

    public Integer getPlayerGold(int playerIndex) {
        return GameEngine.getInstance().getPlayer(playerIndex).getGold();
    }

    public Integer getPlayerReputation(int playerIndex) {
        return GameEngine.getInstance().getPlayer(playerIndex).getReputation();
    }

    public Integer getPlayerActionPoints(int playerIndex) {
        return GameEngine.getInstance().getPlayer(playerIndex).getActionPoints();
    }
}
