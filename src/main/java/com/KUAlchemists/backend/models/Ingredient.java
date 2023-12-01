package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.IngredientType;

/**
 * This class represents an ingredient in the game. Each ingredient is associated with a unique
 * alchemical that defines its properties for gameplay, such as potion crafting.
 */
public class Ingredient {

    private final String name;  // name of the ingredient
    private final int value;  // value in gold
    private final String description; // description of the ingredient
    private final IngredientType type; //herb, mineral, mushroom
    private Alchemical alchemical; // alchemical of the ingredient

    public Ingredient(String name, int value, String description, IngredientType type) {
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

    public Ingredient(String name, Alchemical alchemical) {
        this.name = name;
        this.alchemical = alchemical;
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
