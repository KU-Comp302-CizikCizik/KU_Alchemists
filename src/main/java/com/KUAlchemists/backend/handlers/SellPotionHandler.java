package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.ui.controllers.SellPotionController;

import java.util.ArrayList;

public class SellPotionHandler {

    private static SellPotionHandler INSTANCE;
    private boolean skipDialog = false;
    private String potionName;
    private String potionType;
    private String stage = null; //can only be null, selled, cancelled;

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

    public void handleSellPotion(String potionName, int price){
        System.out.println("The potion: "+potionName+" is sold for "+price+" gold(s)");
        System.out.println("The status is "+getStatus());
    }

    public boolean handleIsSkipDialog(){
        return skipDialog;
    }
    public void handleSkipDialog(){
        skipDialog = true;
    }

    public void setPotionToBeSelled(String[] potion){
        this.potionName = potion[0];
        this.potionType = potion[1];
    }
    public String[] getPotionToBeSelled(){
        return new String[]{potionName, potionType};
    }

    public void setStatusGood(){
        stage = "good";
    }
    public void setStatusBad(){
        stage = "bad";
    }
    public void setStatusCancelled(){
        stage = "cancel";
    }
    public void setStatusNull(){
        stage = null;
    }
    public String getStatus(){
        return stage;
    }




}
