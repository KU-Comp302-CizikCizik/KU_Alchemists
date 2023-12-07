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
        Ingredient ingredient = deck.drawIngredient();
        Board.getInstance().getIngredientStorage(player).addIngredient(ingredient);
        return ingredient.getName().toLowerCase();
    }
}
