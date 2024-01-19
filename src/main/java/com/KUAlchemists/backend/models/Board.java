package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.ApplicationMode;
import com.KUAlchemists.backend.handlers.ForageForIngredientHandler;
import com.KUAlchemists.backend.states.BoardState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton Board Class
 * It has storages for ingredients, potions, and artifacts
 */
public class Board implements Serializable {

    private static Board Instance;
    private HashMap<Player, IngredientStorage> ingredientStorages;
    private HashMap<Player, PotionStorage> potionStorages;
    private HashMap<Player, ArtifactStorage> artifactStorages;
    private List<Theory> publishedTheoriesList;
    private  Deck deck;

    private Board (){
        publishedTheoriesList =  new ArrayList<>();
        artifactStorages =  new HashMap<>();
        potionStorages = new HashMap<>();
        ingredientStorages = new HashMap<>();
        deck = Deck.getInstance();

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

    public void createEmptyStoragesForAllPlayers(){
        for(Player player: GameEngine.getInstance().getPlayerList()){
            createEmptyStoragesForPlayer(player);
        }
    }
    public IngredientStorage getIngredientStorage(Player player){

        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            return ingredientStorages.get(player);
        }
        else{
            for(Map.Entry<Player, IngredientStorage> entry : ingredientStorages.entrySet()){
                if(entry.getKey().getId() == player.getId()){
                    return entry.getValue();
                }
            }
        }
        System.out.println("Bro");
        return null;

    }

    public IngredientStorage getIngredientStorage(String playerName){
        return ingredientStorages.get(GameEngine.getInstance().getPlayer(playerName));
    }
    public HashMap<Player, IngredientStorage> getIngredientStorages(){
        return ingredientStorages;
    }
    public Deck getDeck(){
        return deck;
    }

    public void addPotionToStorage(Potion potion){
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            potionStorages.get(GameEngine.getInstance().getCurrentPlayer()).addPotion(potion);
        }
        else{
            for(Map.Entry<Player, PotionStorage> entry : potionStorages.entrySet()){
                if(entry.getKey().getId() == GameEngine.getInstance().getCurrentPlayer().getId()){
                    entry.getValue().addPotion(potion);
                    return;
                }
            }
        }
    }

    public PotionStorage getPotionStorage(Player player){
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            return potionStorages.get(player);
        }
        else{
            for(Map.Entry<Player, PotionStorage> entry : potionStorages.entrySet()){
                if(entry.getKey().getId() == player.getId()){
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void addArtifactToStorage(Player player, Artifact artifact){
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            artifactStorages.get(player).addArtifact(artifact);
        }
        else{
            for(Map.Entry<Player, ArtifactStorage> entry : artifactStorages.entrySet()){
                if(entry.getKey().getId() == player.getId()){
                    entry.getValue().addArtifact(artifact);
                    return;
                }
            }
        }

    }
    public ArtifactStorage getArtifactStorage(Player player){
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            return artifactStorages.get(player);
        }
        else{
            for(Map.Entry<Player, ArtifactStorage> entry : artifactStorages.entrySet()){
                if(entry.getKey().getId() == player.getId()){
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void addPotionToStorage(Player player, Potion potion){
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE){
            potionStorages.get(player).addPotion(potion);
        }
        else{
            for(Map.Entry<Player, PotionStorage> entry : potionStorages.entrySet()){
                if(entry.getKey().getId() == player.getId()){
                    entry.getValue().addPotion(potion);
                    return;
                }
            }
        }
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
    public HashMap<Player, ArtifactStorage> getArtifactStorages() {
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.OFFLINE)
            return artifactStorages;
        else{
            HashMap<Player, ArtifactStorage> storage = new HashMap<>();

            for(Map.Entry<Player, ArtifactStorage> oldEntry : artifactStorages.entrySet()){
                for(Map.Entry<Player, ArtifactStorage> newEntry : artifactStorages.entrySet()){
                    Player oldPlayer = oldEntry.getKey();
                    Player newPlayer = newEntry.getKey();
                    if(oldPlayer.getId() == newPlayer.getId()) {
                        storage.put(oldPlayer, newEntry.getValue());
                        break;
                    }
                }
            }
            return storage;
        }
    }

    public BoardState getState(){
        return new BoardState(publishedTheoriesList, ingredientStorages, potionStorages, artifactStorages);
    }

    //setIngredientStorage
    public void setIngredientStorages(HashMap<Player, IngredientStorage> newStorage){
        ingredientStorages.clear();
        ingredientStorages.putAll(newStorage);

    }

    public void setPotionStorages(HashMap<Player, PotionStorage> newStorage){
        potionStorages.clear();
        potionStorages.putAll(newStorage);
    }


    public void setArtifactStorages(HashMap<Player, ArtifactStorage> newStorage){
        artifactStorages.clear();
        artifactStorages.putAll(newStorage);

    }

    public void setPublishedTheoriesList(List<Theory> newList){
        this.publishedTheoriesList = newList;
        // TODO: notify observers
    }


    public void createStoragesForNewPlayer(Player p) {
        ingredientStorages.put(p, new IngredientStorage());
        ForageForIngredientHandler.getInstance().forageForIngredient(p);
        ForageForIngredientHandler.getInstance().forageForIngredient(p);
        potionStorages.put(p, new PotionStorage());
        artifactStorages.put(p, new ArtifactStorage());
    }
}

