package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.UseArtifactHandler;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ElixirOfInsightController {

    private final Effect glowEffect = new Glow(0.4);
    private final Effect glowEffectSelected = new Glow(0.6);
    private final Effect dropShadowEffect = new DropShadow();
    private final Effect boxBlurEffect = new BoxBlur(5, 5, 2);
    private UseArtifactHandler useArtifactHandler = UseArtifactHandler.getInstance();

    private final int FIRST = 0;
    private final int SECOND = 1;
    private final int THIRD = 2;



    public Pane ingredient1_pane;
    public Pane ingredient2_pane;
    public Pane ingredient3_pane;
    public ImageView ingredient1_image;
    public ImageView ingredient2_image;
    public ImageView ingredient3_image;

    public Text ingredient1_name;
    public Text ingredient2_name;
    public Text ingredient3_name;
    public ArrayList<Pane> paneList = new ArrayList<Pane>();
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    private ArrayList<ImageView> ingredientImages = new ArrayList<ImageView>();
    private ArrayList<Text> ingredientNames = new ArrayList<Text>();
    private javafx.scene.control.Button closeButton;

    private ArrayList<String> orderedIngredients = new ArrayList<String>();

    public void handleOrderButtonClick(){
        ArrayList<String> newTopTree = new ArrayList<String>();
        for(Ingredient ingredient: ingredients){
            newTopTree.add(ingredient.getName());
        }
        useArtifactHandler.useElixirOfInsight(newTopTree);
        for(Pane pane: paneList){
            pane.setEffect(boxBlurEffect);
            pane.setDisable(true);
        }
    }


    public void initialize(){
        ArrayList <String> topThree = useArtifactHandler.handlePeekTopThree();
        for (String artifact : topThree){
            ingredients.add(new Ingredient(artifact));
        }
        paneList.add(ingredient1_pane);
        paneList.add(ingredient2_pane);
        paneList.add(ingredient3_pane);

        ingredientImages.add(ingredient1_image);
        ingredientImages.add(ingredient2_image);
        ingredientImages.add(ingredient3_image);

        ingredientNames.add(ingredient1_name);
        ingredientNames.add(ingredient2_name);
        ingredientNames.add(ingredient3_name);

        updateIngredients();
    }

    public boolean checkSwap(){
        if(Ingredient.numOfSelected == 2){
            Ingredient.setNumOfSelected(0);
            return true;
        } return false;
    }

    public void swap(){
        if(checkSwap()){
            Ingredient ingredient1 = new Ingredient("");
            Ingredient ingredient2 = new Ingredient("");
            int i = 0;
            for(; i < ingredients.size(); i++){
                if(ingredients.get(i).isSelected()){
                    ingredient1 = ingredients.get(i);
                    break;
                }
            }
            int k = i+1;
            for (; k < ingredients.size(); k++){
                if(ingredients.get(k).isSelected()){
                    ingredient2 = ingredients.get(k);
                    break;
                }
            }
            for(Pane pane: paneList){
                pane.setEffect(dropShadowEffect);
            }
            for(Ingredient ingredient: ingredients){
                ingredient.setSelected(false);
            }

            ingredients.set(i, ingredient2);
            ingredients.set(k, ingredient1);
            updateIngredients();
        }
    }

    public void updateIngredients(){
        for(int i = 0; i < ingredients.size(); i++){
            String photoUrl = ingredients.get(i).getPhotoAddress();
            String name = ingredients.get(i).getName();
            try {
                Image image = new Image(getClass().getClassLoader().getResourceAsStream(photoUrl));
                ingredientImages.get(i).setImage(image);
                ingredientNames.get(i).setText(name);
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void ingredient1MouseEntered(){
        if(!ingredients.get(FIRST).isSelected){
            paneList.get(FIRST).setEffect(glowEffect);
        }
    }
    public void ingredient1MouseExited(MouseEvent mouseEvent) {
        if(!ingredients.get(FIRST).isSelected()){
            paneList.get(FIRST).setEffect(dropShadowEffect);
        }
    }

    public void ingredient1MouseClicked(MouseEvent mouseEvent) {
        if(!ingredients.get(FIRST).isSelected()){
            ingredients.get(FIRST).setSelected(true);
            paneList.get(FIRST).setEffect(glowEffectSelected);
            Ingredient.increaseNumOfSelected();
        }else{
            ingredients.get(FIRST).setSelected(false);
            ingredient1MouseEntered();
            Ingredient.decreaseNumOfSelected();
        }
        swap();
    }


    public void ingredient2MouseEntered() {
        if(!ingredients.get(SECOND).isSelected){
            paneList.get(SECOND).setEffect(glowEffect);
        }
    }
    public void ingredient2MouseExited(MouseEvent mouseEvent) {
        if(!ingredients.get(SECOND).isSelected()){
            paneList.get(SECOND).setEffect(dropShadowEffect);
        }
    }

    public void ingredient2MouseClicked(MouseEvent mouseEvent) {
        if(!ingredients.get(SECOND).isSelected()){
            ingredients.get(SECOND).setSelected(true);
            paneList.get(SECOND).setEffect(glowEffectSelected);
            Ingredient.increaseNumOfSelected();
        }else{
            ingredients.get(SECOND).setSelected(false);
            Ingredient.decreaseNumOfSelected();
            ingredient2MouseEntered();
        }
        swap();
    }


    public void ingredient3MouseEntered() {
        if(!ingredients.get(THIRD).isSelected){
            paneList.get(THIRD).setEffect(glowEffect);
        }
    }
    public void ingredient3MouseExited(MouseEvent mouseEvent) {
        if(!ingredients.get(THIRD).isSelected()){
            paneList.get(THIRD).setEffect(dropShadowEffect);
        }
    }
    public void ingredient3MouseClicked(MouseEvent mouseEvent) {
        if(!ingredients.get(THIRD).isSelected()){
            ingredients.get(THIRD).setSelected(true);
            paneList.get(THIRD).setEffect(glowEffectSelected);
            Ingredient.increaseNumOfSelected();
        }else{
            ingredients.get(THIRD).setSelected(false);
            Ingredient.decreaseNumOfSelected();
            ingredient3MouseEntered();
        }
        swap();
    }



    public void buttonClicked(MouseEvent mouseEvent) {
        handleOrderButtonClick();
    }

    private class Ingredient{
        private String name;
        private String photoName;
        private String photoAddress;
        private boolean isSelected;
        private static int numOfSelected;

        public Ingredient(String name) {
            this.name = name;
            this.photoName = name.toLowerCase()+"-ingredient.jpg";
            this.photoAddress = "com.KUAlchemists/images/" + photoName;
            this.isSelected = false;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhotoName() {
            return photoName;
        }

        public void setPhotoName(String photoName) {
            this.photoName = photoName;
        }

        public String getPhotoAddress() {
            return photoAddress;
        }

        public void setPhotoAddress(String photoAddress) {
            this.photoAddress = photoAddress;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public static void increaseNumOfSelected(){
            numOfSelected++;
        }
        public static void decreaseNumOfSelected(){
            numOfSelected--;
        }
        public static int getNumOfSelected(){
            return numOfSelected;
        }
        public static void setNumOfSelected(int newNum){
            numOfSelected = newNum;
        }

    }
}


