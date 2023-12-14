package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.services.PublicationTrackService;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import com.KUAlchemists.backend.handlers.PublicationTrackHandler;

import java.util.List;

public class PublicationTrackController {
    private  PublicationTrackService publicationService;
    private PublicationTrackHandler publicationhandler;

    @FXML
    private VBox v_box_1;

    @FXML
    private VBox v_box_2;
    @FXML
    private void initialize() {


     /*   String info= publicationhandler.handleGetPublishedTheoriesInfo();
        System.out.println(info);*/
    }


}
