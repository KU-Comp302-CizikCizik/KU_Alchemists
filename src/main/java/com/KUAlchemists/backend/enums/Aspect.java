package com.KUAlchemists.backend.enums;

public enum Aspect {
    POSITIVE_BIG, NEGATIVE_BIG, POSITIVE_SMALL, NEGATIVE_SMALL;

    // Methods to determine the properties of the aspects
    public boolean isPositive() {
        return this == POSITIVE_BIG || this == POSITIVE_SMALL;
    }

    public boolean isNegative() {
        return this == NEGATIVE_BIG || this == NEGATIVE_SMALL;
    }

    public boolean isBig() {
        return this == POSITIVE_BIG || this == NEGATIVE_BIG;
    }

    public boolean isSmall() {
        return this == POSITIVE_SMALL || this == NEGATIVE_SMALL;
    }

}
