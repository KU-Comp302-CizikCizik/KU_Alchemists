package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.DeductionBoardHandler;
import com.KUAlchemists.backend.models.DeductionBoard;
import javafx.fxml.FXML;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeductionBoardController {


    @FXML
    private ImageView alchemy_1_1;

    @FXML
    private ImageView alchemy_1_2;

    @FXML
    private ImageView alchemy_1_3;

    @FXML
    private ImageView alchemy_1_4;

    @FXML
    private ImageView alchemy_1_5;

    @FXML
    private ImageView alchemy_1_6;

    @FXML
    private ImageView alchemy_1_7;

    @FXML
    private ImageView alchemy_1_8;

    @FXML
    private ImageView alchemy_2_1;

    @FXML
    private ImageView alchemy_2_2;

    @FXML
    private ImageView alchemy_2_3;

    @FXML
    private ImageView alchemy_2_4;

    @FXML
    private ImageView alchemy_2_5;

    @FXML
    private ImageView alchemy_2_6;

    @FXML
    private ImageView alchemy_2_7;

    @FXML
    private ImageView alchemy_2_8;

    @FXML
    private ImageView alchemy_3_1;

    @FXML
    private ImageView alchemy_3_2;

    @FXML
    private ImageView alchemy_3_3;

    @FXML
    private ImageView alchemy_3_4;

    @FXML
    private ImageView alchemy_3_5;

    @FXML
    private ImageView alchemy_3_6;

    @FXML
    private ImageView alchemy_3_7;

    @FXML
    private ImageView alchemy_3_8;

    @FXML
    private ImageView alchemy_4_1;

    @FXML
    private ImageView alchemy_4_2;

    @FXML
    private ImageView alchemy_4_3;

    @FXML
    private ImageView alchemy_4_4;

    @FXML
    private ImageView alchemy_4_5;

    @FXML
    private ImageView alchemy_4_6;

    @FXML
    private ImageView alchemy_4_7;

    @FXML
    private ImageView alchemy_4_8;

    @FXML
    private ImageView alchemy_5_1;

    @FXML
    private ImageView alchemy_5_2;

    @FXML
    private ImageView alchemy_5_3;

    @FXML
    private ImageView alchemy_5_4;

    @FXML
    private ImageView alchemy_5_5;

    @FXML
    private ImageView alchemy_5_6;

    @FXML
    private ImageView alchemy_5_7;

    @FXML
    private ImageView alchemy_5_8;

    @FXML
    private ImageView alchemy_6_1;

    @FXML
    private ImageView alchemy_6_2;

    @FXML
    private ImageView alchemy_6_3;

    @FXML
    private ImageView alchemy_6_4;

    @FXML
    private ImageView alchemy_6_5;

    @FXML
    private ImageView alchemy_6_6;

    @FXML
    private ImageView alchemy_6_7;

    @FXML
    private ImageView alchemy_6_8;

    @FXML
    private ImageView alchemy_7_1;

    @FXML
    private ImageView alchemy_7_2;

    @FXML
    private ImageView alchemy_7_3;

    @FXML
    private ImageView alchemy_7_4;

    @FXML
    private ImageView alchemy_7_5;

    @FXML
    private ImageView alchemy_7_6;

    @FXML
    private ImageView alchemy_7_7;

    @FXML
    private ImageView alchemy_7_8;

    @FXML
    private ImageView alchemy_8_1;

    @FXML
    private ImageView alchemy_8_2;

    @FXML
    private ImageView alchemy_8_3;

    @FXML
    private ImageView alchemy_8_4;

    @FXML
    private ImageView alchemy_8_5;

    @FXML
    private ImageView alchemy_8_6;

    @FXML
    private ImageView alchemy_8_7;


    @FXML
    private ImageView alchemy_8_8;


    @FXML
    private ImageView birdfeet_feather;

    @FXML
    private ImageView birdfeet_flower;

    @FXML
    private ImageView birdfeet_root;

    @FXML
    private ImageView birdfeet_scorpion;

    @FXML
    private ImageView flower_feather;

    @FXML
    private ImageView flower_root;

    @FXML
    private ImageView flower_scorpion;

    @FXML
    private ImageView frog_birdfeet;

    @FXML
    private ImageView frog_feather;

    @FXML
    private ImageView frog_flower;

    @FXML
    private ImageView frog_root;

    @FXML
    private ImageView frog_scorpion;

    @FXML
    private ImageView mushroom_birdfeet;

    @FXML
    private ImageView mushroom_feather;

    @FXML
    private ImageView mushroom_flower;

    @FXML
    private ImageView mushroom_frog;

    @FXML
    private ImageView mushroom_plant;

    @FXML
    private ImageView mushroom_root;

    @FXML
    private ImageView mushroom_scorpion;

    @FXML
    private ImageView plant_birdfeet;

    @FXML
    private ImageView plant_feather;

    @FXML
    private ImageView plant_flower;

    @FXML
    private ImageView plant_frog;

    @FXML
    private ImageView plant_root;

    @FXML
    private ImageView plant_scorpion;

    @FXML
    private ImageView root_feather;

    @FXML
    private ImageView root_scorpion;

    @FXML
    private ImageView scorpion_feather;

    private ArrayList<ImageView> alchemies;

    public DeductionBoardController(){

    }

    @FXML
    void alchemyClicked(MouseEvent event) {

        String alchemy_id = new String(event.toString());
        String alchemy_column=alchemy_id.substring(alchemy_id.indexOf("alchemy")+8,alchemy_id.indexOf("alchemy")+9);
        String alchemy_row=alchemy_id.substring(alchemy_id.indexOf("alchemy")+10,alchemy_id.indexOf("alchemy")+11);
        String alchemy_made="alchemy_"+alchemy_column+"_"+alchemy_row;
        for (int i = 0; i < 64; i++) {

            if(alchemies.get(i).getId().equals(alchemy_made)){
                if(alchemies.get(i).getEffect() == null){
                    Glow selectGlow = new Glow(1.7f);
                    alchemies.get(i).setEffect(selectGlow);
                    DeductionBoardHandler.getInstance().markAlchemical(alchemy_made);
                }
                else{
                    DeductionBoardHandler.getInstance().unmarkAlhemical(alchemy_made);
                    alchemies.get(i).setEffect(null);
                }
            }
        }

    }

    private void addEffect(ImageView imageView) {
        Glow selectGlow = new Glow(1.7f);
        imageView.setEffect(selectGlow);
    }

    @FXML
    private void initialize() {
        alchemies = new ArrayList<ImageView>();
        alchemies.add(alchemy_1_1);alchemies.add(alchemy_1_2);alchemies.add(alchemy_1_3);alchemies.add(alchemy_1_4);alchemies.add(alchemy_1_5);alchemies.add(alchemy_1_6);alchemies.add(alchemy_1_7);alchemies.add(alchemy_1_8);
        alchemies.add(alchemy_2_1);alchemies.add(alchemy_2_2);alchemies.add(alchemy_2_3);alchemies.add(alchemy_2_4);alchemies.add(alchemy_2_5);alchemies.add(alchemy_2_6);alchemies.add(alchemy_2_7);alchemies.add(alchemy_2_8);
        alchemies.add(alchemy_3_1);alchemies.add(alchemy_3_2);alchemies.add(alchemy_3_3);alchemies.add(alchemy_3_4);alchemies.add(alchemy_3_5);alchemies.add(alchemy_3_6);alchemies.add(alchemy_3_7);alchemies.add(alchemy_3_8);
        alchemies.add(alchemy_4_1);alchemies.add(alchemy_4_2);alchemies.add(alchemy_4_3);alchemies.add(alchemy_4_4);alchemies.add(alchemy_4_5);alchemies.add(alchemy_4_6);alchemies.add(alchemy_4_7);alchemies.add(alchemy_4_8);
        alchemies.add(alchemy_5_1);alchemies.add(alchemy_5_2);alchemies.add(alchemy_5_3);alchemies.add(alchemy_5_4);alchemies.add(alchemy_5_5);alchemies.add(alchemy_5_6);alchemies.add(alchemy_5_7);alchemies.add(alchemy_5_8);
        alchemies.add(alchemy_6_1);alchemies.add(alchemy_6_2);alchemies.add(alchemy_6_3);alchemies.add(alchemy_6_4);alchemies.add(alchemy_6_5);alchemies.add(alchemy_6_6);alchemies.add(alchemy_6_7);alchemies.add(alchemy_6_8);
        alchemies.add(alchemy_7_1);alchemies.add(alchemy_7_2);alchemies.add(alchemy_7_3);alchemies.add(alchemy_7_4);alchemies.add(alchemy_7_5);alchemies.add(alchemy_7_6);alchemies.add(alchemy_7_7);alchemies.add(alchemy_7_8);
        alchemies.add(alchemy_8_1);alchemies.add(alchemy_8_2);alchemies.add(alchemy_8_3);alchemies.add(alchemy_8_4);alchemies.add(alchemy_8_5);alchemies.add(alchemy_8_6);alchemies.add(alchemy_8_7);alchemies.add(alchemy_8_8);

        ArrayList<String> markedAlchemicals = DeductionBoardHandler.getInstance().getMarkedAlchemicals();
        HashMap<String,ArrayList <String>> markedIngredients = DeductionBoardHandler.getInstance().getMarkedIngredients();

        for(String key: markedIngredients.keySet()){
            for(String ingredient: markedIngredients.get(key)){
                updateImageByName(key, ingredient);
            }
        }

        for (String alchemy : markedAlchemicals) {
            updateAlchemyByName(alchemy);
        }

    }

    private void updateAlchemyByName(String alchemy) {
        try {
            // Use reflection to get the ImageView field by name
            Field field = getClass().getDeclaredField(alchemy);
            field.setAccessible(true);
            ImageView targetImageView = (ImageView) field.get(this);

            addEffect(targetImageView);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle exceptions, e.g., ImageView not found
            e.printStackTrace();
        }
    }

    public void updateImageByName(String imageName, String targetImageViewName) {
        try {
            // Use reflection to get the ImageView field by name
            Field field;
            try{
                field = getClass().getDeclaredField(targetImageViewName);
            }
            catch (Exception e){
                String[] parts = targetImageViewName.split("_");
                targetImageViewName = parts[1] + "_" + parts[0];
                field = getClass().getDeclaredField(targetImageViewName);

            }

            field.setAccessible(true);
            ImageView targetImageView = (ImageView) field.get(this);

            // Assuming your image files have a common extension like ".png" or ".jpg"
            String imagePath = "/com.KUAlchemists/images/alchemy/" + imageName + ".png";
            // Load the image using the class loader to ensure it works regardless of the build type

            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                // Set the image to the ImageView
                targetImageView.setImage(image);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle exceptions, e.g., ImageView not found
            e.printStackTrace();
        }
    }
    @FXML
    void setCircle(MouseEvent event) {

    }
}
