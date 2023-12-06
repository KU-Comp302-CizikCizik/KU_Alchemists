package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.observer.PotionBrewingObserver;
import com.KUAlchemists.backend.services.DeductionBoardService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeductionBoardHandler {

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

    public void markAlchemical(String alchemicalName){
        GameEngine.getInstance().getCurrentPlayer().getDeductionBoard().addMarkedAlchemy(alchemicalName);

    }

    public void unmarkAlhemical(String alcemicalName){
        GameEngine.getInstance().getCurrentPlayer().getDeductionBoard().removeMarkedAlchemy(alcemicalName);
    }

    public HashMap<String, String> getMarkedIngredients(){
        return GameEngine.getInstance().getCurrentPlayer().getDeductionBoard().getMarkedIngredients();
    }

    public List<String> getMarkedAlchemicals(){
        return GameEngine.getInstance().getCurrentPlayer().getDeductionBoard().getMarkedAlchemicals();

      
    public HashMap<String,String> getCircleList() {
        HashMap<String,String> circleList = new HashMap<>();
        circleList.put("green_minus","root_feather");
        circleList.put("blue_plus","frog_flower");
        circleList.put("red_minus","mushroom_plant");
        return circleList;
    }

    public List<String> getAlchemyList() {
        ArrayList<String> alchemyList = new ArrayList<>();
        alchemyList.add("alchemy_8_6");
        alchemyList.add("alchemy_8_7");
        alchemyList.add("alchemy_8_8");
        alchemyList.add("alchemy_6_1");
        alchemyList.add("alchemy_6_2");
        alchemyList.add("alchemy_6_3");
        alchemyList.add("alchemy_6_4");
        alchemyList.add("alchemy_6_5");
        alchemyList.add("alchemy_1_3");
        alchemyList.add("alchemy_1_4");
        alchemyList.add("alchemy_1_5");
        return alchemyList;
      
    }
}
