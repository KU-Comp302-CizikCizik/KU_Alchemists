package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.EndorseHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Stack;

public class EndorseController {

    @FXML
    private ImageView alchemy;


    @FXML
    private Text EndorseText;
    @FXML
    private ImageView seal1;

    @FXML
    private ImageView seal2;

    @FXML
    private ImageView seal3;

    @FXML
    private ImageView SealGS;

    @FXML
    private ImageView SealSS;

    @FXML
    private ImageView SealRQ;

    @FXML
    private ImageView SealBQ;

    @FXML
    private ImageView SealGQ;

    @FXML
    private ImageView theoryImage;

    private ImageView selectedSeal;

    ArrayList<String> playerAvailableSeals;

    ArrayList<String> playerSeals;

    Stack<ImageView> sealSlots = new Stack<>();
    private ImageView selectedSeal1;

    private boolean isPlayerAuthor;

    @FXML
    private void initialize() {
        playerAvailableSeals = EndorseHandler.getInstance().getPlayerAvailableTheorySeals();
        isPlayerAuthor = EndorseHandler.getInstance().isCurrentPlayerAuthor();
        playerSeals = EndorseHandler.getInstance().getEndorsedPlayerSeals();
        String theory = EndorseHandler.getInstance().getTheoryString();
        String alchemicalName = EndorseHandler.getInstance().getAlchemicalName();

        setTheoryImage(theory);
        sealSlots.add(seal3);
        sealSlots.add(seal2);
        sealSlots.add(seal1);

        String playerSeal = EndorseHandler.getInstance().getPlayerSeal();
        setSeals(playerSeal);
        disactiveNotOwnedSeals();
        setEndorsersSeals();
        setAlchemy(alchemicalName);
        if(isPlayerAuthor){
            EndorseText.setDisable(true);
            EndorseText.setEffect(new GaussianBlur(4));
        }

    }

    private void setAlchemy(String alchemicalName) {
        String imagePath = "/com.KUAlchemists/images/alchemy/" + alchemicalName;
        try {
            Image newImage = new Image(getClass().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            alchemy.setImage(newImage);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    private void setTheoryImage(String theory) {
        String imagePath = "/com.KUAlchemists/images/" + theory + "-ingredient.jpg";
        try {
            Image newImage = new Image(getClass().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            theoryImage.setImage(newImage);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void setEndorsersSeals() {

        if(playerSeals.contains("red")) {
            sealSlots.pop().setImage(getImage("redSecretSeal"));
        }

        if(playerSeals.contains("blue")) {
            sealSlots.pop().setImage(getImage("blueSecretSeal"));
        }

        if(playerSeals.contains("green")) {
            sealSlots.pop().setImage(getImage("greenSecretSeal"));
        }

        if(playerSeals.contains("yellow")){
            sealSlots.pop().setImage(getImage("yellowSecretSeal"));
        }
    }

    private void disactiveNotOwnedSeals() {
        if(!playerAvailableSeals.contains("GS")) {
            SealGS.setDisable(true);
            SealGS.setEffect(new GaussianBlur(4));
        }

        if(!playerAvailableSeals.contains("SS")) {
            SealSS.setDisable(true);
            SealSS.setEffect(new GaussianBlur(4));
        }

        if(!playerAvailableSeals.contains("RQ")) {
            SealRQ.setDisable(true);
            SealRQ.setEffect(new GaussianBlur(4));
        }

        if(!playerAvailableSeals.contains("BQ")) {
            SealBQ.setDisable(true);
            SealBQ.setEffect(new GaussianBlur(4));
        }

        if(!playerAvailableSeals.contains("GQ")) {
            SealGQ.setDisable(true);
            SealGQ.setEffect(new GaussianBlur(4));
        }

    }

    private void setSeals(String colour) {
        SealGS.setImage(getImage(colour+ "SealGS" ));
        SealSS.setImage(getImage(colour + "SealSS"));
        SealRQ.setImage(getImage(colour + "SealRQ"));
        SealBQ.setImage(getImage(colour + "SealBQ"));
        SealGQ.setImage(getImage(colour + "SealGQ"));
    }

    private Image getImage(String s) {
        String imagePath = "/com.KUAlchemists/images/Endorse/" + s + ".png";
        try {
            Image newImage = new Image(getClass().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            return newImage;
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    private void select(ImageView seal) {
        seal.setEffect(new Glow(1));
        if (selectedSeal != null && selectedSeal != seal) {
            selectedSeal.setEffect(null);
        }
        selectedSeal = seal;
        seal.setEffect(new Glow(0.5));
    }

    @FXML
    void endorseClicked(MouseEvent event) {
        if(playerSeals.size()<3){
            sealSlots.pop().setImage(selectedSeal.getImage());
            try {
                EndorseHandler.getInstance().saveEndorsedSeal(getClass().getDeclaredField(selectedSeal.getId()).getName().split("Seal")[1]);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }else{
            SceneLoader.getInstance().loadGenericPopUp("This theory have already been endorsed 3 seals");
        }

    }

    @FXML
    void seal1Clicked(MouseEvent event) {
            select(SealGS);
    }

    @FXML
    void seal2Clicked(MouseEvent event) {
        select(SealSS);
    }

    @FXML
    void seal3Clicked(MouseEvent event) {
        select(SealRQ);
    }

    @FXML
    void seal4Clicked(MouseEvent event) {
        select(SealBQ);
    }

    @FXML
    void seal5Clicked(MouseEvent event) {
        select(SealGQ);
    }

    @FXML
    void onMouseEntered(MouseEvent event){
        EndorseText.setEffect(new Glow(0.7));
    }

    @FXML
    void onMouseExited(MouseEvent event){
        EndorseText.setEffect(null);
    }

}
