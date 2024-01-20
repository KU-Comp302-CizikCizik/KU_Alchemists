package com.KUAlchemists.backend.models;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.PlayerSeal;
import com.KUAlchemists.backend.enums.TheorySeal;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.observer.Observer;
import com.KUAlchemists.backend.observer.PlayerObserver;
import com.KUAlchemists.backend.states.PlayerInitState;
import com.KUAlchemists.backend.states.PlayerState;
import com.KUAlchemists.backend.subjects.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Subject, Serializable {
    private int gold;
    private String status;
    private int sicknessLevel;
    private int reputation;
    private ArrayList<Theory> publishedTheories;
    private DeductionBoard deductionBoard;
    private int actionPoints;

    private int id;

    private boolean isIDInitializedbyHost;

    //To indicate its color on endorse UI, each player has only one, and it is randomly assigned
    private PlayerSeal seal;
  
    //To indicate the seal of the theory, each player has multiple, they put seals on theories
    private ArrayList<TheorySeal> theorySeals;
    private String name;

    private UserType userType;

    private int score;
    private List<PlayerObserver> observers;
    private String avatar; // this is the avatar of the player that will be displayed on the board
    private Map<String, Boolean> activatedArtifacts;


    public Player(){
        this("");
    }
    public Player(String name){
        this.gold = 20;
        this.status = "Healthy"; // Default status
        this.sicknessLevel = 0;
        this.reputation = 0;
        this.publishedTheories = new ArrayList<>();
        this.deductionBoard = new DeductionBoard();
        this.name = name;
        this.actionPoints = 5;
        this.seal = PlayerSeal.getRandomSeal(); //random seal for indicating the player's color on endorsement
        this.theorySeals = TheorySeal.getSeals(); //default seals
        observers = new ArrayList<>();
        this.id = 0;
        isIDInitializedbyHost = false;
        this.activatedArtifacts = new HashMap<>();
        userType = UserType.CLIENT; // does not matter since it will be updated by the network handler, not to cause any exceptions in offlineGame
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
        notifyObservers();
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getSicknessLevel() {
        return sicknessLevel;
    }
    public void setSicknessLevel(int sicknessLevel) {
        this.sicknessLevel = sicknessLevel;
        if(this.sicknessLevel < 0)this.sicknessLevel = 0;
        notifyObservers();
    }
    public int getReputation() {
        return reputation;
    }
    public void setReputation(int reputation) {
        this.reputation = reputation;
        notifyObservers();
    }
    public ArrayList<Theory> getPublishedTheories() {
        return publishedTheories;
    }
    public DeductionBoard getDeductionBoard() {
        return deductionBoard;
    }
    public void setPublishedTheories(ArrayList<Theory> publishedTheories) {
        this.publishedTheories = publishedTheories;
    }
    public String getName() {
        return name;
    }
    public void deduceActionPoints(int actionPoints) {
        this.actionPoints -= actionPoints;
        notifyObservers();
    }
    public Integer getActionPoints() {
        return actionPoints;
    }
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
        notifyObservers();
    }
    public void setPlayerSeal(PlayerSeal seal){
        this.seal = seal;
    }
    public PlayerSeal getPlayerSeal(){
        return seal;
    }

    public void setTheorySeals(ArrayList<TheorySeal> theorySeals){
        this.theorySeals = theorySeals;
        notifyObservers();
    }

    public ArrayList<TheorySeal> getTheorySeals(){
        return theorySeals;
    }

    public void removeTheorySeal(TheorySeal seal) {
        this.theorySeals.remove(seal);
    }

    public void addGold(int price) {
        this.gold += price;
        notifyObservers();
    }
      
    @Override
    public void registerObserver(Observer observer) {
        observers.add((PlayerObserver) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove((PlayerObserver) observer);
    }

    public int getIndex(){
        return GameEngine.getInstance().getPlayerIndex(this);
    }
    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            ((PlayerObserver) observer).onPlayerStatusChanged(status, getIndex());
            ((PlayerObserver) observer).onPlayerSicknessLevelChanged(sicknessLevel,getIndex());
            ((PlayerObserver) observer).onPlayerReputationChanged(reputation,getIndex());
            ((PlayerObserver) observer).onPlayerGoldChanged(gold,getIndex());
            ((PlayerObserver) observer).onPlayerActionPointsChanged(actionPoints,getIndex());
            ((PlayerObserver) observer).onPlayerNameChanged(name,getIndex());
        }

    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public String getAvatar(){
        return avatar;
    }


    public PlayerState getState(){
        return new PlayerState(id, gold, sicknessLevel, status, reputation, actionPoints, publishedTheories, theorySeals, deductionBoard, score, activatedArtifacts);
    }

    public PlayerInitState getInitState(){
        return new PlayerInitState(this);
    }

    public int getId() {
        return id;
    }

    public void deduceReputationPoints(int cost){
        this.reputation -= cost;
        notifyObservers();
    }

    public void increaseReputationPoints(int cost){
        this.reputation += cost;
        notifyObservers();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setName(String player1) {
        this.name = player1;

    }

    public void setPlayerID(int activePlayersCount) {
        this.id = activePlayersCount;
    }

    public void setUserType(UserType type) {
        this.userType = type;
    }

    public UserType getUserType() {
        return userType;
    }


    public void setIDInitializedbyHost(boolean isIDInitializedbyHost) {
        this.isIDInitializedbyHost = isIDInitializedbyHost;
    }

    public boolean isIDInitializedbyHost() {
        return isIDInitializedbyHost;
    }

    public void removeAllObservers() {
        observers.clear();
    }

    public void setDeductionBoard(DeductionBoard deductionBoard) {
      this.deductionBoard = deductionBoard;
      }

    // Artifact related methods to get activation status.
    public void activateArtifact(String artifactName) {
        activatedArtifacts.put(artifactName, true);
    }

    public boolean isArtifactActivated(String artifactName) {
        return activatedArtifacts.getOrDefault(artifactName, false);
    }
    public ArrayList<String> getActivatedArtifacts() {
        ArrayList<String> activatedArtifactNames = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : activatedArtifacts.entrySet()) {
            if (entry.getValue()) {
                activatedArtifactNames.add(entry.getKey());
            }
        }
        return activatedArtifactNames;
    }
    public void deactivateArtifact(String artifactName) {
        activatedArtifacts.put(artifactName, false);

    }

    public void setActivatedArtifacts(Map<String, Boolean> activatedArtifacts) {
        this.activatedArtifacts = activatedArtifacts;
    }

    public void removeActivedArtifact(String name) {
        activatedArtifacts.remove(name);
    }
}
