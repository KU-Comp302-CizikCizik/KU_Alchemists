package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.PublicationTrackObserver;
import com.KUAlchemists.backend.services.EndorseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EndorseHandler implements PublicationTrackObserver {


    private Theory selectedTheory; //the theory that the player wants to endorse
    private static EndorseHandler instance; //singleton instance


    private EndorseService endorseService;
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
        endorseService = new EndorseService();

    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return seals
     */
    public ArrayList<String> getPlayerAvailableTheorySeals() {
        return endorseService.getPlayerAvailableTheorySeals();
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return playerSeals
     */
    public ArrayList<String> getEndorsedTheorySeals() {
        return endorseService.getEndorsedTheorySeals();
    }


    public boolean isCurrentPlayerAuthor(){
        return GameEngine.getInstance().getCurrentPlayer().getPublishedTheories().contains(selectedTheory);
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @param sealName
     */
    public void saveEndorsedSeal(String sealName) {
        TheorySeal seal = TheorySeal.getSealByName(sealName);
        GameEngine.getInstance().getCurrentPlayer().removeTheorySeal(seal);
        selectedTheory.addEndorser(GameEngine.getInstance().getCurrentPlayer(),seal);
        Board.getInstance().updateTheTheory(selectedTheory);
        GameEngine.getInstance().getCurrentPlayer().deduceActionPoints(1);
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return theory
     */
    public String getTheoryString() {
        return endorseService.getTheoryString();
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @param theory
     */
    @Override
    public void onTheorySelected(Theory theory) {
        this.selectedTheory = theory;
        endorseService.setSelectedTheory(theory);
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return playerSeal
     */
    public String getPlayerSeal() {
        return endorseService.getPlayerSeal();
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return playerSeals
     */
    public ArrayList<String> getEndorsedPlayerSeals() {
        return endorseService.getEndorsedPlayerSeals();
    }

    /**
     * This method is called when the player clicks on the endorse button
     * @return reputation
     */
    public String getAlchemicalName() {
        return endorseService.getAlchemicalName();
    }
}
