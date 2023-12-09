package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.services.PublicationService;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import com.KUAlchemists.backend.handlers.PublicationHandler;

import java.util.List;

public class PublicationTrackController {
    private  PublicationService publicationService;
    private PublicationHandler publicationhandler;

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
