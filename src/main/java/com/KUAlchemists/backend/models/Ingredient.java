package com.KUAlchemists.backend.models;

/**
 * The game features various types of ingredients (e.g., herbs, minerals, mushrooms), each with unique attributes:
 * Unique identifier.
 * Name.
 * Properties, including color, value, and other characteristics.
 * Ingredients are stored in the Ingredient Storage area on the board.
 */
public class Ingredient {

    private String name;  // name of the ingredient
    private String color;  // color of the ingredient
    private int value;  // value in gold
    private String description; // description of the ingredient
    private String type; //herb, mineral, mushroom
    private String image;  //image path

    private Alchemical alchemical; // alchemical of the ingredient

    public Ingredient(String name, String color, int value, String description, String type, String image) {
        this.name = name;
        this.color = color;
        this.value = value;
        this.description = description;
        this.type = type;
        this.image = image;
    }

    public Ingredient() {
        this.name = "";
        this.color = "";
        this.value = 0;
        this.description = "";
        this.type = "";
        this.image = "";
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the color
     */
    public String getColor() {
        return color;
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
    public String getType() {
        return type;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
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