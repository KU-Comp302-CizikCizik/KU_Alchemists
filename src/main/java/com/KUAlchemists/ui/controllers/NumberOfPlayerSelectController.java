package com.KUAlchemists.ui.controllers;
import com.KUAlchemists.backend.handlers.NumberOfPlayerSelectHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NumberOfPlayerSelectController {

    @FXML
    private Button four_player;

    @FXML
    private Button one_player;

    @FXML
    private Button three_player;

    @FXML
    private Button two_player;

    @FXML
    public void initialize() {
        one_player.setUserData("1");
        two_player.setUserData("2");
        three_player.setUserData("3");
        four_player.setUserData("4");
    }

    @FXML
    void onPlayerSelect(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String numberOfPlayers = (String) clickedButton.getUserData();
        NumberOfPlayerSelectHandler.getInstance().setNumberOfPlayers(numberOfPlayers);
    }
}
