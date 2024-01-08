package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.PublicationTrackHandler;
import com.KUAlchemists.backend.services.PublicationTrackService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
public class PublicationTrackController {
    private PublicationTrackHandler publicationhandler;
    @FXML
    private GridPane other_v_box;

    @FXML
    private GridPane player_box;
    @FXML
    private VBox v_box_1;

    @FXML
    private VBox v_box_2;
    @FXML
    private void initialize() {

        int column = 0;
        int row = 0;
        publicationhandler = PublicationTrackHandler.getInstance();

        String allPublishedTheoriesInfo= publicationhandler.handleGetAllPublishedTheoriesInfo();
        String playerPublishedTheoriesInfo=publicationhandler.handleGetPlayerPublishedTheoriesInfo();
        String[] lines=allPublishedTheoriesInfo.split("\n");
        String[] lines2=playerPublishedTheoriesInfo.split("\n");
        try{

        for(int i = 0; i <lines.length; i++) {
            String[] everything=lines[i].split(" ");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("TheoryCardUI.fxml"));
            VBox cardBox = fxmlLoader.load();
            TheoryCardController controller = fxmlLoader.getController();
            controller.setAlchemy(everything[1],everything[2],everything[3]);
            controller.setIngredient(everything[0]);

            if (column == 4) {
                column = 0;
                row++;
            }
            other_v_box.add(cardBox, column++, row);
            GridPane.setMargin(cardBox, new Insets(1,10,1,1));
        }
    }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try{

            column = 0;
            row = 0;

            for(int i = 0; i <lines2.length; i++) {

                String[] everything=lines2[i].split(" ");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("TheoryCardUI.fxml"));

                VBox cardBox = fxmlLoader.load();

                TheoryCardController controller = fxmlLoader.getController();
                controller.setAlchemy(everything[1],everything[2],everything[3]);
                controller.setIngredient(everything[0]);

                if (column == 4) {
                    column = 0;
                    row++;
                }

                player_box.add(cardBox, column++, row);

                GridPane.setMargin(cardBox, new Insets(1,10,1,1));
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
