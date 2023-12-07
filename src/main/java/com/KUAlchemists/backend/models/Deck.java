package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.IngredientType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static Deck instance;
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private Deck() {
        loadIngredientsFromResources();
    }

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    private void loadIngredientsFromResources() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("ingredients.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int value = Integer.parseInt(parts[1]);
                String description = parts[2];
                IngredientType type = IngredientType.valueOf(parts[3]);
                Ingredient ingredient = new Ingredient(name, value, description, type);
                ingredientsList.add(ingredient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ingredient drawIngredient(){
        Random rand = new Random();
        int index = rand.nextInt(ingredientsList.size());
        return ingredientsList.remove(index);
    }

    public void addIngredient(Ingredient ingredient){
        ingredientsList.add(ingredient);
    }

    public ArrayList<String> getIngredientsNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Ingredient ingredient : ingredientsList) {
            names.add(ingredient.getName());
        }
        return names;
    }

    public ArrayList<Ingredient> getIngredientsList(){
        return ingredientsList;
    }


    public void setIngredientList(ArrayList<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public ArrayList<Ingredient> peekTopThreeIngredients() {
        ArrayList<Ingredient> topIngredients = new ArrayList<>();
        int count = Math.min(3, ingredientsList.size());
        for (int i = 0; i < count; i++) {
            topIngredients.add(ingredientsList.get(i));
        }
        return topIngredients;
    }

    public void rearrangeTopThreeIngredients(List<Ingredient> rearrangedIngredients) {
        // Ensure the rearranged list has exactly 3 elements and is a permutation of the top 3 ingredients
        if (rearrangedIngredients.size() == 3 && ingredientsList.containsAll(rearrangedIngredients)) {
            // Remove the top three ingredients
            ingredientsList.subList(0, 3).clear();
            // Add the rearranged ingredients back to the top of the deck
            for (int i = 2; i >= 0; i--) {
                ingredientsList.add(0, rearrangedIngredients.get(i));
            }
        }
    }
}
