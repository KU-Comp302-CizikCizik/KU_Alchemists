package com.KUAlchemists.backend.states;

import java.io.Serializable;

public abstract class State implements Serializable {

    public abstract  void update(StateUpdater stateUpdater);
}
