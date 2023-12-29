package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.PublicationTrackHandler;
import com.KUAlchemists.backend.services.PublicationTrackService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
public class PublicationTrackController {
    private  PublicationTrackService publicationService;
    private final PublicationTrackHandler publicationhandler = PublicationTrackHandler.getInstance();
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
        String info= publicationhandler.handleGetAllPublishedTheoriesInfo();
        String player_info=publicationhandler.handleGetPlayerPublishedTheoriesInfo();
        System.out.println(info);
        String[] lines=info.split("\n");
        System.out.println(lines.length);
        try{


        for(int i = 0; i <lines.length; i++) {
            String[] everything=lines[i].split(" ");
            System.out.println("done");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("TheoryCardUI.fxml"));
            System.out.println("done");
           VBox cardBox = fxmlLoader.load();
            System.out.println("done");
            TheoryCardController controller = fxmlLoader.getController();
            controller.setAlchemy(everything[1],everything[2],everything[3]);
            controller.setIngredient(everything[0]);

            if (column == 4) {
                column = 0;
                row++;
            }
            System.out.println("done");
            other_v_box.add(cardBox, column++, row);
            System.out.println("done");
            GridPane.setMargin(cardBox, new Insets(1,1,1,1));
        }
    }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
