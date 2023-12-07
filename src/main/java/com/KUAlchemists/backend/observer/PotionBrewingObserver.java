package com.KUAlchemists.backend.observer;

import com.KUAlchemists.backend.models.Potion;

public interface PotionBrewingObserver {

    void onPotionBrewingPerformed(Potion potion);


}
