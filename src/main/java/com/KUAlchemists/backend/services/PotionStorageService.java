package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Potion;

public class PotionStorageService {


    public void removePotionFromStorage(Potion potion) {

    }

    public void addPotionToStorage(Potion potion) {
        Board.getInstance().addPotionToStorage(potion);
    }
}
