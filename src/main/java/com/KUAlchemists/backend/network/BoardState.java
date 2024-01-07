package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.models.Theory;

import java.io.Serializable;
import java.util.ArrayList;
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
}
