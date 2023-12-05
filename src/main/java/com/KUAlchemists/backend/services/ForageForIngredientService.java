package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Player;

public class ForageForIngredientService {

    private final Player player;
    private final Deck deck = Deck.getInstance();
    //private final Board board = Board.getInstance();
    public ForageForIngredientService(Player player) {
        this.player = player;
    }

    public void forageForIngredient() {
        Board.getIngredientStorage(player).addIngredient(deck.drawIngredient());
    }
}
