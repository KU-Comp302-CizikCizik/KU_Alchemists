package com.KUAlchemists.backend.subjects;

import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.observer.GameStateObserver;
import com.KUAlchemists.backend.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class GameStateData implements Subject{

    private List<GameStateObserver> observers;
    private Gamestate gamestate;

    public GameStateData(){
        observers = new ArrayList<>();
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add((GameStateObserver) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove((GameStateObserver) observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            ((GameStateObserver) observer).onGameStateChanged(gamestate);
        }
    }

    public void onGameStateChanged(Gamestate gamestate){
        this.gamestate = gamestate;
        notifyObservers();
    }
}
