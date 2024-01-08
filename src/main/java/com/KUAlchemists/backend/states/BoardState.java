package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.states.State;

import java.util.List;

public class BoardState extends State {
    // board attributes
    private List<Theory> publishedTheoriesList;

    public BoardState(List<Theory> publishedTheoriesList){
        this.publishedTheoriesList = publishedTheoriesList;
    }

    public List<Theory> getPublishedTheoriesList() {
        return publishedTheoriesList;
    }

    @Override
    void update(StateUpdater stateUpdater) {
        stateUpdater.updateBoard(this);
    }
}
