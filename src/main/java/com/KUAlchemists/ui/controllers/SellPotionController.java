package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.SellPotionHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SellPotionController{

    private final Effect glowEffect = new Glow(0.4);
    private final Effect glowEffectSelected = new Glow(0.6);
    private final Effect dropShadowEffect = new DropShadow();
    private final Effect boxBlurEffect = new BoxBlur(5, 5, 2);


    public ImageView adventurerImageView;
    public Text dialogText;

    public Text button;
    public Pane pane1, pane2, pane3, pane4, pane5, pane6, pane7, pane8, pane9, pane10, pane11, pane12, pane13, pane14;
    public ImageView imageView;
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button cancelButton;

    @FXML
    private AnchorPane offerPriceAnchorPane;

    @FXML
    private ImageView potionImage;

    @FXML
    private Text potionName;

    @FXML
    private Text potionType;

    @FXML
    private Text recommendedPrice;

    public ArrayList<Pane> paneList;
    private ArrayList<String> usersPotions;
    private ArrayList<PotionSlot> potionSlots = new ArrayList<>();
    private Scenerio scenerio;
    private int scenerioIndex;
    private boolean isAnySelected;
    private String[] potion;
    private String recomPrice;


    public void initialize(){
        scenerioIndex = 1;
        usersPotions = SellPotionHandler.getInstance().handleGetPlayersPotions();
        for (String potion: usersPotions){
            System.out.println(potion);
        }
        paneList = new ArrayList<Pane>();
        paneList.add(pane1);paneList.add(pane2);paneList.add(pane3);paneList.add(pane4);paneList.add(pane5);
        paneList.add(pane6);paneList.add(pane7);paneList.add(pane8);paneList.add(pane9);paneList.add(pane10);
        paneList.add(pane11);paneList.add(pane12);paneList.add(pane13);paneList.add(pane14);

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
        return SellPotionHandler.getInstance().handleGetPotionType(potionName).toLowerCase();
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
                scenerio.runScene4(potionSlots, offerPriceAnchorPane);
                scenerioIndex++;
                SellPotionHandler.getInstance().handleSkipDialog();
                break;
            case 5:
                if(SellPotionHandler.getInstance().getStatus() == null || SellPotionHandler.getInstance().getStatus() == "cancel") {
                    if (getSelectedPotion(potionSlots) != null) {
                        SellPotionHandler.getInstance().setPotionToBeSelled(getSelectedPotion(potionSlots));
                        potion = SellPotionHandler.getInstance().getPotionToBeSelled();

                        recomPrice = (potion[1].equals("Type: neutral"))?"2 Golds":"1 Gold";
                        recomPrice = (potion[1].equals("Type: positive"))? "3 Golds" : recomPrice;
                        System.out.println(potion[1]);

                        String imagePath = "/com.KUAlchemists/images/potions/" + potion[0] + "_POTION.png";
                        potionName.setText(capitalizeWords(potion[0]));
                        potionType.setText(potion[1]);
                        recommendedPrice.setText("Recommended: "+recomPrice);


                        // Load the image using the class loader to ensure it works regardless of the build type
                        try {
                            Image image = new Image(getClass().getResourceAsStream(imagePath));
                            potionImage.setImage(image);
                        }catch (Exception e){
                            System.err.println(e.getMessage());
                        }
                        scenerio.runSceneOfferPrice(offerPriceAnchorPane);
                    }
                }else{
                    if (SellPotionHandler.getInstance().getStatus().equals("bad")) {
                        scenerio.runScene5(potionSlots, offerPriceAnchorPane);
                    } else if (SellPotionHandler.getInstance().getStatus().equals("good")) {
                        scenerio.runScene6(potionSlots, offerPriceAnchorPane);
                    }
                    SellPotionHandler.getInstance().setStatusNull();
                }
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

    @FXML
    void sellPotionButton(ActionEvent event) {
        if (event.getSource() == button1) {
            sellThePotion(1);
        }
        else if (event.getSource() == button2){
            sellThePotion(2);
        } else if (event.getSource() == button3) {
            sellThePotion(3);
        } else if (event.getSource() == cancelButton) {
            SellPotionHandler.getInstance().setStatusCancelled();
            scenerioIndex = 4;
        }
        buttonClicked();
    }

    private void sellThePotion(int price){
        if(potion[1].equals("Type: negative") && price > 1){
            SellPotionHandler.getInstance().setStatusBad();
        }else if(potion[1].equals("Type: neutral") && price > 2){
            SellPotionHandler.getInstance().setStatusBad();
        }else{
            SellPotionHandler.getInstance().setStatusGood();
        }
        SellPotionHandler.getInstance().handleSellPotion(potion[0], price);
    }

    private void closePopup(){
        final Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }
    private String capitalizeWords(String oldString){
        String[] words = oldString.replace("_", " ").toLowerCase().split(" ");
        String newString = "";
        for(String word: words){
            word = word.substring(0,1).toUpperCase() + word.substring(1);
            newString+=word+" ";
        }
        return newString.substring(0, newString.length()-1);
    }


    private void debug(String message){
        System.out.println(message);
    }

    private String[] getSelectedPotion(ArrayList<PotionSlot> potionSlots){
        for(PotionSlot slot: potionSlots){
            if(slot.isSelected == true){
                return new String[]{slot.potionName, slot.potionType};
            }
        }
        return null;
    }



    private class Scenerio{
        private ImageView adventurerImageView;
        private Text dialogText, button;

        private AnchorPane offerPriceAnchorPane;

        private String scene1Dialog = "Hello, I am el-Sistam, the famous adventurer here." +
                " I frequently come here to bu some potions from alchemists, and make them rich.";

        private String scene2Dialog = "I have heard that you have some potions, would you like to sell me some?";
        private String scene3Dialog = "Before we go on, think twice! Since i cannot know what is the type of the potion" +
                " you ar selling me, i have to drink and test it. I trust you when i buy it, but if you lie, it may not" +
                " end well for you. You will lose some reputation in this area.\nI am el-Sista'm," +
                " I have that power!";
        private String scene5Dialog = "You lied! You lost some reputation points. I will be around here since you may" +
                " have some potions. I might buy some more even if you lost my trust";
        private String scene6Dialog = "What a great sale, here is your money. " +
                "I will be around since you have potions to sale. I may buy some more, and make you even richer";

        private String scene1Button = "Continue";
        private String scene2Button = "Yes";
        private String scene3Button = "Understood";
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
        public void runScene4(ArrayList<PotionSlot> potionSlots, AnchorPane anchorPane){
            getDialogText().setText(null);
            getButton().setText(scene4Button);
            anchorPane.setVisible(false);
            getButton().setVisible(true);

            getAdventurerImageView().setImage(getImage(null));
            for(PotionSlot slot : potionSlots){
                slot.show();
            }
        }
        public void runScene5(ArrayList<PotionSlot> potionSlots, AnchorPane anchorPane){
            for(PotionSlot slot: potionSlots){
                slot.hide();
            }
            getDialogText().setText(scene5Dialog);
            getButton().setVisible(false);
            anchorPane.setVisible(false);
            getAdventurerImageView().setImage(getImage(SCENE_5_ADV_PHOTO_NAME));
        }
        public void runScene6(ArrayList<PotionSlot> potionSlots, AnchorPane anchorPane){
            for(PotionSlot slot: potionSlots){
                slot.hide();
            }
            getDialogText().setText(scene6Dialog);
            getButton().setVisible(false);
            anchorPane.setVisible(false);
            getAdventurerImageView().setImage(getImage(SCENE_6_ADV_PHOTO_NAME));
        }

        public void runSceneOfferPrice(AnchorPane anchorPane){
            for(PotionSlot slot: potionSlots){
                slot.hide();
            }
            getDialogText().setText("");
            getButton().setVisible(false);
            anchorPane.setVisible(true);
            getAdventurerImageView().setImage(null);
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
            String imagePath = "/com.KUAlchemists/images/adventurer/" + imageName+".png";
            // Load the image using the class loader to ensure it works regardless of the build type
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
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
            this.potionName = potionName;
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


        private String capitalizeWords(String oldString){
            String[] words = oldString.replace("_", " ").toLowerCase().split(" ");
            String newString = "";
            for(String word: words){
                word = word.substring(0,1).toUpperCase() + word.substring(1);
                newString+=word+" ";
            }
            return newString.substring(0, newString.length()-1);
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
            return capitalizeWords(this.potionName);
        }
        public String getPotionRawName(){
            return this.potionName;
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
            String imagePath = "/com.KUAlchemists/images/potions/" + imageName.toUpperCase() + "_POTION.png";
            // Load the image using the class loader to ensure it works regardless of the build type
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                return image;
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
            return null;
        }
    }
}
