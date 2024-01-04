package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.*;

import java.util.List;
import java.util.stream.Collectors;

public class PublishTheoryService {

    public PublishTheoryService() {
    }

    /**
     * Publishes a new theory.
     * @param rawIngredientName Name of the ingredient in the theory.
     * @param predictedRedAspectString Predicted red aspect.
     * @param predictedGreenAspectString Predicted green aspect.
     * @param predictedBlueAspectString Predicted blue aspect.
     * @return True if the theory is successfully published, false otherwise.
     */
    public boolean publishTheory(String rawIngredientName,
                                 String predictedRedAspectString,
                                 String predictedGreenAspectString,
                                 String predictedBlueAspectString,
                                 List<String> theorySeals) {
        Player player = GameEngine.getInstance().getCurrentPlayer();
        Aspect redAspect = Aspect.fromString(predictedRedAspectString);
        Aspect greenAspect = Aspect.fromString(predictedGreenAspectString);
        Aspect blueAspect = Aspect.fromString(predictedBlueAspectString);
        Alchemical predictedAlchemical = new Alchemical(redAspect, greenAspect, blueAspect);
        String ingredientName = rawIngredientName.split("-")[0];
        Ingredient ingredient = new Ingredient(ingredientName);
        ingredient.setAlchemical(predictedAlchemical);

        List<TheorySeal> seals = theorySeals.stream()
                .map(TheorySeal::getSealByName) // Assuming you have a method to convert string to TheorySeal
                .collect(Collectors.toList());

        Theory theory = new Theory(ingredient, predictedAlchemical, seals);

        // This method checks if a theory with the same ingredient name already exists
        boolean theoryExists = Board.getInstance().getPublishedTheoriesList().stream()
                .anyMatch(existingTheory -> existingTheory.getIngredient().getName().equals(ingredientName));

        boolean hasTheorySeal = player.getTheorySeals().contains(seals.get(0));

        if (!theoryExists && player.getGold() >= 1 & hasTheorySeal) {
            theory.addEndorser(player);
            player.setGold(player.getGold() - 1);
            theory.setPublished(true);
            player.setReputation(player.getReputation() + 1);
            player.getPublishedTheories().add(theory);
            Board.getInstance().getPublishedTheoriesList().add(theory);
            player.getTheorySeals().remove(seals.get(0));
            return true;
        }
        return false;
    }

}
