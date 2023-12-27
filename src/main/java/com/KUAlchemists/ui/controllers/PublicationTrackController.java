package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.PublicationTrackHandler;
import com.KUAlchemists.backend.services.PublicationTrackService;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PublicationTrackController {
    private  PublicationTrackService publicationService;
    private final PublicationTrackHandler publicationhandler = PublicationTrackHandler.getInstance();

    @FXML
    private VBox v_box_1;

    @FXML
    private VBox v_box_2;
    @FXML
    private void initialize() {


        String info= publicationhandler.handleGetAllPublishedTheoriesInfo();
        System.out.println(info);
    }


}
