package com.KUAlchemists.backend.subjects;

import com.KUAlchemists.backend.observer.GameTurnObserver;
import com.KUAlchemists.backend.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class GameTurnData implements Subject{

    List<GameTurnObserver> observers;

    private int gameTurn;

    public GameTurnData(){
        observers = new ArrayList<>();
        gameTurn = 0;
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add((GameTurnObserver) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove((GameTurnObserver) observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            ((GameTurnObserver) observer).onGameTurnChanged(gameTurn);
        }
    }

    public void onGameTurnChangedPerformed(int gameTurn){
        this.gameTurn = gameTurn;
        notifyObservers();
    }
}
