package com.KUAlchemists.backend.subjects;

import com.KUAlchemists.backend.observer.Observer;

import java.io.Serializable;

public interface Subject extends Serializable {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
