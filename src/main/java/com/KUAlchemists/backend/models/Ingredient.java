package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.IngredientType;

/**
 * The game features various types of ingredients (e.g., herbs, minerals, mushrooms), each with unique attributes:
 * Unique identifier.
 * Name.
 * Properties, including color, value, and other characteristics.
 * Ingredients are stored in the Ingredient Storage area on the board.
 */
public class Ingredient {

    private String name;  // name of the ingredient
    private int value;  // value in gold
    private String description; // description of the ingredient
    private IngredientType type; //herb, mineral, mushroom
    private Alchemical alchemical; // alchemical of the ingredient

    public Ingredient(String name, int value, String description, IngredientType type, String image) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.type = type;
    }

    public Ingredient() {
        this.name = "";
        this.value = 0;
        this.description = "";
        this.type = IngredientType.NONE;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return the type
     */
    public IngredientType getType() {
        return type;
    }


    /**
     * @return the alchemical

     */
    public Alchemical getAlchemical() {
        return alchemical;
    }

    /**
     * @param alchemical the alchemical to set
     */
    public void setAlchemical(Alchemical alchemical) {
        this.alchemical = alchemical;
    }
}