package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Potion;

public class DeductionBoardService {


    public String getPotionEffectString(Potion potion) {
        return potion.getPotionEffect().toString();
    }

    public String getIngredientSuffix(Ingredient ingredient) {
        String ingredientname = ingredient.getName();
        return ingredientname.split("-")[0].toLowerCase();

    }


    public String getIngredientCode(Potion potion) {
        String ingredient1Suffix = getIngredientSuffix(potion.getIngredient1());
        String ingredient2Suffix = getIngredientSuffix(potion.getIngredient2());
        return ingredient1Suffix + "_" + ingredient2Suffix;
    }


}
