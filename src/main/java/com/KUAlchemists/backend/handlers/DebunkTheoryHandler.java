package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.PublicationTrackObserver;
import com.KUAlchemists.backend.services.DebunkTheoryService;

import java.util.ArrayList;

public class DebunkTheoryHandler implements PublicationTrackObserver {

    private DebunkTheoryService debunkTheoryService;

    private static DebunkTheoryHandler INSTANCE;

    private Theory selectedTheory; //the theory that the player wants to debunk

    private Ingredient actualIngredient; //the actual ingredient, not published one

    public DebunkTheoryHandler() {
        this.debunkTheoryService = new DebunkTheoryService();
    }

    public static DebunkTheoryHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DebunkTheoryHandler();
        }
        return INSTANCE;
    }

    public boolean isCurrentPlayerAuthor() {
        return GameEngine.getInstance().getCurrentPlayer().getPublishedTheories().contains(selectedTheory);
    }

    // Other handler methods...
    public String checkAspect(String aspect) {
        //It takes the aspect as "blue" or "green" or "red" and returns the sign
        // as "bluePlus.png" or "blueMinus.png" or "greenPlus.png" or "greenMinus.png" or "redPlus.png" or "redMinus.png"
        Alchemical alchemical = actualIngredient.getAlchemical();
        debunkTheoryService.debunkTheory(selectedTheory,aspect,actualIngredient);
        return debunkTheoryService.getCorrespondingSignWithPath(aspect,alchemical);
    }

    public String getTheory() {
        return selectedTheory.getIngredient().getName().toLowerCase();

    }

    @Override
    public void onTheorySelected(Theory theory) {
        selectedTheory = theory;
        actualIngredient = getActualIngredient(theory);
    }

    private Ingredient getActualIngredient(Theory theory) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.addAll(IngredientStorageHandler.getInstance().getAllIngredients());
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().toLowerCase().equals(theory.getIngredient().getName().toLowerCase())) {
                return ingredient;
            }
        }
        return null;
    }
}