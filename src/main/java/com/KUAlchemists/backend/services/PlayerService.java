package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.exceptions.PlayerNotFoundException;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;

import java.util.List;
import java.util.stream.Collectors;


public class PlayerService {

    private List<Player> players;
    private Deck deck;

    public PlayerService() {
        this.deck = Deck.getInstance();
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

    /**
     * Allows a player to rearrange the top three ingredients of the deck based on a list of names.
     *
     * @param player       The name of the player who is rearranging the ingredients.
     * @param ingredientNames   The names of the ingredients in the new order.
     * @return The rearranged list of ingredients, or null if the operation was not successful.
     */
    public List<Ingredient> nameToIngredient(Player player, List<String> ingredientNames) {
        // Retrieve the top three ingredients from the deck.
        List<Ingredient> topThreeIngredients = deck.peekTopThreeIngredients();
        // Convert the list of ingredient names into actual Ingredient objects.
        List<Ingredient> playerChosenOrder = ingredientNames.stream()
                .map(name -> findIngredientByName(topThreeIngredients, name))
                .collect(Collectors.toList());
        // Apply the rearrangement to the deck.
        deck.rearrangeTopThreeIngredients(playerChosenOrder);
        return playerChosenOrder;
    }

    // Helper method to find an Ingredient by name from a list of Ingredients.
    public Ingredient findIngredientByName(List<Ingredient> ingredients, String name) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
