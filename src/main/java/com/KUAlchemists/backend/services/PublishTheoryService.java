package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.*;

import java.util.HashMap;
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



        //Ingredient ingredient = Board.getInstance().getIngredientStorage(player).getIngredient(ingredientName);

        String ingredientName = rawIngredientName.split("-")[0];

        Ingredient ingredient = new Ingredient(ingredientName);
        ingredient.setAlchemical(predictedAlchemical);

        List<TheorySeal> seals = theorySeals.stream()
                .map(TheorySeal::getSealByName) // Assuming you have a method to convert string to TheorySeal
                .collect(Collectors.toList());


        // This method checks if a theory with the same ingredient name already exists
        boolean theoryExists = Board.getInstance().getPublishedTheoriesList().stream()
                .anyMatch(existingTheory -> existingTheory.getIngredient().getName().equals(ingredientName));

        boolean hasTheorySeal = player.getTheorySeals().contains(seals.get(0));
        HashMap<Player,TheorySeal> playerTheorySealsMap = new HashMap<>();
        //
        if (!theoryExists && player.getGold() >= 1 & hasTheorySeal && player.getActionPoints()>0 &&
                Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press") == null) {

            playerTheorySealsMap.put(player, seals.get(0));
            Theory theory = new Theory(ingredient, predictedAlchemical, playerTheorySealsMap);
            player.setGold(player.getGold() - 1);
            player.deduceActionPoints(1);
            theory.setPublished(true);
            player.setReputation(player.getReputation() + 1);
            player.getPublishedTheories().add(theory);
            Board.getInstance().getPublishedTheoriesList().add(theory);
            player.getTheorySeals().remove(seals.get(0));
            return true;

        }
        // printing press is owned by the player but not activated
        else if (!theoryExists && player.getGold() >= 1 & hasTheorySeal && player.getActionPoints()>0 &&
                Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press") != null &&
                !Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press").isActivated()) {

            playerTheorySealsMap.put(player, seals.get(0));
            Theory theory = new Theory(ingredient, predictedAlchemical, playerTheorySealsMap);
            player.setGold(player.getGold() - 1);
            player.deduceActionPoints(1);
            theory.setPublished(true);
            player.setReputation(player.getReputation() + 1);
            player.getPublishedTheories().add(theory);
            Board.getInstance().getPublishedTheoriesList().add(theory);
            player.getTheorySeals().remove(seals.get(0));
            return true;
        }

        // if the player has printing press artifact and it is activated, he can publish a theory without paying gold and lose action point.
        else if (!theoryExists && hasTheorySeal &&
                Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press").isActivated() &&
                Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press") != null) {

            playerTheorySealsMap.put(player, seals.get(0));
            Theory theory = new Theory(ingredient, predictedAlchemical, playerTheorySealsMap);
            theory.setPublished(true);
            player.setReputation(player.getReputation() + 1);
            player.getPublishedTheories().add(theory);
            Board.getInstance().getPublishedTheoriesList().add(theory);
            player.getTheorySeals().remove(seals.get(0));
            Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press").setActivated(false); // Deactivate the artifact after use
            player.deactivateArtifact("printing_press");
            Artifact artifact = Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press");
            Board.getInstance().getArtifactStorage(player).removeArtifact(artifact); // Remove the artifact from storage
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
