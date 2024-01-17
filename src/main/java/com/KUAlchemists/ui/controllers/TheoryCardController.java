package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.PublicationTrackHandler;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.ui.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TheoryCardController {


    @FXML
    private ImageView ingredient_image;

    @FXML
    private Text player_txt;


    @FXML
    private ImageView alchemy;


    private String ingredientName;

    @FXML
    private Button debunkButton;

    @FXML
    private Button endorseButton;

    @FXML
    void initialize() {
       if(GameEngine.getInstance().getGameRound() != 3){
          disableButton(debunkButton);
          disableButton(endorseButton);
       }
    }

    private void disableButton(Button button) {
        button.setDisable(true);
        button.setEffect(new GaussianBlur(4));
    }

    public void setPlayername(String txt) {
        player_txt.setText(txt);

    }
    public void setIngredient(String ingredientName) {
        String imagePath = "/com.KUAlchemists/images/" + ingredientName + "-ingredient-square.png";
        // Load the image using the class loader to ensure it works regardless of the build type
        this.ingredientName = ingredientName;

            Image image = new Image(getClass().getResourceAsStream(imagePath));
        ingredient_image.setImage(image);

    }
    public void setAlchemy(String green,String red, String blue){
        String pb="POSITIVE_BIG";
        String ps="POSITIVE_SMALL";
        String nb="NEGATIVE_BIG";
        String ns="NEGATIVE_SMALL";

        if(green.equals(pb)&&blue.equals(pb)&&red.equals(pb)){
            String imagePath = "/com.KUAlchemists/images/alchemy/alchemy1.png";
            // Load the image using the class loader to ensure it works regardless of the build type



            Image image = new Image(getClass().getResourceAsStream(imagePath));
            alchemy.setImage(image);

        } else if(green.equals(ps)&&blue.equals(nb)&&red.equals(ns)){
            String imagePath = "/com.KUAlchemists/images/alchemy/alchemy2.png";
            // Load the image using the class loader to ensure it works regardless of the build type



            Image image = new Image(getClass().getResourceAsStream(imagePath));
            alchemy.setImage(image);

        }
        else if(green.equals(ps)&&blue.equals(ns)&&red.equals(pb)){
            String imagePath = "/com.KUAlchemists/images/alchemy/alchemy3.png";
            // Load the image using the class loader to ensure it works regardless of the build type



            Image image = new Image(getClass().getResourceAsStream(imagePath));
            alchemy.setImage(image);

        }
        else if(green.equals(pb)&&blue.equals(ps)&&red.equals(ns)){
            String imagePath = "/com.KUAlchemists/images/alchemy/alchemy4.png";
            // Load the image using the class loader to ensure it works regardless of the build type



            Image image = new Image(getClass().getResourceAsStream(imagePath));
            alchemy.setImage(image);

        }
        else if(green.equals(ns)&&blue.equals(pb)&&red.equals(ps)){
            String imagePath = "/com.KUAlchemists/images/alchemy/alchemy5.png";
            // Load the image using the class loader to ensure it works regardless of the build type



            Image image = new Image(getClass().getResourceAsStream(imagePath));
            alchemy.setImage(image);

        }
        else if(green.equals(nb)&&blue.equals(nb)&&red.equals(nb)){
            String imagePath = "/com.KUAlchemists/images/alchemy/alchemy6.png";
            // Load the image using the class loader to ensure it works regardless of the build type



            Image image = new Image(getClass().getResourceAsStream(imagePath));
            alchemy.setImage(image);

        }
        else if(green.equals(ns)&&blue.equals(ps)&&red.equals(nb)){
            String imagePath = "/com.KUAlchemists/images/alchemy/alchemy7.png";
            // Load the image using the class loader to ensure it works regardless of the build type



            Image image = new Image(getClass().getResourceAsStream(imagePath));
            alchemy.setImage(image);

        }
        else if(green.equals(nb)&&blue.equals(ns)&&red.equals(ps)){
            String imagePath = "/com.KUAlchemists/images/alchemy/alchemy8.png";
            // Load the image using the class loader to ensure it works regardless of the build type



            Image image = new Image(getClass().getResourceAsStream(imagePath));
            alchemy.setImage(image);

        }

    }

    @FXML
    public void endorseClicked(){
        PublicationTrackHandler.getInstance().selectTheoryToEndorse(ingredientName);
        Stage stage = (Stage)ingredient_image.getScene().getWindow();
        stage.close();
        SceneLoader.getInstance().loadEndorse();
    }

    @FXML
    public void debunkClicked(){
        PublicationTrackHandler.getInstance().selectTheoryToDebunk(ingredientName);
        Stage stage = (Stage)ingredient_image.getScene().getWindow();
        stage.close();
        SceneLoader.getInstance().loadDebunk();
    }

}
