package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.ScoringService;

import java.util.ArrayList;

public class ScoringHandler {

    private static ScoringHandler INSTANCE = null;
    private ScoringService scoringService;

    private ScoringHandler() {
        scoringService = new ScoringService();
    }

    public static ScoringHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScoringHandler();
        }
        return INSTANCE;
    }

    /**
     * Handler for getting the winner of the game.
     * @see ScoringService#getWinner(ArrayList)
     * @return index of the winner player or -1 if there is a draw
     */
    public Integer handleGetWinner() {
        ArrayList <Player> players = GameEngine.getInstance().getPlayerList();
        return scoringService.getWinner(players);
    }
    /**
     * Handler for calculating score of a player.
     * @see ScoringService#calculateScore(Player)
     * @param playerIndex
     * @return score of the player
     */
    public Integer handleGetScores(int playerIndex) {
        return scoringService.calculateScore(GameEngine.getInstance().getPlayer(playerIndex));
    }






}
