package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.PublicationTrackObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EndorseHandler implements PublicationTrackObserver {


    private Theory selectedTheory; //the theory that the player wants to endorse
    private static EndorseHandler instance; //singleton instance
    /**
     * This method is called when the player clicks on the endorse button
     * @return instance
     */
    public static EndorseHandler getInstance() {
        if (instance == null) {
            instance = new EndorseHandler();
        }
        return instance;
     }

    /**
     * Constructor
     */
    private EndorseHandler() {

    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return seals
     */
    public ArrayList<String> getPlayerAvailableTheorySeals() {
        ArrayList<String> theorySeals =  GameEngine.getInstance().getCurrentPlayer().getTheorySeals()
                .stream()
                .map(TheorySeal::getSealString)
                .collect(Collectors.toCollection(ArrayList::new));
        //remove duplicates
        Set<String> set = new HashSet<>(theorySeals);
        ArrayList<String> seals = new ArrayList<>(set);
        return seals;
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return playerSeals
     */
    public ArrayList<String> getEndorsedTheorySeals() {
        ArrayList<TheorySeal> theorySeals = selectedTheory.getTheorySeals();
        ArrayList<String> result = new ArrayList<>();
        for (TheorySeal theorySeal : theorySeals){
            result.add(theorySeal.getSealString());
        }
        return result;
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @param sealName
     */
    public void saveEndorsedSeal(String sealName) {
        //the format SealGS SealSS SealRQ SealBQ SealGQ
        TheorySeal seal = TheorySeal.getSealByName(sealName);
        GameEngine.getInstance().getCurrentPlayer().removeTheorySeal(seal);
        selectedTheory.addEndorser(GameEngine.getInstance().getCurrentPlayer());
        Board.getInstance().updateTheTheory(selectedTheory); //TO-DO: Can the two theories that shared the same ingredient be published?
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return theory
     */
    public String getTheoryString() {
        String theory = selectedTheory.getIngredient().getName().toLowerCase();
        return theory;
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @param theory
     */
    @Override
    public void onTheorySelected(Theory theory) {
        this.selectedTheory = theory;
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return playerSeal
     */
    public String getPlayerSeal() {
        String playerSeal = GameEngine.getInstance().getCurrentPlayer().getPlayerSeal().getSealString();
        return playerSeal;
    }

    public ArrayList<String> getEndorsedPlayerSeals() {
        ArrayList<String> playerSeals = selectedTheory.getEndorsers().stream()
                .map(player -> player.getPlayerSeal().getSealString())
                .collect(Collectors.toCollection(ArrayList::new));
        return playerSeals;
    }

    public String getAlchemicalName() {
        String result = "PATLADI";
        Aspect red = selectedTheory.getIngredient().getAlchemical().getRedAspect();
        Aspect green = selectedTheory.getIngredient().getAlchemical().getGreenAspect();
        Aspect blue = selectedTheory.getIngredient().getAlchemical().getBlueAspect();
        if(red == Aspect.POSITIVE_BIG && green == Aspect.POSITIVE_BIG && blue == Aspect.POSITIVE_BIG) {
            return "alchemy1.png";
        }
        if(red == Aspect.POSITIVE_SMALL && green == Aspect.NEGATIVE_SMALL && blue == Aspect.NEGATIVE_BIG) {
            return "alchemy2.png";
        }
        if(red == Aspect.POSITIVE_SMALL && green == Aspect.POSITIVE_BIG && blue == Aspect.NEGATIVE_SMALL) {
            return "alchemy3.png";
        }
        if(red == Aspect.POSITIVE_BIG && green == Aspect.NEGATIVE_SMALL && blue == Aspect.POSITIVE_SMALL) {
            return "alchemy4.png";
        }
        if(red == Aspect.NEGATIVE_SMALL && green == Aspect.POSITIVE_SMALL && blue == Aspect.POSITIVE_BIG) {
            return "alchemy5.png";
        }
        if(red == Aspect.NEGATIVE_BIG && green == Aspect.NEGATIVE_BIG && blue == Aspect.NEGATIVE_BIG) {
            return "alchemy6.png";
        }
        if(red == Aspect.NEGATIVE_SMALL && green == Aspect.NEGATIVE_BIG && blue == Aspect.POSITIVE_SMALL) {
            return "alchemy7.png";
        }
        if(red == Aspect.NEGATIVE_BIG && green == Aspect.POSITIVE_SMALL && blue == Aspect.NEGATIVE_SMALL) {
            return "alchemy8.png";
        }

        return result;

    }
}
