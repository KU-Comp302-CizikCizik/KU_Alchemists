package com.KUAlchemists.backend.handlers;

import java.util.ArrayList;

public class MakeExperimentHandler {

    private static MakeExperimentHandler INSTANCE;
    public static MakeExperimentHandler getInstance(){
        if(INSTANCE == null)
            INSTANCE = new MakeExperimentHandler();
        return INSTANCE;
    }

    public ArrayList<String> getIngredientsToBeBrewed(){
        return PotionBrewingAreaHandler.getInstance().getIngredientsToBeBrewed();
    }

    public void makeExperience(String tester, String potionName, String potionType) {
        //@requires:
        //tester = "student" or "master"
        //potionName = "HEALING_POTION" or "SPEED_POTION" or "WISDOM_POTION" (>>> positive type potions)
        // or "NEUTRAL_POTION" (>>> neutral type potion)
        // or "INSANITY_POTION" or "PARALYSIS_POTION" or "POISON_POTION" (>>> negative type potions)
        //potionType = "positive", "negative", "neutral";
        //
        //@effects: Records the result of the experience:
        //1. if the 'tester' is the master(player), and the 'potionType' is negative, the player gets sick.
        //      note: if the user gets 3 times sick, loses all their points to heal.
        //2. if the 'tester' is the student, and the 'potionType' is negative, the player loses 1 gold.
        //3. if the 'potionType' is positive, the player can heal themselves.
    }


}
