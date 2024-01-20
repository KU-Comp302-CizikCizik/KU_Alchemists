package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.services.SellPotionService;

import java.util.ArrayList;

public class SellPotionHandler {

    private static SellPotionHandler INSTANCE;
    private SellPotionService service;
    private boolean skipDialog = false;
    private String potionName;
    private String potionType;
    private String stage = null; //can only be null, selled, cancelled;

    private SellPotionHandler(){
        this.service = new SellPotionService();
    }

    public static SellPotionHandler getInstance(){
        if(INSTANCE == null)
            INSTANCE = new SellPotionHandler();
        return INSTANCE;
    }
    public ArrayList<String> handleGetPlayersPotions(){
        return service.getPlayersPotions(GameEngine.getInstance().getCurrentPlayer());
    }
    public void handleSellPotion(String potionName, int price){
        if(GameEngine.getInstance().getCurrentPlayer().getActionPoints() >= 1) {
            service.sellPotion(GameEngine.getInstance().getCurrentPlayer(), potionName, price);
        }
    }
    public String handleGetPotionType(String potionName) {
        return service.getPotionType(potionName, GameEngine.getInstance().getCurrentPlayer());
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
