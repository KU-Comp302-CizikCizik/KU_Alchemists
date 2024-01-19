package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.BuyArtifactHandler;
import com.KUAlchemists.backend.models.Artifact;
import com.KUAlchemists.backend.models.ArtifactShop;
import com.KUAlchemists.backend.handlers.SoundEffectHandler;
import com.KUAlchemists.backend.handlers.SoundHandler;
import com.KUAlchemists.backend.sound.SoundContrasts;
import com.KUAlchemists.backend.sound.SoundUI;
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

public class BuyArtifactController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text useButton;

    private ArrayList<Slot> artifactSlots;

    private int num = 0;

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
    void handleBuy(){
        for(Slot slot:artifactSlots){
            if(slot != null)
                if(slot.isSelected){
                    BuyArtifactHandler.getInstance().handleBuyArtifactRequest(slot.getId());
                    slot.setNormal();
                }
        }
        initializeHandler();
    }

    public void initialize(){
        artifactSlots  = new ArrayList<Slot>();
        List<String> artifactList = BuyArtifactHandler.getInstance().handleGetAllArtifacts();
        ObservableList<Node> childrenList = anchorPane.getChildren();

        for(int i = 0; i < artifactList.size(); i++){
            Pane pane = (Pane) childrenList.get(i);
            String id = artifactList.get(i);
            Slot slot = new Slot(pane, id);
            slot.setPrice(BuyArtifactHandler.getInstance().handleGetPrice(slot.getId()));
            artifactSlots.add(slot);
        }
        initializeHandler();


    }

    private void initializeHandler(){
        List<String> boughtArtifactList = BuyArtifactHandler.getInstance().handleBoughtArtifacts();
        //System.out.println(boughtArtifactList.toString());
        for(String artifact: boughtArtifactList){
            for(Slot slot: artifactSlots){
                if(slot.getId().equals(artifact)){
                    slot.setDisable();
                }
            }
        }
        List<Artifact> allArficats = ArtifactShop.getInstance().getArtifactsForSale();
        for(Slot slot : artifactSlots){
            boolean doesExist = false;
            for(Artifact arficat : allArficats){
                if(slot.getId().equals(arficat.getName())){
                    doesExist =true;
                    break;
                }
            }
            if(!doesExist)
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
            this.setStatus("Bought");
            this.getPane().setEffect(boxBlurEffect);
        }
        public void setSelected(){
            this.getPane().setEffect(glowEffectSelected);
            this.isSelected = true;
        }
        public void setHover(){
            this.getPane().setEffect(glowEffectHover);
        }
        public void setNormal(){
            this.getPane().setEffect(dropShadowEffect);
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
