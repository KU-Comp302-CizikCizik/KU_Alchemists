package com.KUAlchemists.backend.subjects;

import com.KUAlchemists.backend.enums.GameStatus;
import com.KUAlchemists.backend.observer.GameStatusObserver;
import com.KUAlchemists.backend.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameStatusData implements Subject{


    CopyOnWriteArrayList<GameStatusObserver> observers;

    private GameStatus status;

    public GameStatusData(){
        observers = new CopyOnWriteArrayList<>();

    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add((GameStatusObserver) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove((GameStatusObserver) observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            ((GameStatusObserver) observer).onGameStatusChanged(status);
        }
    }

    public void onGameStatusChanged(GameStatus status){
        this.status = status;
        notifyObservers();
    }
}
