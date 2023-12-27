package com.KUAlchemists.backend.managers;


import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.PotionBrewingObserver;
import com.KUAlchemists.backend.subjects.PotionBrewingData;
import com.KUAlchemists.backend.subjects.PublicationTrackData;

public class EventManager {


    private static EventManager instance;

    private PotionBrewingData potionBrewingData;

    private PublicationTrackData publicationTrackData;

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
        publicationTrackData = new PublicationTrackData();
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
    public void onPotionBrewingPerformed(Potion potion){
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


    /**
     * This method is used to notify the observers when a theory is selected.
     * @param potionBrewingObserver
     */
    public void registerPublicationTrackObserver(PotionBrewingObserver potionBrewingObserver){
        publicationTrackData.registerObserver(potionBrewingObserver);
    }

    /**
     * This method is used to remove an observer.
     * @param potionBrewingObserver
     */
    public void removePublicationTrackObserver(PotionBrewingObserver potionBrewingObserver){
        publicationTrackData.removeObserver(potionBrewingObserver);
    }

    /**
     * This method is used to notify the observers when a theory is selected.
     * @param theory
     */
    private void notifyPublicationTrackObservers(Theory theory){
        publicationTrackData.onTheorySelectedPerformed(theory);
    }

    /**
     * This method is used to notify the observers when a theory is selected.
     * @param theory
     */
    public void onTheorySelectedPerformed(Theory theory){
        notifyPublicationTrackObservers(theory);
    }



}
