package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class AvatarSelectController {
    private boolean p=false;
    @FXML
    private ImageView image_1;

    @FXML
    private ImageView image_2;

    @FXML
    private ImageView image_3;

    @FXML
    private ImageView image_4;

    @FXML
    private ImageView image_5;

    @FXML
    private ImageView image_6;

    @FXML
    private ImageView image_7;

    @FXML
    private ImageView image_8;

    @FXML
    private Text txt;
    private ArrayList<ImageView> pictures;
    @FXML
    void clicked(MouseEvent event) {
        pictures = new ArrayList<ImageView>();
        pictures.add(image_1);pictures.add(image_2);pictures.add(image_3);pictures.add(image_4);pictures.add(image_5);pictures.add(image_6);pictures.add(image_7);pictures.add(image_8);
        String ima=event.toString().substring( event.toString().indexOf("image"), event.toString().indexOf("id=")+10);
        System.out.print(ima+"\n");
        for (int i = 0; i < 8; i++) {

            if(pictures.get(i).getId().equals(ima)){
                Glow selectGlow = new Glow(1.7f);
                pictures.get(i).setEffect(selectGlow);
                //backende gidicekn bilgi
                //Aga burda resmi alıp backendde tutması lazım. Sonra ana oyun board'ında bu resimler, sergileyecek
                System.out.print("geldi");
                System.out.print(pictures.get(i).getImage());
                if(p){
                    txt.setText("Player 2 Your turn:");

                }
                else{
                    //ana oyuna geçer
                }

    }
        }
    }

    @FXML
    void glow(MouseEvent event) {



    }

}
