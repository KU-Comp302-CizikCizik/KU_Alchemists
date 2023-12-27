package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
                                 String predictedBlueAspectString) {
        Player player = GameEngine.getInstance().getCurrentPlayer();
        Aspect redAspect = Aspect.fromString(predictedRedAspectString);
        Aspect greenAspect = Aspect.fromString(predictedGreenAspectString);
        Aspect blueAspect = Aspect.fromString(predictedBlueAspectString);
        Alchemical predictedAlchemical = new Alchemical(redAspect, greenAspect, blueAspect);

        //Ingredient ingredient = Board.getInstance().getIngredientStorage(player).getIngredient(ingredientName);
        Ingredient ingredient = new Ingredient(ingredientName);

        Theory theory = new Theory(ingredient, predictedAlchemical); // ingredient ve predicted alchemical aynıysa tekrar oluşturmaya izin verme.

        if (!theory.isPublished() && player.getGold() >= 1) {
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

    /**
     * This method retrieves the seals of the player.
     *
     * @return List of seal strings.
     */
    public ArrayList<String> getPlayerTheorySeals() {
        ArrayList<String> theorySeals = GameEngine.getInstance().getCurrentPlayer().getTheorySeals()
                .stream()
                .map(TheorySeal::getSealString)
                .collect(Collectors.toCollection(ArrayList::new));
        // Remove duplicates
        Set<String> set = new HashSet<>(theorySeals);
        return new ArrayList<>(set);
    }

    /**
     * This method retrieves the seals of the endorsed theory.
     *
     * @return List of seal strings.
     */
    public ArrayList<String> getEndorsedTheorySeals(Theory theory) {
        ArrayList<String> playerSeals = theory.getEndorsers()
                .stream()
                .map(player -> player.getPlayerSeal().getSealString())
                .collect(Collectors.toCollection(ArrayList::new));

        return playerSeals;
    }
    /**
     * This method saves the endorsed seal for the theory.
     *
     * @param sealName The name of the seal to be saved.
     */
    public void saveEndorsedSeal(Theory theory, String sealName) {
        // The format SealGS SealSS SealRQ SealBQ SealGQ
        TheorySeal seal = TheorySeal.getSealByName(sealName);
        GameEngine.getInstance().getCurrentPlayer().removeTheorySeal(seal);
        theory.addEndorser(GameEngine.getInstance().getCurrentPlayer());
        Board.getInstance().updateTheTheory(theory);
    }

    /**
     * This method retrieves the theory name.
     *
     * @return The name of the theory.
     */
    public String getTheoryName(Theory theory) {
        return theory.getIngredient().getName().toLowerCase();
    }

    /**
     * This method retrieves the player's seal.
     *
     * @return The player's seal string.
     */
    public String getPlayerSeal() {
        return GameEngine.getInstance().getCurrentPlayer().getPlayerSeal().getSealString();
    }
}
