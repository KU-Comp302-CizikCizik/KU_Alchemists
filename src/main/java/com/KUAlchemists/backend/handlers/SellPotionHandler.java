package com.KUAlchemists.backend.handlers;

import java.util.ArrayList;

public class SellPotionHandler {

    private static SellPotionHandler INSTANCE;
    private boolean skipDialog = false;

    public static SellPotionHandler getInstance(){
        if(INSTANCE == null)
            INSTANCE = new SellPotionHandler();
        return INSTANCE;
    }

    public ArrayList<String> handlerUsersPotions(){
        ArrayList<String> potions = new ArrayList<String>();
        potions.add("HEALING_POTION");
        potions.add("SPEED_POTION");
        potions.add("WISDOM_POTION");
        potions.add("INSANITY_POTION");
        potions.add("PARALYSIS_POTION");
        potions.add("POISON_POTION");
        potions.add("NEUTRAL_POTION");
        return potions;
    }

    public boolean handleIsSkipDialog(){
        return skipDialog;
    }
    public void handleSkipDialog(){
        skipDialog = true;
    }



}
