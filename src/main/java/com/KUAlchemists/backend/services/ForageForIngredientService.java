package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Player;

public class ForageForIngredientService {

    private final Deck deck = Deck.getInstance();
    public ForageForIngredientService( ) {

    }

    public void forageForIngredient(Player player){
        Board.getInstance().getIngredientStorage(player).addIngredient(deck.drawIngredient());
    }
}
