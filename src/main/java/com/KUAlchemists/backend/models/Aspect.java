package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.AspectType;

public class Aspect {


    /**
     * This class represents the aspect of an alchemical.
     */
    public AspectType aspectType;

    /**
     * Aspect
     * @param aspectType
     */
    public Aspect(AspectType aspectType){
        this.aspectType = aspectType;
    }

    /**
     * getAspectType
     * @return aspectType
     */
    public AspectType getAspectType(){
        return aspectType;
    }

    /**
     * setAspectType
     * @param aspectType
     */
    public void setAspectType(AspectType aspectType){
        this.aspectType = aspectType;
    }

    /**
     * toString
     * @return aspectType.toString()
     */
    public String toString(){
        return aspectType.toString();
    }

    /**
     * equals
     * @return aspectType.equals(aspect.getAspectType())
     */
    public boolean isPositive() {
        return aspectType == AspectType.POSITIVE_BIG || aspectType == AspectType.POSITIVE_SMALL;
    }

    /**
     * equals
     * @return aspectType.equals(aspect.getAspectType())
     */
    public boolean isNegative() {
        return aspectType == AspectType.NEGATIVE_BIG || aspectType == AspectType.NEGATIVE_SMALL;
    }

    /**
     * equals
     * @return aspectType.equals(aspect.getAspectType())
     */
    public boolean isBig() {
        return aspectType == AspectType.POSITIVE_BIG || aspectType == AspectType.NEGATIVE_BIG;
    }

    /**
     * equals
     * @return aspectType.equals(aspect.getAspectType())
     */
    public boolean isSmall() {
        return aspectType == AspectType.POSITIVE_SMALL || aspectType == AspectType.NEGATIVE_SMALL;
    }


}
