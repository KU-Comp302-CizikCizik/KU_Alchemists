package com.KUAlchemists.backend.enums;

import java.io.Serializable;
import java.util.ArrayList;

public enum TheorySeal implements Serializable {

    RED_QUESTION("RQ"),
    BLUE_QUESTION("BQ"),
    GREEN_QUESTION("GQ"),

    GOLD_STARRED("GS"),

    SILVER_STARRED("SS");


    public final String seal;

    TheorySeal(String seal) {
        this.seal = seal;
    }



    public String getSealString() {
        return seal;
    }

    public static ArrayList<TheorySeal> getSeals() {
        ArrayList<TheorySeal> seals = new ArrayList<>();
        //2 red question seal
        seals.add(RED_QUESTION);
        seals.add(RED_QUESTION);
        //2 blue question seal
        seals.add(BLUE_QUESTION);
        seals.add(BLUE_QUESTION);
        //2 green question seal
        seals.add(GREEN_QUESTION);
        seals.add(GREEN_QUESTION);
        //2 gold starred seal
        seals.add(GOLD_STARRED);
        seals.add(GOLD_STARRED);
        //3 silver starred seal
        seals.add(SILVER_STARRED);
        seals.add(SILVER_STARRED);
        seals.add(SILVER_STARRED);
        return seals;
    }

    public static TheorySeal getSealByName(String seal) {
        switch (seal) {
            case "RQ":
                return RED_QUESTION;
            case "BQ":
                return BLUE_QUESTION;
            case "GQ":
                return GREEN_QUESTION;
            case "GS":
                return GOLD_STARRED;
            case "SS":
                return SILVER_STARRED;
            default:
                try {
                    throw new Exception("Invalid seal name");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
    }
}
