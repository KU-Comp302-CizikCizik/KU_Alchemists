package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.PotionEffect;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.models.*;

public class PotionBrewingService {

    /**
     * brewPotion
     * @param ingredient1, ingredient2
     */
    public Potion brewPotion(Ingredient ingredient1, Ingredient ingredient2) {
        //get the alchemical from the ingredients
        Alchemical alchemical1 = ingredient1.getAlchemical();
        Alchemical alchemical2 = ingredient2.getAlchemical();
        //determine the potion effect
        PotionEffect potionEffect = determinePotionEffect(alchemical1, alchemical2);

        return new Potion(potionEffect,ingredient1,ingredient2);
    }


    /**
     * determinePotionEffect
     * @param alchemical1, alchemical2
     */
    private PotionEffect determinePotionEffect(Alchemical alchemical1, Alchemical alchemical2){

        //For red aspect
        Aspect redAspect1 = alchemical1.getRedAspect();
        Aspect redAspect2 = alchemical2.getRedAspect();

        //For blue aspect
        Aspect blueAspect1 = alchemical1.getBlueAspect();
        Aspect blueAspect2 = alchemical2.getBlueAspect();

        //For green aspect
        Aspect greenAspect1 = alchemical1.getGreenAspect();
        Aspect greenAspect2 = alchemical2.getGreenAspect();

        //determine the potion effect
        if (canAspectsCombineForPotion(redAspect1, redAspect2)) {
            return areAspectsBothPositive(redAspect1, redAspect2) ? PotionEffect.HEALING : PotionEffect.POISON;
        } else if (canAspectsCombineForPotion(blueAspect1, blueAspect2)) {
            return areAspectsBothPositive(blueAspect1, blueAspect2) ? PotionEffect.WISDOM : PotionEffect.INSANITY;
        } else if (canAspectsCombineForPotion(greenAspect1, greenAspect2)) {
            return areAspectsBothPositive(greenAspect1, greenAspect2) ? PotionEffect.SPEED : PotionEffect.PARALYSIS;
        }

        return PotionEffect.NEUTRAL;

    }

    /**
     * canAspectsCombineForPotion
     * @param aspect1, aspect2
     */
    private boolean canAspectsCombineForPotion(Aspect aspect1, Aspect aspect2) {
        return canSignsCombineForPotion(aspect1, aspect2) && canSizesCombineForPotion(aspect1, aspect2);
    }

    /**
     * canSignsCombineForPotion
     * @param aspect1, aspect2
     */
    private boolean canSignsCombineForPotion(Aspect aspect1, Aspect aspect2){
        return ( areAspectsBothPositive(aspect1,aspect2) || (!areAspectsBothPositive(aspect1,aspect2)));
    }

    /**
     * canSizesCombineForPotion
     * @param aspect1, aspect2
     */
    private boolean canSizesCombineForPotion(Aspect aspect1, Aspect aspect2){
        return (aspect1.isBig() && aspect2.isSmall()) || (aspect1.isSmall() && aspect2.isBig());
    }

    /**
     * areAspectsBothPositive
     * @param aspect1, aspect2
     */
    private boolean areAspectsBothPositive(Aspect aspect1, Aspect aspect2){
        return (aspect1.isPositive() && aspect2.isPositive());
    }

    /**
     * getPotionCode
     * @param potion
     */
    public String getPotionCode(Potion potion) {
        String potionEffect = potion.getPotionEffect().toString();
        String potionCode = potionEffect + "_POTION";
        return potionCode;
    }

    public String getFormattedName(String ingredientName) {
        return ingredientName.substring(0, 1).toUpperCase() + ingredientName.substring(1);
    }
}
