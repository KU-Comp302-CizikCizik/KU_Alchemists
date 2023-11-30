package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.PotionEffect;

public class Potion {

    /**
     * PotionEffect
     */
    private PotionEffect potionEffect;

    /**
     * Potion
     * @param potionEffect
     */
    public Potion(PotionEffect potionEffect) {
        this.potionEffect = potionEffect;
    }

    /**
     * getPotionEffect
     */
    public PotionEffect getPotionEffect() {
        return potionEffect;
    }
}
