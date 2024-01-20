package com.KUAlchemists.backend.enums;

import java.io.Serializable;

public enum Aspect implements Serializable {
    POSITIVE_BIG,
    NEGATIVE_BIG,
    POSITIVE_SMALL,
    NEGATIVE_SMALL;

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


    /**
     * Parses a string to find the corresponding Aspect enum constant.
     * @param str The string representation of the aspect, e.g., "POSITIVE_BIG".
     * @return The corresponding Aspect enum constant.
     * @throws IllegalArgumentException if the string does not match any Aspect.
     */
    public static Aspect fromString(String str) {
        for (Aspect aspect : Aspect.values()) {
            if (aspect.name().equalsIgnoreCase(str)) {
                return aspect;
            }
        }
        throw new IllegalArgumentException("No constant with text " + str + " found in Aspect enum");
    }



}
