package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.PublicationTrackObserver;

import java.util.ArrayList;

public class EndorseHandler implements PublicationTrackObserver {

    private Theory selectedTheory;
    private static EndorseHandler instance;
    public static EndorseHandler getInstance() {
        if (instance == null) {
            instance = new EndorseHandler();
        }
        return instance;
     }

    EndorseHandler() {

    }

    public ArrayList<String> getSeals() {
        ArrayList<String> seals = new ArrayList<>();
        seals.add("GS"); //gold star
        seals.add("SS"); //silver star
        seals.add("RQ"); //red question
        seals.add("BQ"); //blue question
        return seals;
    }

    public ArrayList<String> getEndorsedSeals() {
        ArrayList<String> seals = new ArrayList<>();
        //I am placing them as secretSeal
        seals.add("blue"); //blue secret seal
        seals.add("green"); //gree secret seal

        return seals;
    }

    public void saveEndorsedSeal(String name) {
        //save to database
        String endorsedSeal = name;
        System.out.println("Endorsed seal: " + endorsedSeal);

    }

    public String getTheory() {
        return selectedTheory.getIngredient().getName().toLowerCase();
    }

    @Override
    public void onTheorySelected(Theory theory) {
        this.selectedTheory = theory;
    }
}
