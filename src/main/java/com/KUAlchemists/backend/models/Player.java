package com.KUAlchemists.backend.models;


public class Player {

    private Inventory playerInventory;
    private int gold;
    private String status;
    private int sickness_level;
    private int reputation;
    private Theory publishedTheories;
    private DeductionBoard playersDeductionBoard;

    public Player(){
        this.playerInventory = new Inventory();
        this.gold = 0;
        this.status = "";
        this.sickness_level = 0;
        this.reputation = 0;
        this.publishedTheories = new Theory();
        this.playersDeductionBoard = new DeductionBoard();
    }


}
