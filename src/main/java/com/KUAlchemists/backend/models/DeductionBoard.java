package com.KUAlchemists.backend.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeductionBoard implements Serializable {
    private static final long serialVersionUID = 1L;


    private HashMap<String, ArrayList<String>> markedIngredients;

    private ArrayList<String> markedAlchemicals;

    public DeductionBoard() {
        markedIngredients = new HashMap<>();
        markedAlchemicals = new ArrayList<>();
    }

    public void setMarkedIngredients(HashMap<String, ArrayList<String>> markedIngredients) {
        this.markedIngredients = markedIngredients;
    }

    public HashMap<String, ArrayList<String>> getMarkedIngredients(){
        return markedIngredients;
    }

    public void addMarkedIngredient(String potionEffect, String ingredientCode) {
        if(markedIngredients.containsKey(potionEffect)){
            ArrayList<String> val = markedIngredients.get(potionEffect);
            val.add(ingredientCode);
            markedIngredients.put(potionEffect,val);
        }
        else{
            ArrayList<String> val = new ArrayList<>();
            val.add(ingredientCode);
            markedIngredients.put(potionEffect, val);
        }


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

    public ArrayList<String> getMarkedAlchemicals() {
        return markedAlchemicals;
    }
}
