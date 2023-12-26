package com.KUAlchemists.backend.observer;

import com.KUAlchemists.backend.models.Theory;

public interface PublicationTrackObserver extends Observer {

    void onTheorySelected(Theory theory);
}
