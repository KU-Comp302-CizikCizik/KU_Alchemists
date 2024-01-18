package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.PotionEffect;

import java.io.Serializable;
import java.util.ArrayList;

public class PotionStorage implements Serializable {

    private ArrayList<Potion> potionsList = new ArrayList<Potion>();

    public PotionStorage(){
    }

    public void addPotion(Potion potion){
        potionsList.add(potion);
    }

    public void removePotion(Potion potion){
        potionsList.remove(potion);
    }

    public ArrayList<Potion> getPotionsList(){
        return potionsList;
    }

    public Potion getPotion(PotionEffect effect) {
        for (Potion potion : potionsList) {
            if (effect == potion.getPotionEffect()) {
                return potion;
            }
        }
        return null;
    }
}
