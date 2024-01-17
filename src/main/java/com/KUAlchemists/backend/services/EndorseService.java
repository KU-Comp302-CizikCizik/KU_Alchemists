package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.strategy.AlchemicalNamingStrategy;
import com.KUAlchemists.backend.strategy.AlchemicalNamingStrategyMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EndorseService {

    private static Theory selectedTheory;

    public void EndorseService() {


    }

    public void setSelectedTheory(Theory theory) {
        selectedTheory = theory;
    }


    public ArrayList<String> getPlayerAvailableTheorySeals() {
        ArrayList<String> theorySeals =  GameEngine.getInstance().getCurrentPlayer().getTheorySeals()
                .stream()
                .map(TheorySeal::getSealString)
                .collect(Collectors.toCollection(ArrayList::new));
        Set<String> set = new HashSet<>(theorySeals);
        ArrayList<String> seals = new ArrayList<>(set);
        return seals;
    }

    public ArrayList<String> getEndorsedTheorySeals() {
        ArrayList<TheorySeal> theorySeals = selectedTheory.getTheorySeals();
        ArrayList<String> result = new ArrayList<>();
        for (TheorySeal theorySeal : theorySeals){
            result.add(theorySeal.getSealString());
        }
        return result;
    }

    public String getTheoryString() {
        String theory = selectedTheory.getIngredient().getName().toLowerCase();
        return theory;
    }

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

    /**
    public String getAlchemicalName() {
        String result = "not found";
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
     **/

    public String getAlchemicalName() {
        Aspect red = selectedTheory.getIngredient().getAlchemical().getRedAspect();
        Aspect green = selectedTheory.getIngredient().getAlchemical().getGreenAspect();
        Aspect blue = selectedTheory.getIngredient().getAlchemical().getBlueAspect();

        AlchemicalNamingStrategy strategy = AlchemicalNamingStrategyMap.getStrategy(red, green, blue);
        return strategy.getAlchemicalName(red, green, blue);
    }



}
