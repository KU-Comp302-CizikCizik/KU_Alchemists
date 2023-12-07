package com.KUAlchemists.ui.controllers;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PotionBrewController {
    public Pane ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, slot1, slot2, potion;
    boolean isIngredient1Selected = false, isIngredient2Selected = false, isIngredient3Selected = false, isIngredient4Selected = false, isIngredient5Selected = false;
    public Text brewButton;

    private ArrayList<Pane> uiIngredientList = new ArrayList<Pane>();
    private ArrayList<String> selectedIngredients = new ArrayList<String>();

    private int numOfSelected = 0;

    private final Effect glowEffect = new Glow(0.4);
    private final Effect glowEffectSelected = new Glow(0.6);
    private final Effect dropShadowEffect = new DropShadow();

    public void actionPerformed(){
        selectIngredients();//Prepares selected ingredients
        System.out.println(selectedIngredients);
        //----Your Code Here Kardes-----

        //----Do not touch below this line kardes-----
    }

    public void prepareUI(ArrayList<String> list){
        ingredient1.setVisible(false);
        ingredient2.setVisible(false);
        ingredient3.setVisible(false);
        ingredient4.setVisible(false);
        ingredient5.setVisible(false);

        uiIngredientList.add(ingredient1);
        uiIngredientList.add(ingredient2);
        uiIngredientList.add(ingredient3);
        uiIngredientList.add(ingredient4);
        uiIngredientList.add(ingredient5);
        if(!list.isEmpty()){
            for(int i  = 0; i < list.size(); i++){
                uiIngredientList.get(i).setVisible(true);
            }
        }
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
        actionPerformed();
    }

    public void selectIngredients(){
        selectedIngredients.clear();
        if(isIngredient1Selected)
            selectedIngredients.add("ingredient1");
        if(isIngredient2Selected)
            selectedIngredients.add("ingredient2");
        if(isIngredient3Selected)
            selectedIngredients.add("ingredient3");
        if(isIngredient4Selected)
            selectedIngredients.add("ingredient4");
        if(isIngredient5Selected)
            selectedIngredients.add("ingredient5");
    }




}
