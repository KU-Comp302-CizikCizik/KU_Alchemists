package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.PotionBrewingAreaHandler;
import com.KUAlchemists.backend.handlers.PotionStorageHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class PotionBrewController {
    @FXML
    private ImageView ingredient_image1;

    @FXML
    private ImageView ingredient_image2;

    @FXML
    private ImageView ingredient_image3;

    @FXML
    private ImageView ingredient_image4;

    @FXML
    private ImageView ingredient_image5;

    @FXML
    private ImageView selected_ingredient1;

    @FXML
    private ImageView selected_ingredient2;

    @FXML
    private ImageView potion_1;

    public Pane ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, slot1, slot2, potion;
    boolean isIngredient1Selected = false, isIngredient2Selected = false, isIngredient3Selected = false, isIngredient4Selected = false, isIngredient5Selected = false;
    public Text brewButton;

    private ArrayList<Pane> uiIngredientList = new ArrayList<Pane>();
    private ArrayList<String> selectedIngredients = new ArrayList<String>();

    private int numOfSelected = 0;

    private final Effect glowEffect = new Glow(0.4);
    private final Effect glowEffectSelected = new Glow(0.6);
    private final Effect dropShadowEffect = new DropShadow();

    private HashMap<ImageView, String> ingredientMap;


    private ArrayList<String> ingredientList = new ArrayList<String>();


    public void actionPerformed(){

        if(ingredientList.size() == 0){
            SceneLoader.getInstance().loadGenericPopUp("No ingredients available!");
        }
        else{
            disableElements();
            selectIngredients();
            PotionBrewingAreaHandler.getInstance().setIngredientsToBeBrewed(selectedIngredients.get(0), selectedIngredients.get(1));
            if(PotionBrewingAreaHandler.getInstance().isMagicMortarActivated()){
                SceneLoader.getInstance().loadMagicMortarArtifact();
            }
            else{
                SceneLoader.getInstance().loadMakeExperiment();

            }
        }
    }

    @FXML
    private void initialize(){
        //Temporarely disabled
        potion.setDisable(true);
        potion.setVisible(false);

        ingredientList = PotionBrewingAreaHandler.getInstance().getIngredientList();
        ingredientMap =  new HashMap<ImageView, String>();
        for(int i = 0; i < ingredientList.size(); i++){

            //getting the target image view
            String targetImageViewName = "ingredient_image" + (i+1);
            Field field = null;
            ImageView targetImageView = null;
            try {
                field = getClass().getDeclaredField(targetImageViewName);
                field.setAccessible(true);
                targetImageView = (ImageView) field.get(this);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }


            String imagePath = "/com.KUAlchemists/images/" + ingredientList.get(i) + "-ingredient-square.png";
            // Load the image using the class loader to ensure it works regardless of the build type

            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                // Set the image to the ImageView
                targetImageView.setImage(image);
                ingredientMap.put(targetImageView, ingredientList.get(i));

            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }

    }

    private void disableElements(){
        if(isIngredient1Selected){
            ingredient1.setVisible(false);
        }if(isIngredient2Selected){
            ingredient2.setVisible(false);
        }if(isIngredient3Selected){
            ingredient3.setVisible(false);
        }if(isIngredient4Selected){
            ingredient4.setVisible(false);
        }if(isIngredient5Selected){
            ingredient5.setVisible(false);
        }
        numOfSelected = 0;
    }

    public void hmEnteredIngredient1(){
        if(!isIngredient1Selected){
            ingredient1.setEffect(glowEffect);
        }
    }

    public void hmExitedIngredient1(){
        if(!isIngredient1Selected){
            ingredient1.setEffect(dropShadowEffect);
        }
    }

    public void hmClickedIngredient1(){
        if(!isIngredient1Selected && numOfSelected != 2){
            isIngredient1Selected = true;
            numOfSelected++;
            ingredient1.setEffect(glowEffectSelected);
        }else if(isIngredient1Selected){
            isIngredient1Selected = false;
            numOfSelected--;
            hmEnteredIngredient1();
        }
    }


    public void hmEnteredIngredient2(){
        if(!isIngredient2Selected){
            ingredient2.setEffect(glowEffect);
        }
    }
    public void hmExitedIngredient2(){
        if(!isIngredient2Selected){
            ingredient2.setEffect(dropShadowEffect);
        }
    }
    public void hmClickedIngredient2(){
        if(!isIngredient2Selected && numOfSelected != 2){
            isIngredient2Selected = true;
            numOfSelected++;
            ingredient2.setEffect(glowEffectSelected);
        }else if(isIngredient2Selected){
            isIngredient2Selected = false;
            numOfSelected--;
            hmEnteredIngredient2();
        }
    }


    public void hmEnteredIngredient3(){
        if(!isIngredient3Selected){
            ingredient3.setEffect(glowEffect);
        }
    }
    public void hmExitedIngredient3(){
        if(!isIngredient3Selected){
            ingredient3.setEffect(dropShadowEffect);
        }
    }
    public void hmClickedIngredient3(){
        if(!isIngredient3Selected && numOfSelected != 2){
            isIngredient3Selected = true;
            numOfSelected++;
            ingredient3.setEffect(glowEffectSelected);
        }else if(isIngredient3Selected){
            isIngredient3Selected = false;
            numOfSelected--;
            hmEnteredIngredient3();
        }
    }


    public void hmEnteredIngredient4(){
        if(!isIngredient4Selected){
            ingredient4.setEffect(glowEffect);
        }
    }
    public void hmExitedIngredient4(){
        if(!isIngredient4Selected){
            ingredient4.setEffect(dropShadowEffect);
        }
    }
    public void hmClickedIngredient4(){
        if(!isIngredient4Selected && numOfSelected != 2){
            isIngredient4Selected = true;
            numOfSelected++;
            ingredient4.setEffect(glowEffectSelected);
        }else if(isIngredient4Selected){
            isIngredient4Selected = false;
            numOfSelected--;
            hmEnteredIngredient4();
        }
    }


    public void hmEnteredIngredient5(){
        if(!isIngredient5Selected){
            ingredient5.setEffect(glowEffect);
        }
    }
    public void hmExitedIngredient5(){
        if(!isIngredient5Selected){
            ingredient5.setEffect(dropShadowEffect);
        }
    }
    public void hmClickedIngredient5(){
        if(!isIngredient5Selected && numOfSelected != 2){
            isIngredient5Selected = true;
            numOfSelected++;
            ingredient5.setEffect(glowEffectSelected);
        }else if(isIngredient5Selected){
            isIngredient5Selected = false;
            numOfSelected--;
            hmEnteredIngredient5();
        }
    }


    public void hmEnteredBrewButton(){
        brewButton.setFill(Color.web("#f5dc6a"));
    }
    public void hmExitedBrewButton(){
        brewButton.setFill(Color.web("#ffd700"));
    }
    public void hmClickedBrewButton(){
        if(numOfSelected != 2) return;
        actionPerformed();
        Stage stage = (Stage)ingredient_image1.getScene().getWindow();
        stage.close();
    }

    public void selectIngredients(){
        selectedIngredients.clear();
        if(isIngredient1Selected) {
            selectedIngredients.add(ingredientMap.get(ingredient_image1));
            isIngredient1Selected = false;
        }
        if(isIngredient2Selected) {
            selectedIngredients.add(ingredientMap.get(ingredient_image2));
            isIngredient2Selected = false;
        }
        if(isIngredient3Selected) {
            selectedIngredients.add(ingredientMap.get(ingredient_image3));
            isIngredient3Selected = false;
        }
        if(isIngredient4Selected) {
            selectedIngredients.add(ingredientMap.get(ingredient_image4));
            isIngredient4Selected = false;
        }
        if(isIngredient5Selected) {
            selectedIngredients.add(ingredientMap.get(ingredient_image5));
            isIngredient5Selected = false;
        }
    }
}
