package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class ScoringService {

    public ScoringService() {
    }

    public Integer calculateScore(Player player) {
        int score = 0;
        int goldScore = player.getGold() / 3; // 3 gold = 1 score
        player.setGold(player.getGold() - player.getGold() % 3); // remove the gold that is not divisible by 3
        score += goldScore;
        score += player.getReputation() * 10; // 1 reputation = 10 score
        player.setScore(score);
        return score;
    }

    public Integer getWinner(ArrayList<Player> players) {
        int winnerIndex = 0;
        int winnerScore = 0;
        for (int i = 0; i < players.size(); i++) {
            int score = calculateScore(players.get(i));
            if (score > winnerScore) {
                winnerScore = score;
                winnerIndex = i;
            }
            else if (score == winnerScore) {
                if (players.get(i).getGold() > players.get(winnerIndex).getGold()) {
                    winnerIndex = i;
                }
                else if (players.get(i).getGold() == players.get(winnerIndex).getGold()) {
                    return -1; // draw
                }
            }
        }
        return winnerIndex;
    }

    // TODO: implement this method
    // getRanking method should return the ranking of the players
    // The ranking of the players is the index of the players in the player list
    // It uses the calculateScore method to calculate the score of the players
    // If the players scores are equal it will check the gold of the players
    // If the gold of the players are equal it will return -1

public ArrayList<Integer> getRanking(ArrayList<Player> players) {
        ArrayList<Integer> rankingList = new ArrayList<>();
        HashMap<Integer, Integer> scoreMap = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            int score = calculateScore(players.get(i));
            score += players.get(i).getGold();
            scoreMap.put(i, score);
        }
        ArrayList<Integer> scoreList = new ArrayList<>(scoreMap.values());
        Collections.sort(scoreList, Collections.reverseOrder());
        for (int i = 0; i < scoreList.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (scoreMap.get(j) == scoreList.get(i)) {
                    rankingList.add(j);
                }
            }
        }
        return rankingList;
    }


}
