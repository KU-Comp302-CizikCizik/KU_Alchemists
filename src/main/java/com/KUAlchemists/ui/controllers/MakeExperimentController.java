package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.MakeExperimentHandler;
import com.KUAlchemists.backend.handlers.PotionBrewingAreaHandler;
import com.KUAlchemists.backend.handlers.SoundEffectHandler;
import com.KUAlchemists.backend.sound.SoundContrasts;
import com.KUAlchemists.ui.SceneLoader;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MakeExperimentController {
    public Pane masterPane;
    public Pane studentPane;

    public Pane redPotionPane, greenPotionPane, grayPotionPane, messagePane;
    public Text masterText;
    public Text studentText;

    private final Effect glowEffect = new Glow(0.4);
    private final Effect glowEffectSelected = new Glow(0.4);

    private final String STUDENT = "student";
    private final String MASTER = "master";

    public Text message;
    private String finalMessage;

    private String potionName;
    private String potionType;
    private ArrayList<String> positivePotions = new ArrayList<>();
    private ArrayList<String> negativePotions = new ArrayList<>();
    private String ingredient1Name;
    private String ingredient2Name;


    private void experiencePotion(String person){
        SoundEffectHandler.getInstance().handleSoundEffect(SoundContrasts.POP_SOUND);
        ingredient1Name = MakeExperimentHandler.getInstance().getIngredientsToBeBrewed().get(0);
        ingredient2Name = MakeExperimentHandler.getInstance().getIngredientsToBeBrewed().get(1);

        System.out.println(ingredient1Name + ingredient2Name);

        potionName  = PotionBrewingAreaHandler.getInstance().brewPotion(ingredient1Name, ingredient2Name);
        if(potionName == null) SceneLoader.getInstance().loadGenericPopUp("No action points left!");

        String subPronounce = (person.equals(MASTER))? " You" : " Your student";
        String pronounce = (person.equals(MASTER))? " Are":" Is";

        if(positivePotions.contains(potionName)){
            finalMessage = "Wow! As"+subPronounce.toLowerCase()+pronounce.toLowerCase()+" feeling much better, it turns out to be a POSITIVE potion! Which means you can use it to heal yourself, congratulations.";
            potionType = "positive";
        } else if (negativePotions.contains(potionName)) {
            finalMessage = "Omg!"+pronounce+subPronounce.toLowerCase()+" okay?"+subPronounce+pronounce.toLowerCase() +" looking like got poisoned. That means the potion is NEGATIVE and venomous.";
            if(person.equals(STUDENT)){
                finalMessage+=" Well, you have to pay your student to heal themselves right?";
            }else{
                finalMessage+=" Be careful next time, try not to be poisoned.";
            }
            potionType = "negative";
        } else{
            finalMessage = "Well, I would like to say"+subPronounce.toLowerCase()+pronounce.toLowerCase()+" turning green, but nah! There is no change, the potion is NEUTRAL.";
            potionType = "neutral";
        }
        finalizeUIElements();
        MakeExperimentHandler.getInstance().makeExperience(person, potionName, potionType);
    }

    private void finalizeUIElements(){
        message.setText(finalMessage);
        message.setVisible(true);
        messagePane.setVisible(true);

        masterPane.setEffect(null);
        masterPane.setDisable(true);

        studentPane.setEffect(null);
        studentPane.setDisable(true);

        if(positivePotions.contains(potionName)){
            greenPotionPane.setVisible(true);
        } else if (negativePotions.contains(potionName)) {
            redPotionPane.setVisible(true);
        } else{
            grayPotionPane.setVisible(true);
        }

    }

    public void initialize(){
        System.out.println(potionName);//for debug

        positivePotions.add("HEALING_POTION");
        positivePotions.add("SPEED_POTION");
        positivePotions.add("WISDOM_POTION");

        negativePotions.add("INSANITY_POTION");
        negativePotions.add("PARALYSIS_POTION");
        negativePotions.add("POISON_POTION");
    }



    public void masterMouseClicked(MouseEvent mouseEvent) {
        experiencePotion(MASTER);
    }

    public void masterMouseEntered(MouseEvent mouseEvent) {
        masterPane.setEffect(glowEffectSelected);
        masterText.setVisible(true);
    }

    public void masterMouseExited(MouseEvent mouseEvent) {
        masterPane.setEffect(null);
        masterText.setVisible(false);
    }



    public void studentMouseClicked(MouseEvent mouseEvent) {
        experiencePotion(STUDENT);
    }

    public void studentMouseEntered(MouseEvent mouseEvent) {
        studentPane.setEffect(glowEffectSelected);
        studentText.setVisible(true);
    }
    public void studentMouseExited(MouseEvent mouseEvent) {
        studentPane.setEffect(null);
        studentText.setVisible(false);
    }
}
