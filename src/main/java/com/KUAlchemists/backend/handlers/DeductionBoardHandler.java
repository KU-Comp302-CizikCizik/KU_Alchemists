package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.observer.PotionBrewingObserver;
import com.KUAlchemists.backend.services.DeductionBoardService;

public class DeductionBoardHandler implements PotionBrewingObserver {




    private static DeductionBoardHandler instance;
    private DeductionBoardService deductionBoardService;

    private DeductionBoardHandler() {
        deductionBoardService = new DeductionBoardService();
    }

    public static DeductionBoardHandler getInstance() {
        return instance;
    }


    @Override
    public void onPotionBrewingPerformed(Potion potion) {
        String potionEffect = potion.getPotionEffect().toString();
        String ingredient1name = potion.getIngredient1().getName();
        String ingredient2name = potion.getIngredient2().getName();
        String ingredientCode = ingredient1name + "_" + ingredient2name;
        //give the potion effect as string, png file name
        //ingredient1_name, png file name
        //ingredient2_name, png file name
        //ingredient1-name_ingredient2_name


    }
}
