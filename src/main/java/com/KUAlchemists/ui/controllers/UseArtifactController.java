package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.UseArtifactHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class UseArtifactController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text useButton;

    private ArrayList<Slot> artifactSlots;

    private static final String ELIXIR_OF_INSIGHT = "elixir_of_insight";
    private static final String PHILOSOPHERS_COMPASS = "philosophers_compass";
    private static final String HARD_BARGAIN = "hard_bargain";

    private static final String WISDOM_IDOL = "wisdom_idol";
    private static final String MAGIC_MORTAR = "magic_mortar";
    private static final String PRINTING_PRESS = "printing_press";

    @FXML
    void handleMouseClicked(MouseEvent event) {
        Slot slot = getSlot((Pane) event.getSource());
        if(slot != null)
            if (!slot.isSelected) {
                slot.setSelected();
            } else{
                slot.setNormal();
            }
    }

    @FXML
    void handleMouseEntered(MouseEvent event) {
        Slot slot = getSlot((Pane) event.getSource());
        if(slot != null)
            if (!slot.isSelected) {
                slot.setHover();
            }
    }

    @FXML
    void handleMouseExited(MouseEvent event) {
        Slot slot = getSlot((Pane) event.getSource());
        if(slot != null)
            if(!slot.isSelected) {
                slot.setNormal();
            }
    }

    @FXML
    void handleUse(){
        for(Slot slot:artifactSlots){
            if(slot != null)
                if(slot.isSelected){
                    if(!UseArtifactHandler.getInstance().deduceActionPoint()) {
                        SceneLoader.getInstance().loadGenericPopUp("Not enough action point");
                        return;
                    }
                    if(slot.getId().equals(ELIXIR_OF_INSIGHT)) {
                        SceneLoader.getInstance().loadElixirOfInsight();
                        UseArtifactHandler.getInstance().handleRemoveArtifact(ELIXIR_OF_INSIGHT);
                    } else if (slot.getId().equals(WISDOM_IDOL)) {
                        UseArtifactHandler.getInstance().activateWisdomIdol();
                    } else if (slot.getId().equals(MAGIC_MORTAR)){
                        UseArtifactHandler.getInstance().activateMagicMortar();
                    } else if (slot.getId().equals(PRINTING_PRESS)){
                        UseArtifactHandler.getInstance().activatePrintingPress();
                    }
                    slot.setDisable();
                    slot.isSelected = false;
                }
        }
        initializeHelper();
    }

    public void initialize(){
        artifactSlots  = new ArrayList<Slot>();
        List<String> artifactList = UseArtifactHandler.getInstance().handleGetAllArtifacts();
        ObservableList<Node> childrenList = anchorPane.getChildren();

        for(int i = 0; i < artifactList.size(); i++){
            Pane pane = (Pane) childrenList.get(i);
            String id = artifactList.get(i);
            Slot slot = new Slot(pane, id);
            artifactSlots.add(slot);
        }
        initializeHelper();


    }

    private void initializeHelper(){
        List<String> usedArtifacts = UseArtifactHandler.getInstance().handleUsedArtifacts();
        List<String> boughtArtifacts = UseArtifactHandler.getInstance().handleStorageArtifact();
        List<String> activeArtifacts = UseArtifactHandler.getInstance().getActivatedArtifacts();

        resetUI();


        for(Slot slot: artifactSlots){
            slot.setDisable();
        }

        for(String artifact: boughtArtifacts){
            for(Slot slot: artifactSlots){
                if(slot.getId().equals(artifact) && !usedArtifacts.contains(slot.getId())){
                    slot.setEnable();
                }
            }
        }

        for(String artifact: activeArtifacts){
            for(Slot slot: artifactSlots){
                if(slot.getId().equals(artifact)){
                    slot.setActive();
                }
            }
        }
    }

    private void resetUI() {
        for(Slot slot: artifactSlots){
            slot.setDisable();
        }

    }

    private Slot getSlot(Pane pane){
        for(Slot slot:artifactSlots){
            if(slot.getPane() == pane){
                return slot;
            }
        }
        return null;
    }



    private class Slot{
        private static final int ARTIFACT_NAME = 1;
        private static final int ARTIFACT_INTRODUCTION = 2;
        private static final int ARTIFACT_USAGE = 3;
        private static final int ARTIFACT_PRICE = 4;
        private static final int ARTIFACT_STATUS = 5;

        private final Effect glowEffectHover = new Glow(0.4);
        private final Effect glowEffectSelected = new Glow(0.6);
        private final Effect glowEffectActive = new Glow(0.8);
        private final Effect dropShadowEffect = new DropShadow();
        private final Effect boxBlurEffect = new BoxBlur(5, 5, 2);


        private final Pane artifactPane;
        private Text artifactNameText;
        private Text artifactIntroductionText;
        private Text artifactUsageText;
        private Text artifactPriceText;
        private Text artifactStatusText;
        private boolean isSelected;
        private final String id;

        public Slot(Pane artifactPane, String id){
            this.artifactPane = artifactPane;
            this.id = id;
            initialize();
        }

        private void initialize(){
            ObservableList<Node> childrenList = artifactPane.getChildren();
            this.artifactNameText = (Text) childrenList.get(ARTIFACT_NAME);
            this.artifactIntroductionText = (Text) childrenList.get(ARTIFACT_INTRODUCTION);
            this.artifactUsageText = (Text) childrenList.get(ARTIFACT_USAGE);
            this.artifactPriceText = (Text) childrenList.get(ARTIFACT_PRICE);
            this.artifactStatusText = (Text) childrenList.get(ARTIFACT_STATUS);
        }

        public void setEnable(){
            this.getPane().setDisable(false);
            setNormal();
        }
        public void setDisable(){
            this.getPane().setDisable(true);
            this.setStatus("Cannot Use");
            this.getPane().setEffect(boxBlurEffect);
        }

        public void setInUse(){
            this.setStatus("In Use");
        }
        public void setSelected(){
            this.getPane().setEffect(glowEffectSelected);
            this.setStatus("Selected");
            this.isSelected = true;
        }

        public void setActive(){
            this.setStatus("Activated");
            this.getPane().setDisable(true);
            this.getPane().setEffect(glowEffectActive);
        }
        public void setHover(){
            this.getPane().setEffect(glowEffectHover);
        }
        public void setNormal(){
            this.getPane().setEffect(dropShadowEffect);
            this.setStatus("Ready to Use");
            this.isSelected = false;
        }

        public void setName(String name){
            this.getArtifactNameText().setText(name);
        }
        public void setIntroduction(String introduction){
            this.getArtifactIntroductionText().setText(introduction);
        }
        public void setUsage(String usage){
            this.getArtifactUsageText().setText(usage);
        }
        public void setPrice(int price){
            this.getArtifactPriceText().setText(String.valueOf(price) + " Gold");
        }
        public void setStatus(String status){
            this.getArtifactStatusText().setText(status);
        }

        public Pane getPane(){
            return this.artifactPane;
        }
        public Text getArtifactNameText() {
            return artifactNameText;
        }
        public Text getArtifactIntroductionText() {
            return artifactIntroductionText;
        }
        public Text getArtifactUsageText() {
            return artifactUsageText;
        }
        public Text getArtifactPriceText() {
            return artifactPriceText;
        }
        public Text getArtifactStatusText() {
            return artifactStatusText;
        }
        public String getId(){
            return id;
        }
        public boolean getIsSelected(){
            return isSelected;
        }
    }

}
