package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.ArtifactStorage;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class ScoringService {

    public ScoringService() {
    }

    public Integer calculateScore(Player player) {
        System.out.println("Calculating score for player " + player.getName());
        int score = 0;
        int goldScore = player.getGold() / 3; // 3 gold = 1 score
        System.out.println("Gold  " + player.getGold());
        System.out.println("Gold score: " + goldScore);
        player.setGold(player.getGold() - goldScore * 3); // remove the gold that is not divisible by 3
        //int remainingGold = player.getGold();
        //System.out.println("Remaining gold: " + remainingGold);
        score += goldScore;
        score += player.getReputation() * 10; // 1 reputation = 10 score
        System.out.println("Reputation: " + player.getReputation());
        System.out.println("Reputation score: " + player.getReputation() * 10);
        score += artifactScore(player); // calculate artifact score
        System.out.println("Artifact number: " + Board.getInstance().getArtifactStorages().get(player).getArtifactsTotalNumber());
        System.out.println("Artifact score: " + artifactScore(player));
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
            System.out.println("Player " + (i+1) + " score: " + score);
            scoreMap.put(i, score);
        }
        // We have a map with player index and score
        // We need to sort the map by score
        // We need to return the keys of the map
        Collection<Integer> scores = scoreMap.values(); // get the values of the map
        ArrayList<Integer> sortedScores = new ArrayList<>(scores); // convert the values to an arraylist
        Collections.sort(sortedScores); // sort the arraylist
        Collections.reverse(sortedScores); // reverse the arraylist
        // Print the sorted arraylist
        for (int i = 0; i < sortedScores.size(); i++) { // iterate over the sorted arraylist
            for (int j = 0; j < sortedScores.size(); j++) { // iterate over the map
                if (sortedScores.get(i) == scoreMap.get(j)) { // if the value of the sorted arraylist is equal to the value of the map
                    rankingList.add(j); // add the key of the map to the ranking list
                }
            }
        }
        return rankingList;
    }

    /**
     * This method is used to sell remaining artifacts,
     * @param player
     */
    public int artifactScore(Player player){
        HashMap<Player, ArtifactStorage> storages = Board.getInstance().getArtifactStorages();
        ArtifactStorage storage = storages.get(player);
        int numberOfArtifacts = storage.getArtifactsTotalNumber();
        int artifactScore = 0;
        for(int i = 0; i < numberOfArtifacts; i++){
            artifactScore += storage.getArtifactList().get(i).getVictoryPoints();
            artifactScore += 2; // 2 point for each remaining artifact
        }
        return artifactScore;
    }

    public int getScore(Player player) {
        return player.getScore() - getRemainingGold(player);
    }

    public int getRemainingGold(Player player){
        int remainingGold = player.getGold();
        return remainingGold;
    }

    public String isThereADraw(ArrayList<Player> players) {
        int winnerIndex = 0;
        int winnerScore = 0;
        for (int i = 0; i < players.size(); i++) {
            int score = players.get(i).getScore();
            if (score > winnerScore) {
                winnerScore = score;
                winnerIndex = i;
            }
            else if (score == winnerScore) {
                if (players.get(i).getGold() > players.get(winnerIndex).getGold()) {
                    winnerIndex = i;
                }
                else if (players.get(i).getGold() == players.get(winnerIndex).getGold()) {
                    return "There is a draw between" + "player" + (i + 1) + " and " + "player" + (winnerIndex + 1);
                }
            }
        }
        return "no draw";
    }
}
