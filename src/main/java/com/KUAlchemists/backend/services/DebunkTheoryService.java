package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;

import java.util.HashMap;


// Debunk Theory daha tam olarak bitmedi aspect alması gerekiyor.!
public class DebunkTheoryService {

    private final TheoryService theoryService;
    public DebunkTheoryService() {
        this.theoryService = new TheoryService();
    }
    /**
     * Attempts to debunk a theory.
     *
     * @param selectedTheory           the player attempting to debunk the theory.
     * @param actualIngredient
     * @return true if the theory was successfully debunked; false otherwise.
     */
    public boolean debunkTheory(Theory selectedTheory, String aspect, Ingredient actualIngredient) {
        Alchemical acutalAlchemical = actualIngredient.getAlchemical();
        Alchemical assertedAlchemical = selectedTheory.getIngredient().getAlchemical();
        Aspect actualAspect = getAspectByColor(aspect, acutalAlchemical);
        Aspect assertedAspect = getAspectByColor(aspect, assertedAlchemical);

        if(actualAspect == assertedAspect){
            //good reward
            //deduce one reputation point from the player who debunked the theory
            GameEngine.getInstance().getCurrentPlayer().deduceReputationPoints(1);
        }
        else{
            //deduce one reputation point from each player that has debunked the theory
            GameEngine.getInstance().getCurrentPlayer().deduceReputationPoints(-2);
            //remove the theory from the published theories
            GameEngine.getInstance().getCurrentPlayer().getPublishedTheories().remove(selectedTheory);
            //punish players who have endorsed the theory
            punishPlayers(selectedTheory,aspect);

        }

        GameEngine.getInstance().getCurrentPlayer().deduceActionPoints(1);
        return false;
    }

    private void punishPlayers(Theory selectedTheory, String assertedAspectString){
        TheorySeal correspondingSeal = getCorrespondingSeal(assertedAspectString);
        HashMap<Player, TheorySeal> playerTheorySealsMap = selectedTheory.getPlayerTheorySealsMap();
        for(Player player: playerTheorySealsMap.keySet()){
            TheorySeal theorySeal = playerTheorySealsMap.get(player);
            if(theorySeal == TheorySeal.GOLD_STARRED){
                player.deduceReputationPoints(5);
            }
            else if(theorySeal == TheorySeal.SILVER_STARRED){
                player.deduceReputationPoints(3);
            }
            else if (theorySeal != correspondingSeal){
                player.deduceReputationPoints(1);
            }

        }
        System.out.println("punishPlayers");
    }

    private TheorySeal getCorrespondingSeal(String assertedAspectString) {
        switch (assertedAspectString.toLowerCase()) {
            case "blue":
                return TheorySeal.BLUE_QUESTION;
            case "green":
                return TheorySeal.GREEN_QUESTION;
            case "red":
            default:
                return TheorySeal.RED_QUESTION;
        }
    }

    public Aspect getAspectByColor(String aspect, Alchemical alchemical) {
        switch (aspect.toLowerCase()) {
            case "blue":
                return alchemical.getBlueAspect();
            case "green":
                return alchemical.getGreenAspect();
            case "red":
            default:
                return alchemical.getRedAspect();
        }
    }



    public String getCorrespondingSignWithPath(String aspect, Alchemical alchemical) {
        if (aspect.equals("blue")) {
            if (alchemical.getBlueAspect().isPositive())
                return "bluePlus.png";
            else
                return "blueMinus.png";
        } else if (aspect.equals("green")) {
            if (alchemical.getGreenAspect().isPositive())
                return "greenPlus.png";
            else
                return "greenMinus.png";
        } else if (aspect.equals("red")) {
            if (alchemical.getRedAspect().isPositive())
                return "redPlus.png";
            else
                return "redMinus.png";
        }
        return null;
    }
}