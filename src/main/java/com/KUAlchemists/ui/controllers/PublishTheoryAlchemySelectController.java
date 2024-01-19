package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.PublishTheoryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
public class PublishTheoryAlchemySelectController {
    private String blue;
    private String red;
    private String green;
    private String seal;

    private boolean lock2;
    private boolean lock;
    @FXML
    private ImageView SealBQ;

    @FXML
    private ImageView SealGQ;

    @FXML
    private ImageView SealGS;
    @FXML
    private Text txt;

    @FXML
    private ImageView SealRQ;

    @FXML
    private ImageView SealSS;

    @FXML
    private ImageView alchemy_1;

    @FXML
    private ImageView alchemy_2;

    @FXML
    private ImageView alchemy_3;

    @FXML
    private ImageView alchemy_4;

    @FXML
    private ImageView alchemy_5;

    @FXML
    private ImageView alchemy_6;

    @FXML
    private ImageView alchemy_7;

    @FXML
    private ImageView alchemy_8;
    @FXML
    void PublishClicked(ActionEvent event) {
        PublishTheoryHandler pbt = PublishTheoryHandler.getInstance();

        String result = pbt.handlePublishTheoryRequest();

        if ("Failed to publish theory.".equals(result)) {
            txt.setText("You couldnt published the thoery");
        }
        else if ("You don't have enough action points to publish a theory.".equals(result)) {
            txt.setText("You don't have enough action points to publish a theory.");
        }
        else {
            txt.setText("You succesfully published the theory");
        }
    }


    @FXML
    void initialize(){
        lock = true;
        lock2 = true;
    }
    @FXML
    void sealClicked(MouseEvent event) {
        if(lock) {
            String seal = event.toString().substring(34, event.toString().indexOf(","));

            Glow selectGlow = new Glow(1.7f);
            ImageView clickedImage = (ImageView) event.getSource();
            clickedImage.setEffect(selectGlow);
            lock=false;
            List<String> seals= new ArrayList<>();
            seals.add(seal.substring(seal.length() - 2));
            this.seal=seal.substring(seal.length() - 2);

            if(!lock2){
               String ing= PublishTheoryHandler.getInstance().getSelectedIngredientName();

                PublishTheoryHandler pbt = PublishTheoryHandler.getInstance();
                pbt.setSelectedIngredientName(PublishTheoryHandler.getInstance().getSelectedIngredientName());
               pbt.setSelectedTheorySeals(seals);
               pbt.setPredictedRedAspectString(red);pbt.setPredictedGreenAspectString(green);pbt.setPredictedBlueAspectString(blue);



            }
        }
        else {

        }

    }


    @FXML void unglow(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        clickedImage.setEffect(null);
    }

    @FXML
    void glow(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        Glow selectGlow = new Glow(1.4f);
        clickedImage.setEffect(selectGlow);

        // Your implementation for glow effect on hover (if needed)
    }

    @FXML
    void alchemyClicked(MouseEvent event) {
        ArrayList<ImageView> alchemies_photo = new ArrayList<ImageView>();

       if(lock2){

           ImageView clickedImage = (ImageView) event.getSource();
           Glow selectGlow = new Glow(1.4f);
           clickedImage.setEffect(selectGlow);

           lock2=false;


        //order is green red blue
        ArrayList<String> alchemy_1 = new ArrayList<String>();
        alchemy_1.add("POSITIVE_BIG");
        alchemy_1.add("POSITIVE_BIG");
        alchemy_1.add("POSITIVE_BIG");

        ArrayList<String> alchemy_2 = new ArrayList<String>();
        alchemy_2.add("POSITIVE_SMALL");
        alchemy_2.add("NEGATIVE_SMALL");
        alchemy_2.add("NEGATIVE_BIG");

        ArrayList<String> alchemy_3 = new ArrayList<String>();
        alchemy_3.add("POSITIVE_SMALL");
        alchemy_3.add("POSITIVE_BIG");
        alchemy_3.add("NEGATIVE_SMALL");

        ArrayList<String> alchemy_4 = new ArrayList<String>();
        alchemy_4.add("POSITIVE_BIG");
        alchemy_4.add("NEGATIVE_SMALL");
        alchemy_4.add("POSITIVE_SMALL");

        ArrayList<String> alchemy_5 = new ArrayList<String>();
        alchemy_5.add("NEGATIVE_SMALL");
        alchemy_5.add("POSITIVE_SMALL");
        alchemy_5.add("POSITIVE_BIG");

        ArrayList<String> alchemy_6 = new ArrayList<String>();
        alchemy_6.add("NEGATIVE_BIG");
        alchemy_6.add("NEGATIVE_BIG");
        alchemy_6.add("NEGATIVE_BIG");

        ArrayList<String> alchemy_7 = new ArrayList<String>();
        alchemy_7.add("NEGATIVE_SMALL");
        alchemy_7.add("NEGATIVE_BIG");
        alchemy_7.add("POSITIVE_SMALL");

        ArrayList<String> alchemy_8 = new ArrayList<String>();
        alchemy_8.add("NEGATIVE_BIG");
        alchemy_8.add("POSITIVE_SMALL");
        alchemy_8.add("NEGATIVE_SMALL");


        Dictionary<String, ArrayList> dict = new Hashtable<>();
        dict.put("alchemy_1", alchemy_1);
        dict.put("alchemy_2", alchemy_2);
        dict.put("alchemy_3", alchemy_3);
        dict.put("alchemy_4", alchemy_4);
        dict.put("alchemy_5", alchemy_5);
        dict.put("alchemy_6", alchemy_6);
        dict.put("alchemy_7", alchemy_7);
        dict.put("alchemy_8", alchemy_8);


        String id = event.toString().substring(34, event.toString().indexOf(","));
        PublishTheoryHandler.getInstance().setPredictedRedAspectString( dict.get(id).get(1).toString());
        this.red=dict.get(id).get(0).toString();

        this.green=dict.get(id).get(1).toString();
        this.blue=dict.get(id).get(2).toString();
           PublishTheoryHandler.getInstance().setPredictedGreenAspectString( dict.get(id).get(0).toString());
           PublishTheoryHandler.getInstance().setPredictedBlueAspectString( dict.get(id).get(2).toString());
           if(!lock){
               String ing= PublishTheoryHandler.getInstance().getSelectedIngredientName();
               List<String> seals= new ArrayList<>();


               seals.add(this.seal.substring(seal.length() - 2));
               PublishTheoryHandler pbt = PublishTheoryHandler.getInstance();
               pbt.setSelectedIngredientName(PublishTheoryHandler.getInstance().getSelectedIngredientName());
               pbt.setSelectedTheorySeals(seals);
               pbt.setPredictedRedAspectString(red);pbt.setPredictedGreenAspectString(green);pbt.setPredictedBlueAspectString(blue);


           }
       }
       else{


    }
    }

}
