package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.enums.AspectType;

public class Aspect {


    /**
     * This class represents the aspect of an alchemical.
     */
    public AspectType aspectType;

    public Aspect(AspectType aspectType){
        this.aspectType = aspectType;
    }

    public AspectType getAspectType(){
        return aspectType;
    }

    public void setAspectType(AspectType aspectType){
        this.aspectType = aspectType;
    }

    public String toString(){
        return aspectType.toString();
    }

    public boolean isPositive() {
        return aspectType == AspectType.POSITIVE_BIG || aspectType == AspectType.POSITIVE_SMALL;
    }

    public boolean isNegative() {
        return aspectType == AspectType.NEGATIVE_BIG || aspectType == AspectType.NEGATIVE_SMALL;
    }

    public boolean isBig() {
        return aspectType == AspectType.POSITIVE_BIG || aspectType == AspectType.NEGATIVE_BIG;
    }

    public boolean isSmall() {
        return aspectType == AspectType.POSITIVE_SMALL || aspectType == AspectType.NEGATIVE_SMALL;
    }


}
