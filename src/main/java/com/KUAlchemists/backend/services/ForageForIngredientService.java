package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;

public class ForageForIngredientService {

    private final Deck deck = Deck.getInstance();
    public ForageForIngredientService( ) {

    }

    public String forageForIngredient(Player player){
        Ingredient ingredient;
        try {
             ingredient = deck.drawIngredient();
            Board.getInstance().getIngredientStorage(player).addIngredient(ingredient);
        }
        catch (RuntimeException e){
            return "ERROR! Deck is empty";
        }
        return ingredient.getName().toLowerCase();
    }
}
