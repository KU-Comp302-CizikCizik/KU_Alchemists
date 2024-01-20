package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.models.*;
import com.KUAlchemists.backend.states.State;

import java.util.HashMap;
import java.util.List;

public class BoardState extends State {
    // board attributes
    private List<Theory> publishedTheoriesList;

    private HashMap<Player, IngredientStorage> ingredientStorages = new HashMap<>();

    private HashMap<Player, PotionStorage> potionStorages = new HashMap<>();

    private HashMap<Player, ArtifactStorage> artifactStorages = new HashMap<>();

    public BoardState(List<Theory> publishedTheoriesList,
                      HashMap<Player, IngredientStorage> ingredientStorages,HashMap<Player,
            PotionStorage> potionStorages,HashMap<Player, ArtifactStorage> artifactStorages){
        this.publishedTheoriesList = publishedTheoriesList;
        this.artifactStorages = artifactStorages;
        this.ingredientStorages = ingredientStorages;
        this.potionStorages = potionStorages;

    }

    public List<Theory> getPublishedTheoriesList() {
        return publishedTheoriesList;
    }

    @Override
    public void update(StateUpdater stateUpdater) {
        stateUpdater.updateBoard(this);
    }

    public HashMap<Player, IngredientStorage> getIngredientStorages() {
        return ingredientStorages;
    }

    public HashMap<Player, PotionStorage> getPotionStorages() {
        return potionStorages;
    }

    public HashMap<Player, ArtifactStorage> getArtifactStorages() {
        return artifactStorages;
    }



}
