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
     * @param ingredientName Name of the ingredient in the theory.
     * @param predictedRedAspectString Predicted red aspect.
     * @param predictedGreenAspectString Predicted green aspect.
     * @param predictedBlueAspectString Predicted blue aspect.
     * @return True if the theory is successfully published, false otherwise.
     */
    // önce string depolanacak sonrasında alchemy gelip theory oluşturacak!
    public boolean publishTheory(String ingredientName,
                                 String predictedRedAspectString,
                                 String predictedGreenAspectString,
                                 String predictedBlueAspectString,
                                 List<String> theorySeals) {
        Player player = GameEngine.getInstance().getCurrentPlayer();
        Aspect redAspect = Aspect.fromString(predictedRedAspectString);
        Aspect greenAspect = Aspect.fromString(predictedGreenAspectString);
        Aspect blueAspect = Aspect.fromString(predictedBlueAspectString);
        Alchemical predictedAlchemical = new Alchemical(redAspect, greenAspect, blueAspect);


        //Ingredient ingredient = Board.getInstance().getIngredientStorage(player).getIngredient(ingredientName);
        Ingredient ingredient = new Ingredient(ingredientName);

        List<TheorySeal> seals = theorySeals.stream()
                .map(TheorySeal::getSealByName) // Assuming you have a method to convert string to TheorySeal
                .collect(Collectors.toList());

        Theory theory = new Theory(ingredient, predictedAlchemical, seals);


        // This method checks if a theory with the same ingredient name already exists
        boolean theoryExists = Board.getInstance().getPublishedTheoriesList().stream()
                .anyMatch(existingTheory -> existingTheory.getIngredient().getName().equals(ingredientName));

        if (!theoryExists && player.getGold() >= 1) {
            player.setGold(player.getGold() - 1);
            theory.setPublished(true);
            player.setReputation(player.getReputation() + 1);
            player.getPublishedTheories().add(theory);
            Board.getInstance().getPublishedTheoriesList().add(theory);


            System.out.println(theory.getIngredient().getName());

            return true;
        }
        return false;
    }


    // Burası daha kullanıma hazır değil
    /**
     * This method retrieves the player's seal.
     *
     * @return The player's seal string.
     */
    public String getPlayerSeal() {
        return GameEngine.getInstance().getCurrentPlayer().getPlayerSeal().getSealString();
    }
    public Number getPlayerAction() {
        return GameEngine.getInstance().getCurrentPlayer().getActionPoints();
    }
}
