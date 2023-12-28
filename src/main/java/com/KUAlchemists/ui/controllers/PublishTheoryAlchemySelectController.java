package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
public class PublishTheoryAlchemySelectController {

    @FXML
    private ImageView blue_big_n;

    @FXML
    private ImageView blue_big_p;

    @FXML
    private ImageView blue_small_n;

    @FXML
    private ImageView blue_small_p;

    @FXML
    private ImageView green_big_n;

    @FXML
    private ImageView green_big_p;

    @FXML
    private ImageView green_small_n;

    @FXML
    private ImageView green_small_p;

    @FXML
    private ImageView red_big_n;

    @FXML
    private ImageView red_big_p;

    @FXML
    private ImageView red_small_n;

    @FXML
    private ImageView red_small_p;

    @FXML
    void alchemyClicked(MouseEvent event) {
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
        System.out.println(dict.get(id).get(1));
        String red = dict.get(id).get(1).toString();
        String green = dict.get(id).get(0).toString();
        String blue = dict.get(id).get(2).toString();

            //Arda endorse eklendiği için bu kısıma bir el atılması gerekecek. Konuşuruz.
//        // Get the existing instance of PublishTheoryHandler
//        PublishTheoryHandler pbt = PublishTheoryHandler.getInstance();
//
//        // Call the handlePublishTheoryRequest method
//        String result = pbt.handlePublishTheoryRequest(red, green, blue);
//
//        // Handle the result accordingly
//        if ("Failed to publish theory.".equals(result)) {
//            // Handle failure
//            System.out.println(result);
//        } else {
//            // Handle success
//            System.out.println("Theory published successfully for ingredient: " + result);
//
//        }
    }

}
