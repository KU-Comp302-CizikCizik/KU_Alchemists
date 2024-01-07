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
     * Handler for getting score of a player.
     * @see ScoringService#calculateScore(Player)
     * @param playerIndex
     * @return score of the player
     */
    public Integer handleGetScores(int playerIndex) {
        return scoringService.getScore(GameEngine.getInstance().getPlayerList().get(playerIndex));
    }

    /**
     * Handler for getting the ranking of the players.
     * @see ScoringService#getRanking(ArrayList)
     * @return ranking of the players (player + Index of the players in the player list)
     * For example: if the player list is [player1, player2, player3]
     * and the ranking is [player2, player1, player3]
     * it will return [player2, player1, player3]
     */
    public ArrayList<Integer> handleGetRankingList(){
        ArrayList<Player> players = GameEngine.getInstance().getPlayerList();
        ArrayList<Integer> ranking = scoringService.getRanking(players);
        return ranking;
    }

    public int handleDraw(){
        return scoringService.isThereADraw(GameEngine.getInstance().getPlayerList());
    }



}
