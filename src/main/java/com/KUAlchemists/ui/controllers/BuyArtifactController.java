package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BuyArtifactController {

    private boolean is_eoi_selected = false;
    private boolean is_pc_selected = false;
    private boolean is_hb_selected = false;

    private final Effect glowEffect = new Glow(0.4);
    private final Effect glowEffectSelected = new Glow(0.6);
    private final Effect dropShadowEffect = new DropShadow();

    @FXML
    public Pane elixir_of_insight_con;
    public Pane philosophers_compass_con;
    public Pane hard_bargain_con;
    public Text useButton;

    private ArrayList<String> allArtifacts = new ArrayList<String>();
    private ArrayList<String> boughtArtifacts = new ArrayList<String>();
    private ArrayList<String> selectedArtifacts = new ArrayList<String>();


    public void useArtifacts(){
        selectedArtifacts.clear();
        if(is_eoi_selected)
            selectedArtifacts.add("elixir_of_insight");
        if(is_pc_selected)
            selectedArtifacts.add("philosopher's_compass");
        if(is_hb_selected)
            selectedArtifacts.add("hard_bargain");
    }

    public ArrayList<String> getSelectedArtifacts(){
        return selectedArtifacts;
    }

    public void handleMouseEntered_eoi(){
        if (!is_eoi_selected) {
            elixir_of_insight_con.setEffect(glowEffect);
        }
    }
    public void handleMouseExited_eoi(){
        if(!is_eoi_selected) {
            elixir_of_insight_con.setEffect(dropShadowEffect);
        }
    }
    public void handleMouseClicked_eoi(){
        if (!is_eoi_selected) {
            is_eoi_selected = true;
            elixir_of_insight_con.setEffect(glowEffectSelected);
        } else{
            is_eoi_selected = false;
            handleMouseEntered_eoi();
        }
    }

    public void handleMouseEntered_pc(){
        if(!is_pc_selected)
            philosophers_compass_con.setEffect(glowEffect);
    }
    public void handleMouseExited_pc(){
        if(!is_pc_selected)
            philosophers_compass_con.setEffect(dropShadowEffect);
    }
    public void handleMouseClicked_pc(){
        if(!is_pc_selected){
            is_pc_selected = true;
            philosophers_compass_con.setEffect(glowEffectSelected);
        }else{
            is_pc_selected = false;
            handleMouseEntered_pc();
        }
    }

    public void handleMouseEntered_hb(){
        if(!is_hb_selected)
            hard_bargain_con.setEffect(glowEffect);
    }
    public void handleMouseExited_hb(){
        if(!is_hb_selected)
            hard_bargain_con.setEffect(dropShadowEffect);
    }
    public void handleMouseClicked_hb(){

        if(!is_hb_selected){

            is_hb_selected = true;
            hard_bargain_con.setEffect(glowEffectSelected);
        }
        else{
            is_hb_selected = false;
            handleMouseEntered_hb();
        }
    }

    public void handleMouseEnteredUseButton(){
        useButton.setFill(Color.web("#f5dc6a"));
    }
    public void handleMouseExitedUseButton(){
        useButton.setFill(Color.web("#db9e42"));
    }
    public void handleMouseClickedUseButton(){
        useButton.setFill(Color.web("#b6651d"));
        useArtifacts();
        handleMouseEnteredUseButton();
        System.out.println(selectedArtifacts);
    }

}
