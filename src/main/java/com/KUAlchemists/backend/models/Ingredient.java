package com.KUAlchemists.backend.models;
/**
 * This class represents an ingredient in the game. Each ingredient is associated with a unique
 * alchemical that defines its properties for gameplay, such as potion crafting.
 */
public class Ingredient {
    // Unique identifier for the ingredient
    private String identifier;
    // Human-readable name of the ingredient
    private String name;
    // The associated alchemical object
    private Alchemical alchemical;
    // description of the ingredient
    private String description; 

    public Ingredient(String name, Alchemical alchemical) {
        this.name = name;
        this.alchemical = alchemical;
    }

    // Getter for identifier
    public String getIdentifier() {
        return identifier;
    }

    // Setter for identifier
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for alchemical
    public Alchemical getAlchemical() {
        return alchemical;
    }

    // Setter for alchemical
    public void setAlchemical(Alchemical alchemical) {
        this.alchemical = alchemical;
    }

    // Additional methods can be added here as necessary.
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

}
