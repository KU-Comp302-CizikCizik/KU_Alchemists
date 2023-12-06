package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.services.PotionStorageService;

public class PotionStorageHandler {


    private static PotionStorageHandler INSTANCE = null;

    private PotionStorageService potionStorageService;

    private PotionStorageHandler(){
        this.potionStorageService = new PotionStorageService();
    }


    public static PotionStorageHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PotionStorageHandler();
        }
        return INSTANCE;
    }

    public void handleRemovePotion(Potion potion) {
        try {
            potionStorageService.removePotionFromStorage(potion);
            // Update UI with the potion details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
        }
    }

    public void handleAddPotion(Potion potion){
        try {
            potionStorageService.addPotionToStorage(potion);
            // Update UI with the potion details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
            System.out.println("Error: " + e.getMessage());
        }
    }



}
