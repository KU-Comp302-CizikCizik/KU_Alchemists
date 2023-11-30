package com.KUAlchemists.backend.models;

public class Atom {

    private boolean isPositive;
    private boolean isBig;

    public Atom(boolean isPositive, boolean isBig){
        this.isPositive = isPositive;
        this.isBig = isBig;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public boolean isNegative(){
        return !isPositive;
    }

    public boolean isBig() {
        return isBig;
    }

    public boolean isSmall(){
        return !isBig;
    }
}