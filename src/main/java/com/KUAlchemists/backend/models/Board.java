package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.engine.GameEngine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Singleton Board Class
 * It has storages for ingredients, potions, and artifacts
 */
public class Board {

    public static Board Instance;

    private static final HashMap<Player, IngredientStorage> ingredientStorages = new HashMap<>();
    //private static final HashMap<Player, PotionStorage> potionStorages = new HashMap<>();
    //private static final HashMap<Player, ArtifactStorage> artifactStorages = new HashMap<>();

    private static final Deck deck = Deck.getInstance();

    private Board (){
        // create empty storages for players // if we have 2 players, otherwise we need to change this
        createEmptyStoragesForPlayer(GameEngine.getInstance().getPlayer(0));
        createEmptyStoragesForPlayer(GameEngine.getInstance().getPlayer(1));


    }
    public static Board getInstance(){
        if(Instance == null){
            Instance = new Board();
        }
        return Instance;
    }

    public static void createEmptyStoragesForPlayer(Player player){
        ingredientStorages.put(player, new IngredientStorage());
        //potionStorages.put(player, new PotionStorage());
        //artifactStorages.put(player, new ArtifactStorage());
    }


    public static IngredientStorage getIngredientStorage(Player player){
        return ingredientStorages.get(player);
    }

    public static IngredientStorage getIngredientStorage(String playerName){
        return ingredientStorages.get(GameEngine.getInstance().getPlayer(playerName));
    }

    public static HashMap<Player, IngredientStorage> getIngredientStorages(){
        return ingredientStorages;
    }

    public static Deck getDeck(){
        return deck;
    }







}

