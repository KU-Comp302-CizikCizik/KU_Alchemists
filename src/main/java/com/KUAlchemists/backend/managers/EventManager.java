package com.KUAlchemists.backend.managers;


import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.observer.PotionBrewingObserver;
import com.KUAlchemists.backend.subjects.PotionBrewingData;

public class EventManager {


    private static EventManager instance;

    private PotionBrewingData potionBrewingData;

    /**
     * This method is used to get the instance of the class.
     * @return
     */
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    /**
     * Constructor for the class.
     */
    private EventManager() {
        potionBrewingData = new PotionBrewingData();
    }

    /**
     * This method is used to notify the observers when a potion is brewed.
     * @param potion
     */
    private void notifyPotionBrewingObservers(Potion potion){
        potionBrewingData.onPotionBrewingActionPerformed(potion);
    }

    /**
     * This method is used to notify the observers when a potion is brewed.
     * @param potion
     */
    public void onPotionBrewingActionPerformed(Potion potion){
        notifyPotionBrewingObservers(potion);
    }

    /**
     * This method is used to register an observer.
     * @param potionBrewingObserver
     */
    public void registerPotionBrewingObserver(PotionBrewingObserver potionBrewingObserver){
        potionBrewingData.registerObserver(potionBrewingObserver);
    }

    /**
     * This method is used to remove an observer.
     * @param potionBrewingObserver
     */
    public void removePotionBrewingObserver(PotionBrewingObserver potionBrewingObserver){
        potionBrewingData.removeObserver(potionBrewingObserver);
    }



}
