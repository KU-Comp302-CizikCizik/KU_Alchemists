package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.engine.GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Singleton Board Class
 * It has storages for ingredients, potions, and artifacts
 */
public class Board {

    private static Board Instance;
    private static final HashMap<Player, IngredientStorage> ingredientStorages = new HashMap<>();
    private static final HashMap<Player, PotionStorage> potionStorages = new HashMap<>();
    private static final HashMap<Player, ArtifactStorage> artifactStorages = new HashMap<>();
    private static final List<Theory> publishedTheoriesList = new ArrayList<>();
    private static final Deck deck = Deck.getInstance();

    private Board (){
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

    public static ArtifactStorage getArtifactStorage(Player player){
        return artifactStorages.get(player);
    }

    public static void initializePlayer(Player player) {
        artifactStorages.put(player, new ArtifactStorage());
    }

    public Deck getDeck(){
        return deck;
    }

    public void addPotionToStorage(Potion potion){
        potionStorages.get(GameEngine.getInstance().getCurrentPlayer()).addPotion(potion);
    }

    public void addPotionToStorage(Player player, Potion potion){
        potionStorages.get(player).addPotion(potion);
    }

    public static List<Theory> getPublishedTheoriesList() {
        return publishedTheoriesList;
    }
}

