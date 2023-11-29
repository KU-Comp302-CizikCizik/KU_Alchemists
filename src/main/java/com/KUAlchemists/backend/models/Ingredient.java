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
