package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;
import java.util.List;

public class UseArtifactService {
    private Deck deck;

    private PlayerService playerService;

    public UseArtifactService() {
        this.deck = Deck.getInstance();
        this.playerService = new PlayerService();
    }

    public void useElixirOfInsight(Player player, List<String> rearrangedTopThree) {
        List<Ingredient> rearrangedTopThreeIngredients = playerService.nameToIngredient(player, rearrangedTopThree);
        deck.rearrangeTopThreeIngredients(rearrangedTopThreeIngredients);
    }

    public ArrayList<String> peekTopThree() {
        ArrayList<Ingredient> topThreeIngredients = Deck.getInstance().peekTopThreeIngredients();
        ArrayList<String> topThreeIngredientsNames = new ArrayList<>();
        for (int i = 0; i < topThreeIngredients.size(); i++){
            String name = topThreeIngredients.get(i).getName();
            topThreeIngredientsNames.add(name);
        }
        return topThreeIngredientsNames;
    }
}
