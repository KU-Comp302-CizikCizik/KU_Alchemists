package com.KUAlchemists.backend.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeductionBoard {


    private HashMap<String, String> markedIngredients;
    private ArrayList<String> markedAlchemicals;

    public DeductionBoard() {
        markedIngredients = new HashMap<>();
        markedAlchemicals = new ArrayList<>();
    }

    public void setMarkedIngredients(HashMap<String, String> markedIngredients) {
        this.markedIngredients = markedIngredients;
    }

    public HashMap<String, String> getMarkedIngredients(){
        return markedIngredients;
    }

    public void addMarkedIngredient(String potionEffect, String ingredientCode) {
        markedIngredients.put(potionEffect, ingredientCode);
    }


    public void removeMarkedIngredient(String potionEffect) {
        markedIngredients.remove(potionEffect);
    }


    public void addMarkedAlchemy(String alchemicalName) {
        markedAlchemicals.add(alchemicalName);
    }

    public void removeMarkedAlchemy(String alchemicalName) {
        markedAlchemicals.remove(alchemicalName);
    }

    public List<String> getMarkedAlchemicals() {
        return markedAlchemicals;
    }
}
