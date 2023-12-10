package com.KUAlchemists.backend.subjects;

import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.observer.Observer;
import com.KUAlchemists.backend.observer.PotionBrewingObserver;

import java.util.ArrayList;
import java.util.List;

public class PotionBrewingData implements Subject{

    /**
     * This class is a subject class for the observer pattern.
     * It is used to notify the observers when a potion is brewed.
     * It is used in the EventManager class.
     */
    private List<PotionBrewingObserver> observers;
    private Potion potion;

    /**
     * Constructor for the class.
     */
    public PotionBrewingData(){
        observers = new ArrayList<>();
    }

    /**
     * This method is used to register an observer.
     * @param observer
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add((PotionBrewingObserver) observer);
    }

    /**
     * This method is used to remove an observer.
     * @param observer
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove((PotionBrewingObserver) observer);
    }

    /**
     * This method is used to notify the observers.
     */
    @Override
    public void notifyObservers() {
            for(Observer observer : observers){
                ((PotionBrewingObserver) observer).onPotionBrewingPerformed(potion);
            }
    }

    /**
     * This method is used to notify the observers when a potion is brewed.
     * @param potion
     */
    public void onPotionBrewingActionPerformed(Potion potion){
        this.potion = potion;
        notifyObservers();
    }
}
