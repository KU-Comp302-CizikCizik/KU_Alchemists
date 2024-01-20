package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.IngredientType;

import java.io.Serializable;

/**
 * This class represents an ingredient in the game. Each ingredient is associated with a unique
 * alchemical that defines its properties for gameplay, such as potion crafting.
 */
public class Ingredient implements Serializable {
    private final String name;  // name of the ingredient
    private  int value;  // value in gold
    private  String description; // description of the ingredient
    private IngredientType type; //herb, mineral, mushroom
    private Alchemical alchemical; // alchemical of the ingredient

    /**
     * Constructor for Ingredient
     *
     * @param name        The name of the ingredient.
     * @param value       The value of the ingredient.
     * @param description The description of the ingredient.
     * @param type        The type of the ingredient.
     */
    public Ingredient(String name, int value, String description, IngredientType type) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.type = type;
    }

    /**
     * Constructor for Ingredient
     *
     */
    public Ingredient() {
        this.name = "";
        this.value = 0;
        this.description = "";
        this.type = IngredientType.NONE;
    }

    /**
     * Constructor for Ingredient
     *
     */
    public Ingredient(String name) {
        this.name = name;
        this.value = 0;
        this.description = "";
        this.type = IngredientType.NONE;
    }

    /**
     * Constructor for Ingredient
     *
     * @param name        The name of the ingredient.
     * @param alchemical  The alchemical of the ingredient.
     */
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }


}
