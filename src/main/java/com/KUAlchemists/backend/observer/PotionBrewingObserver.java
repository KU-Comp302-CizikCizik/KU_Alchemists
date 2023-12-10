package com.KUAlchemists.backend.observer;

import com.KUAlchemists.backend.models.Potion;

public interface PotionBrewingObserver extends Observer {

    void onPotionBrewingPerformed(Potion potion);


}
