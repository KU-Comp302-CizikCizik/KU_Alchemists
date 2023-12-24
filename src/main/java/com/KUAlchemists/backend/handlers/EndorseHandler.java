package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.PlayerSeal;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.PublicationTrackObserver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
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
        ArrayList<String> theorySeals =  GameEngine.getInstance().getCurrentPlayer().getTheorySeals()
                .stream()
                .map(TheorySeal::getSealString)
                .collect(Collectors.toCollection(ArrayList::new));
        Set<String> set = new HashSet<>(theorySeals);
        return new ArrayList<>(set);
    }

    public ArrayList<String> getEndorsedSeals() {
        //for testing

        selectedTheory = new Theory();
        ArrayList<Player> temp = new ArrayList<>();
        temp.add(GameEngine.getInstance().getCurrentPlayer());
        selectedTheory.setEndorsers(temp);

        //
        ArrayList<String> playerSeals =  selectedTheory.getEndorsers()
                .stream().map(player -> player.getPlayerSeal().getSealString())
                .collect(Collectors.toCollection(ArrayList::new));

        return playerSeals;
    }

    public void saveEndorsedSeal(String sealName) {
        //the format SealGS SealSS SealRQ SealBQ SealGQ
        TheorySeal seal = TheorySeal.getSealByName(sealName);
        GameEngine.getInstance().getCurrentPlayer().removeTheorySeal(seal);
        selectedTheory.addEndorser(GameEngine.getInstance().getCurrentPlayer());
        Board.getInstance().updateTheTheory(selectedTheory);
    }

    public String getTheory() {
        System.out.println("Publish Theory is not implemented, will throw an error");
        return selectedTheory.getIngredient().getName().toLowerCase();
    }

    @Override
    public void onTheorySelected(Theory theory) {
        this.selectedTheory = theory;
    }

    public String getPlayerSeal() {
        return GameEngine.getInstance().getCurrentPlayer().getPlayerSeal().getSealString();
    }
}
