package com.KUAlchemists.backend.managers;


import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.observer.PotionBrewingObserver;

import java.util.ArrayList;

public class EventManager {


    private static EventManager instance;

    private ArrayList<PotionBrewingObserver> onPotionBrewingPerformedObserverList;

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    private EventManager() {
        onPotionBrewingPerformedObserverList = new ArrayList<>();
    }


    private void notifyPotionBrewingObservers(Potion potion){
        for(PotionBrewingObserver potionBrewingObserver : onPotionBrewingPerformedObserverList){
            potionBrewingObserver.onPotionBrewingPerformed(potion);
        }
    }

    public void onPotionBrewingActionPerformed(Potion potion){
        notifyPotionBrewingObservers(potion);
    }

    public void registerPotionBrewingObserver(PotionBrewingObserver potionBrewingObserver){
        onPotionBrewingPerformedObserverList.add(potionBrewingObserver);
    }

    public void unregisterPotionBrewingObserver(PotionBrewingObserver potionBrewingObserver){
        onPotionBrewingPerformedObserverList.remove(potionBrewingObserver);
    }



}
