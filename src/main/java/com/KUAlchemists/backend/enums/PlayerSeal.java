package com.KUAlchemists.backend.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public enum PlayerSeal implements Serializable {

    /**
     * Seal colors
     */
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    YELLOW("yellow");

    /**
     * Random seal generator
     */
    private static Random randomSeal = new Random();

    /**
     * List of possible seals
     */
    public static ArrayList<PlayerSeal> POSSIBLE_SEALS = new ArrayList<>(
            Arrays.asList(PlayerSeal.RED, PlayerSeal.BLUE, PlayerSeal.GREEN, PlayerSeal.YELLOW)
    );

    /**
     * Seal color
     */
    public final String sealColor;
    PlayerSeal(String sealColor) {
        this.sealColor = sealColor;
    }



    /**
     * Get a random seal
     * @return a random seal
     */
    public static PlayerSeal getRandomSeal(){
        int random = randomSeal.nextInt(POSSIBLE_SEALS.size());
        PlayerSeal seal = POSSIBLE_SEALS.get(random);
        POSSIBLE_SEALS.remove(random);
        return seal;
    }


    /**
     * Get the seal color
     * @return the seal color
     */
    public String getSealString() {
        return sealColor;
    }



}
