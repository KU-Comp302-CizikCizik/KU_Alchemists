package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.SellPotionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PriceOfferSellPotionUIController {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button cancelButton;

    @FXML
    private Text recommendedPrice;

    @FXML
    private Text potionName;

    @FXML
    private Text potionType;

    @FXML
    private ImageView potionImage;

    private String recomPrice;
    private String[] potion;


    @FXML
    private void initialize(){



        potion = SellPotionHandler.getInstance().getPotionToBeSelled();

        recomPrice = (potion[1].equals("Type: neutral"))?"2 Golds":"1 Gold";
        recomPrice = (potion[1].equals("Type: positive"))? "3 Golds" : recomPrice;
        System.out.println(potion[1]);

        String imagePath = "com.KUAlchemists/images/potions/" + potion[0] + ".png";
        potionName.setText(capitalizeWords(potion[0]));
        potionType.setText(potion[1]);
        recommendedPrice.setText("Recommended: "+recomPrice);


        // Load the image using the class loader to ensure it works regardless of the build type
        try {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));
            potionImage.setImage(image);
        }catch (Exception e){
            System.err.println(e.getMessage());
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
            final Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
        closePopup();
    }

    private void sellThePotion(int price){
        SellPotionHandler.getInstance().handleSellPotion(potion[0], price);
        if(potion[1].equals("Type: negative") && price > 1){
            SellPotionHandler.getInstance().setStatusBad();
        }else if(potion[1].equals("Type: neutral") && price > 2){
            SellPotionHandler.getInstance().setStatusBad();
        }else{
            SellPotionHandler.getInstance().setStatusGood();
        }
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


}
