package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.PublicationTrackObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
        ArrayList<String> seals =  GameEngine.getInstance().getCurrentPlayer().getTheorySeals()
                .stream()
                .map(TheorySeal::getSealString)
                .collect(Collectors.toCollection(ArrayList::new));
        Set<String> set = new HashSet<>(seals);
        return new ArrayList<>(set);
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
        //return selectedTheory.getIngredient().getName().toLowerCase();
        return "frog";
    }

    @Override
    public void onTheorySelected(Theory theory) {
        this.selectedTheory = theory;
    }

    public String getPlayerSeal() {
        return GameEngine.getInstance().getCurrentPlayer().getPlayerSeal().getSealString();
    }
}
