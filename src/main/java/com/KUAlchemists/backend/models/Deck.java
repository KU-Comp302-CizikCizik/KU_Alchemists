package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.IngredientType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static Deck instance;
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private Deck() {
        loadIngredientsFromResources("ingredients.csv");
    }

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    /**
     * loadIngredientsFromResources
     *
     * Requires:
     * - A CSV file specified by the `path` parameter must exist in the resources.
     * - The CSV file should be formatted with data in the order: name, value, description, type.
     *   Each line in the file represents one ingredient.
     *
     * Modifies:
     * - Modifies the `ingredientsList` in the `Deck` class by adding new `Ingredient` objects.
     *   Each line in the CSV file corresponds to one `Ingredient` object.
     *
     * Effects:
     * - Reads the CSV file specified by the `path` parameter.
     * - For each line in the file, creates a new `Ingredient` object with the parsed data and adds it to `ingredientsList`.
     * - Handles FileNotFoundException by not adding any ingredients to the list if the file is missing.
     * - Handles incomplete data by partially filling the `ingredientsList` based on the correctly formatted lines in the CSV file.
     * - In case of invalid data format in the CSV file, the method results in an empty `ingredientsList`.
     * - Handles IOException by printing the stack trace, which occurs if there are issues in reading the file.
     * - In cases of empty files or files with invalid data format, the `ingredientsList` remains or becomes empty.
     *
     */


    public void loadIngredientsFromResources(String path) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            if (is == null) {
                throw new FileNotFoundException("File not found");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }



    public Ingredient drawIngredient(){
        Random rand = new Random();
        if (!ingredientsList.isEmpty()) {
            int index = rand.nextInt(ingredientsList.size());
            return ingredientsList.remove(index);
        }
        else {
            throw new RuntimeException("Deck is empty");
        }
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
