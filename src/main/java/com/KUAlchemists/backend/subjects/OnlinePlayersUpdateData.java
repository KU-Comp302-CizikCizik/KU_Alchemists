package com.KUAlchemists.backend.subjects;

import com.KUAlchemists.backend.observer.Observer;
import com.KUAlchemists.backend.observer.OnlinePlayersUpdateObserver;

import java.util.ArrayList;
import java.util.List;

public class OnlinePlayersUpdateData implements Subject{

    List<OnlinePlayersUpdateObserver> observers;

    public OnlinePlayersUpdateData(){
        observers = new ArrayList<>();

    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add((OnlinePlayersUpdateObserver) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove((OnlinePlayersUpdateObserver) observer);
    }

    @Override
    public void notifyObservers() {
        for(OnlinePlayersUpdateObserver observer: observers){
            observer.onUpdateOnlinePlayer();
        }

    }

    public void updateOnlinePlayers(){
        notifyObservers();
    }
}
