package com.KUAlchemists.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PauseController {

    @FXML
    private TextArea description;

    @FXML
    private ImageView notifacationImage;

    @FXML
    private AnchorPane scene;

    Stage stage;
    @FXML
    void resumeButton(ActionEvent event) {
        stage = (Stage) scene.getScene().getWindow();
        stage.close();
    }

}
