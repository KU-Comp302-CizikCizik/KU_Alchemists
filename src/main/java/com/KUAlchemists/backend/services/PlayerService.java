package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.exceptions.PlayerNotFoundException;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;

import java.util.List;


public class PlayerService {

    private List<Player> players; // This should be replaced with your data access mechanism.

    public PlayerService(List<Player> players) {
        this.players = players;
    }

    /**
     * Finds a player by their name.
     *
     * @param playerName The name of the player to find.
     * @return The found player or null if no player with the given name exists.
     */
    public Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equalsIgnoreCase(playerName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates the player's gold amount.
     *
     * @param playerName The name of the player.
     * @param gold The new amount of gold.
     */
    public void updatePlayerGold(String playerName, int gold) {
        Player player = findPlayerByName(playerName);
        if (player != null) {
            player.setGold(gold);
        } else {
            // Handle the case where the player is not found
            throw new PlayerNotFoundException("Player with name " + playerName + " not found");
        }
    }

    /**
     * Adds a published theory to the player's list of published theories.
     *
     * @param playerName The name of the player.
     * @param theory The theory to be added.
     */
    public void addPublishedTheory(String playerName, Theory theory) {
        Player player = findPlayerByName(playerName);
        if (player != null) {
            player.getPublishedTheories().add(theory);
        } else {
            // Handle the case where the player is not found
            throw new PlayerNotFoundException("Player with name " + playerName + " not found");
        }
    }


    public void updatePlayerReputation(String playerName, int reputationChange) {
        Player player = findPlayerByName(playerName);
        if (player != null) {
            int newReputation = Math.max(0, player.getReputation() + reputationChange); // Prevent negative reputation.
            player.setReputation(newReputation);
        } else {
            // Handle the case where the player is not found
            // This might include logging the issue or throwing an exception
            throw new PlayerNotFoundException("Player with name " + playerName + " not found");
        }
    }

    // Additional methods to handle other player operations...

}
