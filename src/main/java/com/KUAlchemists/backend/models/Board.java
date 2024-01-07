package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.network.BoardState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Singleton Board Class
 * It has storages for ingredients, potions, and artifacts
 */
public class Board {

    private static Board Instance;
    private final HashMap<Player, IngredientStorage> ingredientStorages = new HashMap<>();
    private final HashMap<Player, PotionStorage> potionStorages = new HashMap<>();
    private final HashMap<Player, ArtifactStorage> artifactStorages = new HashMap<>();
    private List<Theory> publishedTheoriesList = new ArrayList<>();
    private final Deck deck = Deck.getInstance();

    protected Board(){
        // create empty storages for players
        for(Player player: GameEngine.getInstance().getPlayerList()){
            createEmptyStoragesForPlayer(player);
        }
    }

    public static Board getInstance(){
        if(Instance == null){
            Instance = new Board();
        }
        return Instance;
    }

    public void createEmptyStoragesForPlayer(Player player){
        ingredientStorages.put(player, new IngredientStorage());
        potionStorages.put(player, new PotionStorage());
        artifactStorages.put(player, new ArtifactStorage());

    }

    public IngredientStorage getIngredientStorage(Player player){
        return ingredientStorages.get(player);
    }

    public IngredientStorage getIngredientStorage(String playerName){
        return ingredientStorages.get(GameEngine.getInstance().getPlayer(playerName));
    }

    public HashMap<Player, IngredientStorage> getIngredientStorages(){
        return ingredientStorages;
    }

    public ArtifactStorage getArtifactStorage(Player player){
        return artifactStorages.get(player);
    }

    public void initializePlayer(Player player) {
        artifactStorages.put(player, new ArtifactStorage());
    }

    public Deck getDeck(){
        return deck;
    }

    public void addPotionToStorage(Potion potion){
        potionStorages.get(GameEngine.getInstance().getCurrentPlayer()).addPotion(potion);
    }

    public PotionStorage getPotionStorage(Player player){
        return potionStorages.get(player);
    }

    public void addPotionToStorage(Player player, Potion potion){
        potionStorages.get(player).addPotion(potion);
    }

    public ArrayList<Theory> getPublishedTheoriesList() {
        ArrayList<Theory> publishedTheoriesList = new ArrayList<>();
        for (Player player : GameEngine.getInstance().getPlayerList()) {
            publishedTheoriesList.addAll(player.getPublishedTheories());
        }
        return publishedTheoriesList;
    }

    public void updateTheTheory(Theory selectedTheory) {
        //There is only one theory for each ingredient
        ArrayList<Theory> publishedTheoriesList = getPublishedTheoriesList();
        for(int i =0;i <publishedTheoriesList.size(); i++){
            Theory theory = publishedTheoriesList.get(i);
            if(theory.getIngredient().getName() == selectedTheory.getIngredient().getName()){
                publishedTheoriesList.set(i,selectedTheory); //update the theory
                return;
            }
        }
    }
<<<<<<< HEAD
    public static void setInstanceForTest(Board instance) {
        Instance = instance;
=======


    public HashMap<Player, ArtifactStorage> getArtifactStorages() {
        return artifactStorages;
    }

    public void setPublishedTheoriesList(List<Theory> newList){
        this.publishedTheoriesList = newList;
        // TODO: notify observers
    }

    public BoardState getState(){
        return new BoardState(publishedTheoriesList);
    }

    public void updateState(BoardState state){
        setPublishedTheoriesList(state.getPublishedTheoriesList());
>>>>>>> ba80be16d939e4385a1a3787e75bb9d32a747963
    }
}

