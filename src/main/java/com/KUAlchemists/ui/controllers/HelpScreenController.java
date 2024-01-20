package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import com.KUAlchemists.backend.handlers.HelpScreenHandler;
public class HelpScreenController {

    @FXML
    private Text text;

    @FXML
    private TextFlow text_flow;



    @FXML
    private void initialize() {
        text.setText(HelpScreenHandler.getInstance().handleGetHelpContent());

    }


}