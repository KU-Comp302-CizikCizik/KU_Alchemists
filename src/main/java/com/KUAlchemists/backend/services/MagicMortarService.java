package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

public class  MagicMortarService {

    private String ingredientName1;
    private String ingredientName2;
    private String ingredientNameToRetain;


    public MagicMortarService() {
    }
    /**
     * Activates the Magic Mortar for the given player.
     *
     * @param player The player who is activating the Magic Mortar.
     */
    public void activateMagicMortar(Player player) {
        if (Board.getInstance().getArtifactStorage(player).getArtifactByName("magic_mortar") != null) {
            Board.getInstance().getArtifactStorage(player).getArtifactByName("magic_mortar").setActivated(true);
        }
        else {
            throw new IllegalArgumentException("Player does not have the Magic Mortar artifact.");
        }
    }
    public void setIngredientName1(String ingredientName1) {
        this.ingredientName1 = ingredientName1;
    }
    public void setIngredientName2(String ingredientName2) {
        this.ingredientName2 = ingredientName2;
    }
    // I want to send these ingredient names to the MagicMortarHandler.
    public String getIngredientName1() {
        return ingredientName1;
    }
    public String getIngredientName2() {
        return ingredientName2;
    }
    public String getIngredientNameToRetain() {
        return ingredientNameToRetain;
    }
    public void setIngredientNameToRetain(String ingredientNameToRetain) {
        this.ingredientNameToRetain = ingredientNameToRetain;
    }
}
