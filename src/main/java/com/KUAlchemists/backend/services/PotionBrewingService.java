package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.PotionEffect;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Atom;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Potion;

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

        return new Potion(potionEffect);
    }


    /**
     * determinePotionEffect
     * @param alchemical1, alchemical2
     */
    private PotionEffect determinePotionEffect(Alchemical alchemical1, Alchemical alchemical2){

        //For red atom
        Atom redAtom1 = alchemical1.getRedAtom();
        Atom redAtom2 = alchemical2.getRedAtom();

        //For blue atom
        Atom blueAtom1 = alchemical1.getBlueAtom();
        Atom blueAtom2 = alchemical2.getBlueAtom();

        //For green atom
        Atom greenAtom1 = alchemical1.getGreenAtom();
        Atom greenAtom2 = alchemical2.getGreenAtom();


        if (canAtomsCombineForPotion(redAtom1, redAtom2)) {
            return areAtomsBothPositive(redAtom1, redAtom2) ? PotionEffect.HEALING : PotionEffect.POISON;
        } else if (canAtomsCombineForPotion(blueAtom1, blueAtom2)) {
            return areAtomsBothPositive(blueAtom1, blueAtom2) ? PotionEffect.SPEED : PotionEffect.PARALYSIS;
        } else if (canAtomsCombineForPotion(greenAtom1, greenAtom2)) {
            return areAtomsBothPositive(greenAtom1, greenAtom2) ? PotionEffect.WISDOM : PotionEffect.INSANITY;
        }

        return PotionEffect.NEUTRAL;

    }

    /**
     * canFormPotion
     * @param atom1, atom2
     */
    private boolean canAtomsCombineForPotion(Atom atom1, Atom atom2) {
        return canSignsCombineForPotion(atom1, atom2) && canSizesCombineForPotion(atom1, atom2);
    }

    /**
     * areSignsCorrect
     * @param atom1, atom2
     */
    private boolean canSignsCombineForPotion(Atom atom1, Atom atom2){
        return ( areAtomsBothPositive(atom1,atom2) || (!areAtomsBothPositive(atom1,atom2)));
    }

    /**
     * areSizesCorrect
     * @param atom1, atom2
     */
    private boolean canSizesCombineForPotion(Atom atom1, Atom atom2){
        return (atom1.isBig() && atom2.isSmall()) || (atom1.isSmall() && atom2.isBig());
    }

    /**
     * areAtomsPositive
     * @param atom1, atom2
     */
    private boolean areAtomsBothPositive(Atom atom1, Atom atom2){
        return (atom1.isPositive() && atom2.isPositive());
    }

}
