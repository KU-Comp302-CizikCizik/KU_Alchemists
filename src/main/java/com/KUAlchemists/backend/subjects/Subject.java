package com.KUAlchemists.backend.subjects;

import com.KUAlchemists.backend.observer.Observer;

public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
