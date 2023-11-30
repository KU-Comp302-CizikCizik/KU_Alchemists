package com.KUAlchemists.backend.models;

import java.util.ArrayList;

public class Inventory {
    private Player owner;
    private ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();
    private ArrayList<Potion> potionList = new ArrayList<Potion>();
    private ArrayList<Artifact> artifactList = new ArrayList<Artifact>();

    public Inventory(Player owner){
        this.owner = owner;
    }

    public void addIngredient(Ingredient ingredient){
        ingredientsList.add(ingredient);
    }

    public void addPotion(Potion potion){
        potionList.add(potion);
    }

    public void addArtifact(Artifact artifact){
        artifactList.add(artifact);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredientsList.remove(ingredient);
    }

    public void removePotion(Potion potion){
        potionList.remove(potion);
    }

    public void removeArtifact(Artifact artifact){
        artifactList.remove(artifact);
    }

    public ArrayList<Ingredient> getIngredientsList(){
        return ingredientsList;
    }

    public ArrayList<Potion> getPotionList(){
        return potionList;
    }

    public ArrayList<Artifact> getArtifactList(){
        return artifactList;
    }

}
