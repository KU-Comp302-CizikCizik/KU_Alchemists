package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;

/**
 * This class is responsible for storing the ingredients in the game.
 */
public class ForageForIngredientService {
    /**
     * The singleton instance of deck.
     */
    private final Deck deck = Deck.getInstance();
    /**
     * Constructor for IngredientStorageService
     */

    public ForageForIngredientService( ) {
    }

    /**
     * Forages for an ingredient.
     *
     * @param player
     * @return
     */
    public String forageForIngredient(Player player){
        Ingredient ingredient;
        try {
            //draw an ingredient from the deck
            ingredient = deck.drawIngredient();
            //add the ingredient to the player's storage
            Board.getInstance().getIngredientStorage(player).addIngredient(ingredient);
        }
        catch (RuntimeException e){
            return "ERROR! Deck is empty";
        }
        //return the name of the ingredient
        return ingredient.getName().toLowerCase();
    }
}
