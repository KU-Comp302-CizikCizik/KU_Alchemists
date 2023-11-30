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

    private Board (){

    }
    public static Board getInstance(){
        if(Instance == null){
            Instance = new Board();
        }
        return Instance;
    }

    public static void addPlayer(Player player){
        ingredientStorages.put(player, new IngredientStorage());
        //potionStorages.put(player, new PotionStorage());
        //artifactStorages.put(player, new ArtifactStorage());
    }

    public static void removePlayer(Player player){
        ingredientStorages.remove(player);
        //potionStorages.remove(player);
        //artifactStorages.remove(player);
    }

    public static void addIngredientToStorage(Player player, Ingredient ingredient){
        ingredientStorages.get(player).addIngredient(ingredient);
    }

    public static void removeIngredientFromStorage(Player player, Ingredient ingredient){
        ingredientStorages.get(player).removeIngredient(ingredient);
    }

    public static IngredientStorage getIngredientStorage(Player player){
        return ingredientStorages.get(player);
    }


}
