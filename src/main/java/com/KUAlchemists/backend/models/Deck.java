package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.IngredientType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private static Deck instance;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
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
                Aspect redAspect = Aspect.valueOf(parts[4]);
                Aspect greenAspect = Aspect.valueOf(parts[5]);
                Aspect blueeAspect = Aspect.valueOf(parts[6]);
                // Assuming that Alchemical can be derived from the name or other properties
                Alchemical alchemical = new Alchemical(redAspect, greenAspect, blueeAspect);
                Ingredient ingredient = new Ingredient(name, value, description, type);
                ingredient.setAlchemical(alchemical);
                ingredients.add(ingredient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ingredient drawIngredient(){
        Random rand = new Random();
        int index = rand.nextInt(ingredients.size());
        return ingredients.remove(index);
    }

    public ArrayList<String> getIngredientsNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            names.add(ingredient.getName());
        }
        return names;
    }
}
