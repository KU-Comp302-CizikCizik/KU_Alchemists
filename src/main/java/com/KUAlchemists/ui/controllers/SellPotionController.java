package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.SellPotionHandler;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SellPotionController {

    private final Effect glowEffect = new Glow(0.4);
    private final Effect glowEffectSelected = new Glow(0.6);
    private final Effect dropShadowEffect = new DropShadow();
    private final Effect boxBlurEffect = new BoxBlur(5, 5, 2);


    public ImageView adventurerImageView;
    public Text dialogText;

    public Text button;
    public Pane pane1, pane2, pane3, pane4, pane5, pane6, pane7, pane8, pane9, pane10, pane11, pane12, pane13, pane14;
    public ImageView imageView;
    public ArrayList<Pane> paneList;


    private ArrayList<String> usersPotions;
    private ArrayList<PotionSlot> potionSlots = new ArrayList<>();
    private Scenerio scenerio;
    private int scenerioIndex;

    private ArrayList<String> positivePotions = new ArrayList<>();
    private ArrayList<String> negativePotions = new ArrayList<>();

    private boolean isAnySelected;

    public void initialize(){
        scenerioIndex = 1;
        usersPotions = SellPotionHandler.getInstance().handlerUsersPotions();
        paneList = new ArrayList<Pane>();
        paneList.add(pane1);paneList.add(pane2);paneList.add(pane3);paneList.add(pane4);paneList.add(pane5);
        paneList.add(pane6);paneList.add(pane7);paneList.add(pane8);paneList.add(pane9);paneList.add(pane10);
        paneList.add(pane11);paneList.add(pane12);paneList.add(pane13);paneList.add(pane14);

        positivePotions.add("HEALING_POTION");
        positivePotions.add("SPEED_POTION");
        positivePotions.add("WISDOM_POTION");

        negativePotions.add("INSANITY_POTION");
        negativePotions.add("PARALYSIS_POTION");
        negativePotions.add("POISON_POTION");

        for(int i = 0; i < usersPotions.size(); i++){
            String potionName = usersPotions.get(i);

            PotionSlot slot = new PotionSlot(paneList.get(i), potionName, learnPotionType(potionName));
            potionSlots.add(slot);
        }
        scenerio = new Scenerio(adventurerImageView, dialogText, button);
        isAnySelected = false;

        buttonClicked();
    }

    public String learnPotionType(String potionName){
        String type;
        if(positivePotions.contains(potionName))
            type = "positive";
        else if(negativePotions.contains(potionName))
            type = "negative";
        else
            type = "neutral";
        return type;
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        PotionSlot relevantSlot = null;
        Pane source = (Pane) mouseEvent.getSource();
        for(PotionSlot slot: potionSlots){
            if(slot.getMainPane() == source){
                relevantSlot = slot;
            }
        }
        if(!relevantSlot.isSelected() && !isAnySelected){
            relevantSlot.getMainPane().setEffect(glowEffectSelected);
            relevantSlot.select();
            isAnySelected = true;
        }else if(relevantSlot.isSelected()){
            relevantSlot.getMainPane().setEffect(dropShadowEffect);
            relevantSlot.deselect();
            isAnySelected=false;
            mouseEntered(mouseEvent);
        }
    }

    public void buttonClicked(){
        if(SellPotionHandler.getInstance().handleIsSkipDialog() && scenerioIndex< 3){
            scenerioIndex = 3;
        }

        switch (scenerioIndex){
            case 1:
                scenerio.runScene1();
                scenerioIndex++;
                break;
            case 2:
                scenerio.runScene2();
                scenerioIndex++;
                break;
            case 3:
                scenerio.runScene3();
                scenerioIndex++;
                break;
            case 4:
                scenerio.runScene4(potionSlots);
                scenerioIndex++;
                SellPotionHandler.getInstance().handleSkipDialog();
                break;
        }
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        PotionSlot relevantSlot = null;
        Pane source = (Pane) mouseEvent.getSource();
        for(PotionSlot slot: potionSlots){
            if(slot.getMainPane() == source){
                relevantSlot = slot;
            }
        }
        if(!relevantSlot.isSelected()){
            relevantSlot.getMainPane().setEffect(glowEffect);
        }

    }

    public void mouseExited(MouseEvent mouseEvent) {
        PotionSlot relevantSlot = null;
        Pane source = (Pane) mouseEvent.getSource();
        for(PotionSlot slot: potionSlots){
            if(slot.getMainPane() == source){
                relevantSlot = slot;
            }
        }
        if(!relevantSlot.isSelected()){
            relevantSlot.getMainPane().setEffect(dropShadowEffect);
        }
    }

    private void debug(String message){
        System.out.println(message);
    }


    private class Scenerio{
        private ImageView adventurerImageView;
        private Text dialogText, button;

        private String scene1Dialog = "Hello, I am el-Sistam, the famous adventurer here." +
                " I frequently come here to bu some potions from alchemists, and make them rich.";

        private String scene2Dialog = "I have heard that you have some potions, would you like to sell me some?";
        private String scene3Dialog = "Before we go on, think twice! Since i cannot know what is the type of the potion" +
                " you ar selling me, i have to drink and test it. I trust you when i buy it, but if you lie, it may not" +
                " end well for you. You will lose some reputation in this ares.\nAgain, i am el-Sistam," +
                " the famous adventurer here, i have that power!";
        private String scene5Dialog = "You lied! You lost some reputation points. I will be around here since you may" +
                " have some potions. I might buy some more even if you lost my trust";
        private String scene6Dialog = "What a great sale, here is your money. " +
                "I will be arround since you have potions to sale. I may buy some more, and make you even richer";

        private String scene1Button = "Continue";
        private String scene2Button = "Yes";
        private String scene3Button = "Understood, check the inventory";
        private String scene4Button = "Offer a price";

        private String SCENE_1_ADV_PHOTO_NAME = "adventurer-photo-1";
        private String SCENE_2_ADV_PHOTO_NAME = "adventurer-photo-2";
        private String SCENE_3_ADV_PHOTO_NAME = "adventurer-photo-3";
        private String SCENE_5_ADV_PHOTO_NAME = "adventurer-photo-4";
        private String SCENE_6_ADV_PHOTO_NAME = "adventurer-photo-5";
        public Scenerio(ImageView adventurerImageView, Text dialogText, Text button){
            this.adventurerImageView = adventurerImageView;
            this.dialogText = dialogText;
            this.button = button;

        }
        public void runScene1(){
            getDialogText().setText(scene1Dialog);
            getButton().setText(scene1Button);
            getAdventurerImageView().setImage(getImage(SCENE_1_ADV_PHOTO_NAME));
        }
        public void runScene2(){
            getDialogText().setText(scene2Dialog);
            getButton().setText(scene2Button);
            getAdventurerImageView().setImage(getImage(SCENE_2_ADV_PHOTO_NAME));
        }
        public void runScene3(){
            getDialogText().setText(scene3Dialog);
            getButton().setText(scene3Button);
            getAdventurerImageView().setImage(getImage(SCENE_3_ADV_PHOTO_NAME));
        }
        public void runScene4(ArrayList<PotionSlot> potionSlots){
            getDialogText().setText(null);
            getButton().setText(scene4Button);
            getAdventurerImageView().setImage(getImage(null));
            for(PotionSlot slot : potionSlots){
                slot.show();
            }

        }
        public void runScene5(){
            getDialogText().setText(scene5Dialog);
            getButton().setText(null);
            getButton().setVisible(false);
            getButton().setDisable(true);
            getAdventurerImageView().setImage(getImage(SCENE_5_ADV_PHOTO_NAME));
        }
        public void runScene6(){
            getDialogText().setText(scene6Dialog);
            getButton().setText(null);
            getButton().setVisible(false);
            getButton().setDisable(true);
            getAdventurerImageView().setImage(getImage(SCENE_6_ADV_PHOTO_NAME));
        }

        public ImageView getAdventurerImageView() {
            return adventurerImageView;
        }

        public Text getDialogText() {
            return dialogText;
        }

        public Text getButton() {
            return button;
        }

        private Image getImage(String imageName){
            String imagePath = "com.KUAlchemists/images/adventurer/" + imageName+".png";
            // Load the image using the class loader to ensure it works regardless of the build type
            try {
                Image image = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));
                return image;
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
            return null;
        }
    }
    private class PotionSlot{
        private Pane mainPane;
        private Image potionImage;
        private String potionName;
        private String potionType;

        private boolean isSelected;


        public PotionSlot(Pane mainPane, String potionName, String potionType) {
            this.mainPane = mainPane;
            this.potionName = potionName + " potion";
            this.potionType = "Type: "+potionType;
            this.isSelected = false;
            setPotionImage(potionName);
            refreshPane();
        }

        private void refreshPane(){
            ((ImageView) getMainPane().getChildren().get(0)).setImage(getPotionImage());
            ((Text) getMainPane().getChildren().get(1)).setText(getPotionName());
            ((Text) getMainPane().getChildren().get(2)).setText(getPotionType());
        }

        private void show(){
            getMainPane().setVisible(true);
        }
        private void hide(){
            getMainPane().setVisible(false);
        }
        private void select(){
            this.isSelected = true;
        }
        private void deselect(){
            this.isSelected = false;
        }
        private boolean isSelected(){
            return isSelected;
        }

        public Pane getMainPane() {
            return mainPane;
        }

        public void setMainPane(Pane mainPane) {
            this.mainPane = mainPane;
            refreshPane();
        }

        public Image getPotionImage() {
            return potionImage;
        }

        public void setPotionImage(String potionImageName) {
            this.potionImage = getImage(potionImageName);
            refreshPane();
        }

        public String getPotionName() {
            return potionName;
        }

        public void setPotionName(String potionName) {
            this.potionName = potionName;
            refreshPane();
        }

        public String getPotionType() {
            return potionType;
        }

        public void setPotionType(String potionType) {
            this.potionType = potionType;
            refreshPane();
        }
        private Image getImage(String imageName){
            String imagePath = "com.KUAlchemists/images/potions/" + imageName.toUpperCase() + ".png";
            // Load the image using the class loader to ensure it works regardless of the build type
            try {
                Image image = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));
                return image;
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
            return null;
        }
    }
}
