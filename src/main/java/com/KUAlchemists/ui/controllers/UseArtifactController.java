package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.BuyArtifactHandler;
import com.KUAlchemists.backend.handlers.UseArtifactHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class UseArtifactController {

    private boolean is_eoi_selected = false;
    private boolean is_pc_selected = false;
    private boolean is_hb_selected = false;

    public static final String ELIXIR_OF_INSIGHT = "elixir_of_insight";
    public static final String PHILOSOPHERS_COMPASS = "philosophers_compass";
    public static final String HARD_BARGAIN = "hard_bargain";

    private final Effect glowEffect = new Glow(0.4);
    private final Effect glowEffectSelected = new Glow(0.6);
    private final Effect dropShadowEffect = new DropShadow();
    private final Effect boxBlurEffect = new BoxBlur(5, 5, 2);


    private UseArtifactHandler useArtifactHandler = UseArtifactHandler.getInstance();

    @FXML
    public Pane elixir_of_insight_con;
    public Pane philosophers_compass_con;
    public Pane hard_bargain_con;
    public Text useButton;

    private ArrayList<String> allArtifacts = new ArrayList<String>();
    private ArrayList<String> boughtArtifacts = new ArrayList<String>();
    private ArrayList<String> selectedArtifacts = new ArrayList<String>();


    public void useArtifacts(){
        updateSelectedArtifacts();
        for(String artifact: selectedArtifacts) {
            if (artifact.equals(ELIXIR_OF_INSIGHT)){
                useArtifactHandler.handleRemoveArtifact(ELIXIR_OF_INSIGHT);
                SceneLoader.getInstance().loadElixirOfInsight();
            }
        }

        //Alperen burası
        boughtArtifacts = (ArrayList<String>) useArtifactHandler.handleStorageArtifact();
        unselectArtifacts();
        setArtifactDisability();
    }

    public void initialize(){
        boughtArtifacts = (ArrayList<String>) useArtifactHandler.handleStorageArtifact();
        BuyArtifactHandler buyArtifactHandler = new BuyArtifactHandler();
        System.out.println(boughtArtifacts);
        setArtifactDisability();
    }

    public void setArtifactDisability(){
        elixir_of_insight_con.setEffect(boxBlurEffect);
        elixir_of_insight_con.setDisable(true);
        philosophers_compass_con.setEffect(boxBlurEffect);
        philosophers_compass_con.setDisable(true);
        hard_bargain_con.setEffect(boxBlurEffect);
        hard_bargain_con.setDisable(true);
        for(String artifact : boughtArtifacts){
            switch (artifact){
                case ELIXIR_OF_INSIGHT:
                    elixir_of_insight_con.setEffect(dropShadowEffect);
                    elixir_of_insight_con.setDisable(false);
                    break;
                case PHILOSOPHERS_COMPASS:
                    philosophers_compass_con.setEffect(dropShadowEffect);
                    philosophers_compass_con.setDisable(false);
                    break;
                case HARD_BARGAIN:
                    hard_bargain_con.setEffect(dropShadowEffect);
                    hard_bargain_con.setDisable(false);
                    break;
            }
        }
    }

    public void updateSelectedArtifacts(){
        selectedArtifacts.clear();
        if(is_eoi_selected)
            selectedArtifacts.add(ELIXIR_OF_INSIGHT);
        elixir_of_insight_con.setEffect(dropShadowEffect);
        if(is_pc_selected)
            selectedArtifacts.add(PHILOSOPHERS_COMPASS);
        elixir_of_insight_con.setEffect(dropShadowEffect);
        if(is_hb_selected)
            selectedArtifacts.add(HARD_BARGAIN);
        elixir_of_insight_con.setEffect(dropShadowEffect);
    }

    public void unselectArtifacts(){
        is_eoi_selected=false;
        is_hb_selected = false;
        is_pc_selected = false;
        updateSelectedArtifacts();
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

    }

}