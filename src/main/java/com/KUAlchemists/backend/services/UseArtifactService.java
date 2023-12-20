package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.*;

import java.util.ArrayList;
import java.util.List;

public class UseArtifactService {
    private Deck deck;

    private PlayerService playerService;

    private ArtifactStorage artifactStorage;


    public UseArtifactService() {
        this.deck = Deck.getInstance();
        this.playerService = new PlayerService();
        this.artifactStorage = new ArtifactStorage();
    }
    //this method puts rearranged cards to the top of the deck also deletes the used artifact from players storage.
    public void useElixirOfInsight(Player player, List<String> rearrangedTopThree) {
        List<Ingredient> rearrangedTopThreeIngredients = playerService.nameToIngredient(player, rearrangedTopThree);
        deck.rearrangeTopThreeIngredients(rearrangedTopThreeIngredients);
    }

    public ArrayList<String> peekTopThree() {
        ArrayList<Ingredient> topThreeIngredients = Deck.getInstance().peekTopThreeIngredients();
        ArrayList<String> topThreeIngredientsNames = new ArrayList<>();
        for (int i = 0; i < topThreeIngredients.size(); i++){
            String name = topThreeIngredients.get(i).getName();
            topThreeIngredientsNames.add(name);
        }
        return topThreeIngredientsNames;
    }

    public ArrayList<String> getUsedArtifacts(){
        ArrayList<String> artifacts = new ArrayList<>();
        for(Artifact a : artifactStorage.getUsedArtifacts()){
            artifacts.add(a.getName());
        }
        return artifacts;
    }

    public void removeArtifactFromStorage(String name, Player currentPlayer) {
        ArtifactStorage storage = Board.getInstance().getArtifactStorage(currentPlayer);
        storage.removeArtifact(artifactStorage.getArtifact(name));

//        for(Artifact a : storage.getArtifactsList()){
//               System.out.println(a.getName());
//        }
    }

        public ArrayList<String> getStorageArtifacts(Player player){
        ArrayList<String> artifacts = new ArrayList<>();
        ArtifactStorage storage = Board.getInstance().getArtifactStorage(player);
        for(Artifact a : storage.getArtifactsList()){
            artifacts.add(a.getName());
        }
        return artifacts;
    }


}
