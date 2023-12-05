package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;

public class UseArtifactController {
    private final Effect glowEffect = new Glow(0.4);
    private final Effect glowEffectSelected = new Glow(0.6);
    private final Effect dropShadowEffect = new DropShadow();
    @FXML
    public Pane elixir_of_insight_con;
    public Pane philosophers_compass_con;

    public Pane hard_bargain_con;

    public void handleMouseEntered_eoi(){
        elixir_of_insight_con.setEffect(glowEffect);
    }
    public void handleMouseExited_eoi(){
        elixir_of_insight_con.setEffect(dropShadowEffect);
    }
    public void handleMouseClicked_eoi(){
        elixir_of_insight_con.setEffect(glowEffectSelected);
    }

    public void handleMouseEntered_pc(){
        philosophers_compass_con.setEffect(glowEffect);
    }
    public void handleMouseExited_pc(){
        philosophers_compass_con.setEffect(dropShadowEffect);
    }
    public void handleMouseClicked_pc(){
        philosophers_compass_con.setEffect(glowEffectSelected);
    }

    public void handleMouseEntered_hb(){
        hard_bargain_con.setEffect(glowEffect);
    }
    public void handleMouseExited_hb(){
        hard_bargain_con.setEffect(dropShadowEffect);
    }
    public void handleMouseClicked_hb(){
        hard_bargain_con.setEffect(glowEffectSelected);
    }
}
