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

    /**
     * Constructor for Ingredient.
     * @param identifier Unique identifier for the ingredient.
     *        In the game, an identifier for an ingredient might look like "ING_001" for an herb or "ING_002" for a mineral.
     *        The specific format and structure of the identifier would depend on your game's design and requirements (on going process).
     * @param name       Human-readable name for the ingredient.
     * @param alchemical The associated alchemical object that defines the ingredient's properties.
     */
    public Ingredient(String identifier, String name, Alchemical alchemical) {
        this.identifier = identifier; //
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
}
