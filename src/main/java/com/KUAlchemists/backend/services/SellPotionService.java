package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.PotionStorage;
import com.KUAlchemists.ui.controllers.SellPotionController;

import java.util.ArrayList;

public class SellPotionService {
    public SellPotionService() {
    }

    public ArrayList<String> getPlayersPotions(Player player){
        PotionStorage p = Board.getInstance().getPotionStorage(player);
        ArrayList<String> potions = new ArrayList<String>();
        for(int i = 0; i < p.getPotionsList().size(); i++){
            potions.add(p.getPotionsList().get(i).getPotionEffect().toString());
        }
        return potions;
    }

    public String getPotionType(String potionName, Player player) {
        PotionStorage p = Board.getInstance().getPotionStorage(player);
        for (int i = 0; i < p.getPotionsList().size(); i++) {
            if (p.getPotionsList().get(i).getPotionEffect().toString().equals(potionName)) {
                return p.getPotionsList().get(i).getPotionType().toString();
            }
        }
        return "Error";
    }

    public void sellPotion(Player player, String potionName, int price){
        PotionStorage p = Board.getInstance().getPotionStorage(player);
        for(int i = 0; i < p.getPotionsList().size(); i++){
            if(p.getPotionsList().get(i).getPotionEffect().toString().equals(potionName)){
                p.removePotion(p.getPotionsList().get(i));
                player.addGold(price);
                player.deduceActionPoints(1);
                break;
            }
        }
    }

}
