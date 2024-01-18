package com.KUAlchemists.backend.models;


import com.KUAlchemists.backend.enums.PotionEffect;
import com.KUAlchemists.backend.enums.PotionType;

import java.io.Serializable;

public class Potion implements Serializable {

    /**
     * PotionEffect
     */
    private PotionEffect potionEffect;

    private PotionType potionType;


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
        setPotionType();
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

    public void setPotionType() {
        if (potionEffect == PotionEffect.HEALING || potionEffect == PotionEffect.SPEED || potionEffect == PotionEffect.WISDOM) {
            potionType = PotionType.POSITIVE;
        } else if (potionEffect == PotionEffect.POISON || potionEffect == PotionEffect.PARALYSIS || potionEffect == PotionEffect.INSANITY) {
            potionType = PotionType.NEGATIVE;
        } else {
            potionType = PotionType.NEUTRAL;
        }
    }

    public PotionType getPotionType() {
        return potionType;
    }
}
