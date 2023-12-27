package com.KUAlchemists.backend.subjects;

import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.Observer;
import com.KUAlchemists.backend.observer.PotionBrewingObserver;
import com.KUAlchemists.backend.observer.PublicationTrackObserver;

import java.util.ArrayList;
import java.util.List;

public class PublicationTrackData implements Subject{

    private List<PublicationTrackObserver> observers;

    private Theory theory;


    public PublicationTrackData(){
        observers = new ArrayList<>();
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add((PublicationTrackObserver) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove((PublicationTrackObserver) observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            ((PublicationTrackObserver) observer).onTheorySelected(theory);
        }
    }

    public void onTheorySelectedPerformed(Theory theory){
        this.theory = theory;
        notifyObservers();
    }
}
