package com.KUAlchemists.backend.models;


import com.KUAlchemists.backend.enums.PotionEffect;

public class Potion {

    /**
     * PotionEffect
     */
    private PotionEffect potionEffect;


    private Ingredient ingredient1;
    private Ingredient ingredient2;

    /**
     * Potion
     * @param potionEffect
     */
    public Potion(PotionEffect potionEffect, Ingredient ingredient1, Ingredient ingredient2) {
        this.potionEffect = potionEffect;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
    }

    /**
     * getPotionEffect
     */
    public PotionEffect getPotionEffect() {
        return potionEffect;
    }


    /**
     * getIngredient1
     */
    public Ingredient getIngredient1() {
        return ingredient1;
    }

    /**
     * getIngredient2
     */
    public Ingredient getIngredient2() {
        return ingredient2;
    }
}
