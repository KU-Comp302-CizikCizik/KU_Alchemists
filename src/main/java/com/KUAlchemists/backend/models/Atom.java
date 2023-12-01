package com.KUAlchemists.backend.models;

public class Atom {

    private boolean isPositive;
    private boolean isBig;

    public Atom(boolean isPositive, boolean isBig){
        this.isPositive = isPositive;
        this.isBig = isBig;
    }

    // Check if two aspects can create a potion
    public boolean matches(Atom other) {
        // For a potion to be created, one aspect must be big and the other small, and their signs must match
        return this.isPositive == other.isPositive && this.isBig != other.isBig;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public boolean isBig() {
        return isBig;
    }
}
