package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.Aspect;

/**
 * This class represents an alchemical essence associated with an ingredient.
 * It holds three aspects: red, green, and blue. Each aspect has a polarity (positive or negative)
 * and a size (big or small), which are important for gameplay mechanics, particularly when
 * combining alchemicals to produce various effects or potions.
 */
public class Alchemical {

    // Define the three aspects of the alchemical, with size and polarity
    private Aspect redAspect;
    private Aspect greenAspect;
    private Aspect blueAspect;

    /**
     * Constructor for Alchemical.
     *
     * @param redAspect   The red aspect of the alchemical.
     * @param greenAspect The green aspect of the alchemical.
     * @param blueAspect  The blue aspect of the alchemical.
     */
    public Alchemical(Aspect redAspect, Aspect greenAspect, Aspect blueAspect) {
        this.redAspect = redAspect;
        this.greenAspect = greenAspect;
        this.blueAspect = blueAspect;
    }

    /**
     * Enum to represent the combination of polarity and size for each aspect.
     */

    // Getters and setters for each aspect

    public Aspect getRedAspect() {
        return redAspect;
    }

    public Aspect getGreenAspect() {
        return greenAspect;
    }

    public Aspect getBlueAspect() {
        return blueAspect;
    }

    // Setters for each aspect
    public void setRedAspect(Aspect redAspect) {
        this.redAspect = redAspect;
    }

    public void setGreenAspect(Aspect greenAspect) {
        this.greenAspect = greenAspect;
    }

    public void setBlueAspect(Aspect blueAspect) {
        this.blueAspect = blueAspect;
    }


    /**
     * getGreenAspect
     * @return greenAspect
     */
    public Aspect getGreenAspect() {
        return greenAspect;
    }


    /**
     * setGreenAspect
     * @param greenAspect
     */
    public void setGreenAspect(Aspect greenAspect) {
        this.greenAspect = greenAspect;
    }
}

