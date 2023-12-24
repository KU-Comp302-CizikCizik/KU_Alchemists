package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;

public class MakeExperimentHandler {

    private static MakeExperimentHandler INSTANCE;

    private MakeExperimentHandler(){
    }
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
        //3. if the 'potionType' is positive, the player can heal themselves, decrease sickness by 1.
        Player master = GameEngine.getInstance().getCurrentPlayer();
        if(tester.equalsIgnoreCase("master")){
            if(potionType.equalsIgnoreCase("negative")){
                master.setSicknessLevel(master.getSicknessLevel()+1);
                if(master.getSicknessLevel() == 3){
                    master.setGold(0);
                    master.setSicknessLevel(0);
                    System.out.println("You are sick for 3 times, you lose all your gold to heal yourself.");
                }
                System.out.println("Master's sickness level: "+GameEngine.getInstance().getCurrentPlayer().getSicknessLevel());
            }
            else if (potionType.equalsIgnoreCase("positive") && potionName.split("_")[0].equalsIgnoreCase("HEALING")){
                master.setSicknessLevel(master.getSicknessLevel()-1);
            }
        }
        else if (tester.equalsIgnoreCase("student")){
            if(potionType.equalsIgnoreCase("negative")){
                master.setGold(master.getGold()-1);
                System.out.println("Master's gold: "+GameEngine.getInstance().getCurrentPlayer().getGold());
            }
        }
        else{
            System.out.println("Invalid tester name.");
        }
        GameEngine.getInstance().setCurrentPlayer(master);

    }


}
