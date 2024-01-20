package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.observer.PotionBrewingObserver;
import com.KUAlchemists.backend.services.DeductionBoardService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeductionBoardHandler implements PotionBrewingObserver {


    private static DeductionBoardHandler instance;
    private DeductionBoardService deductionBoardService;

    private DeductionBoardHandler() {
        deductionBoardService = new DeductionBoardService();
    }

    public static DeductionBoardHandler getInstance() {
        if (instance == null) {
            instance = new DeductionBoardHandler();
        }
        return instance;
    }


    @Override
    public void onPotionBrewingPerformed(Potion potion) {
        String potionEffect = deductionBoardService.getPotionEffectString(potion);
        String ingredientCode = deductionBoardService.getIngredientCode(potion);
        GameEngine.getInstance().getCurrentPlayer().getDeductionBoard().addMarkedIngredient(potionEffect, ingredientCode);
    }

    public void markAlchemical(String alchemicalName) {
        GameEngine.getInstance().getCurrentPlayer().getDeductionBoard().addMarkedAlchemy(alchemicalName);

    }

    public void unmarkAlhemical(String alcemicalName) {
        GameEngine.getInstance().getCurrentPlayer().getDeductionBoard().removeMarkedAlchemy(alcemicalName);
    }

    public HashMap<String, ArrayList<String>> getMarkedIngredients() {
        return GameEngine.getInstance().getCurrentPlayer().getDeductionBoard().getMarkedIngredients();
    }

    public ArrayList<String> getMarkedAlchemicals() {
        return GameEngine.getInstance().getCurrentPlayer().getDeductionBoard().getMarkedAlchemicals();

    }
}

