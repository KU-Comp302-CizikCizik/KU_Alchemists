package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.models.Ingredient;

import java.util.ArrayList;

public class DeckState extends State{

    ArrayList<Ingredient> IngredientList;

    public DeckState(ArrayList<Ingredient> IngredientList){
        this.IngredientList = IngredientList;
    }

    @Override
    public void update(StateUpdater stateUpdater) {
        stateUpdater.updateDeck(this);
    }

    public ArrayList<Ingredient> getIngredientList() {
        return IngredientList;
    }
}
