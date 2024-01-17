package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.Aspect;

import java.io.Serializable;

/**
 * This class represents an alchemical essence associated with an ingredient.
 * It holds three aspects: red, green, and blue. Each aspect has a polarity (positive or negative)
 * and a size (big or small), which are important for gameplay mechanics, particularly when
 * combining alchemicals to produce various effects or potions.
 */
public class Alchemical implements Serializable {

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

    public Aspect getBlueAspect() {
        return blueAspect;
    }

    public Aspect getGreenAspect() {
        return greenAspect;
    }

    // Setters for each aspect
    public void setRedAspect(Aspect redAspect) {
        this.redAspect = redAspect;
    }

    public void setBlueAspect(Aspect blueAspect) {
        this.blueAspect = blueAspect;
    }

    public void setGreenAspect(Aspect greenAspect) {
        this.greenAspect = greenAspect;
    }

    /**
     * Gets the aspect based on the specified color.
     *
     * @param color The color of the aspect to retrieve ("red", "green", "blue").
     * @return The corresponding Aspect.
     */
    public Aspect getAspectByColor(String color) {
        switch (color.toLowerCase()) {
            case "red":
                return this.redAspect;
            case "green":
                return this.greenAspect;
            case "blue":
                return this.blueAspect;
            default:
                throw new IllegalArgumentException("Invalid color: " + color);
        }
    }
    /**
     * Sets the aspect based on the specified color.
     *
     * @param color The color of the aspect to set ("red", "green", "blue").
     * @param aspect The new aspect value to set.
     */
    public void setAspectByColor(String color, Aspect aspect) {
        switch (color.toLowerCase()) {
            case "red":
                this.redAspect = aspect;
                break;
            case "green":
                this.greenAspect = aspect;
                break;
            case "blue":
                this.blueAspect = aspect;
                break;
            default:
                throw new IllegalArgumentException("Invalid color: " + color);
        }
    }
    // Bunların doğru olup olmadığından emin değilim.
}

