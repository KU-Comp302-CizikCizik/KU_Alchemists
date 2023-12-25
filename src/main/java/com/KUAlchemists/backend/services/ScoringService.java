package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;

public class ScoringService {

    public ScoringService() {
    }

    public Integer calculateScore(Player player) {
        int score = 0;
        int goldScore = player.getGold() / 3; // 3 gold = 1 score
        player.setGold(player.getGold() - player.getGold() % 3); // remove the gold that is not divisible by 3
        score += goldScore;
        score += player.getReputation() * 10; // 1 reputation = 10 score
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

    public ArrayList<Player> getRanking(ArrayList<Player> players) {
        ArrayList<Player> ranking = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            int score = calculateScore(players.get(i));
            players.get(i).setScore(score);
        }
        while (players.size() > 0) {
            int maxScore = 0;
            int maxIndex = 0;
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getScore() > maxScore) {
                    maxScore = players.get(i).getScore();
                    maxIndex = i;
                }
            }
            ranking.add(players.get(maxIndex));
            players.remove(maxIndex);
        }
        return ranking;
    }

}
