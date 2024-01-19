package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.ApplicationMode;
import com.KUAlchemists.backend.models.ArtifactStorage;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

import java.util.*;

public class ScoringService {
    private HashMap<Integer,Integer> scoreMap;

    public ScoringService() {
        this.scoreMap = new HashMap<>();
    }

    /**
     * This method is used to calculate the score of a player.
     * It calculates the score based on the gold, reputation and artifacts of the player.
     * It also sets the score of the player.
     * @param player
     * @return
     */
    public Integer calculateScore(Player player) {
        int score = 0;
        int goldScore = player.getGold() / 3; // 3 gold = 1 score
        player.setGold(player.getGold() - goldScore * 3); // remove the gold that is not divisible by 3
        //int remainingGold = getRemainingGold(player);
        score += goldScore;
        score += player.getReputation() * 10; // 1 reputation = 10 score
        score += artifactScore(player); // calculate artifact score
        player.setScore(score);
        return score;
    }

    /**
     * This method is used to calculate the score of all players.
     */
    public void calculateScoreOfAllPlayers(){
        for (int i = 0; i < GameEngine.getInstance().getPlayerList().size(); i++){
            int score = calculateScore(GameEngine.getInstance().getPlayer(i));
            scoreMap.put(i,score);
        }
    }

    /**
     * This method is used to get ranking of the players.
     * It returns a list of indices of the players in the player list.
     * For example: if the player list is [player1, player2, player3]
     * and the ranking is [player2, player1, player3]
     * it will return [1, 0, 2]
     * If there is a draw, it will check the remaining gold of the players.
     * @param players
     * @return
     */
    public ArrayList<Integer> getRanking(ArrayList<Player> players) {
        calculateScoreOfAllPlayers(); // first calculate the scores
        // Create a list of indices
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            indices.add(i);
        }
        // Sort the indices based on player scores and gold
        Collections.sort(indices, new Comparator<Integer>() {
            @Override
            public int compare(Integer index1, Integer index2) {
                Player p1 = players.get(index1);
                Player p2 = players.get(index2);

                // Compare by score
                int scoreCompare = Integer.compare(p2.getScore(), p1.getScore());
                if (scoreCompare != 0) {
                    return scoreCompare;
                }

                // If scores are equal, compare by remaining gold
                return Integer.compare(p2.getGold(), p1.getGold());
            }
        });
        return new ArrayList<>(indices);

    }

    /**
     * This method is used to sell remaining artifacts,and calculate the artifact score of the player.
     * @param player
     */
    public int artifactScore(Player player){
        HashMap<Player, ArtifactStorage> storages = Board.getInstance().getArtifactStorages();
        Player p = null;
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            p = player;
        }
        else{
            for(Map.Entry<Player, ArtifactStorage> entry : storages.entrySet()){
                if(entry.getKey().getId() == player.getId()){
                    p = entry.getKey();
                }
            }
        }
        ArtifactStorage storage = storages.get(p);
        int numberOfArtifacts = storage.getArtifactsTotalNumber();
        int artifactScore = 0;
        for(int i = 0; i < numberOfArtifacts; i++){
            artifactScore += storage.getArtifactList().get(i).getVictoryPoints();
            artifactScore += 2; // 2 point for each remaining artifact
        }
        return artifactScore;
    }

    public int getScore(Player player) {
        return player.getScore();
    }

    /**
     * This method is used to determine is there a draw or not.
     * @param players
     * @return
     */
    public int isThereADraw(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (i != j) {
                    int score1 = players.get(i).getScore();
                    int score2 = players.get(j).getScore();
                    if (score1 == score2) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}
