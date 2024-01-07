package com.KUAlchemists.ui.controllers;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameModeSelectingController {

    @FXML
    private Button offline_b;

    @FXML
    private Button online_b;

    @FXML
    void offline_b(ActionEvent event) {
       SceneLoader.getInstance().loadNumberOfPlayersScreen();
    }

    @FXML
    void online_b(ActionEvent event) {
        SceneLoader.getInstance().loadOnlineGameRoomScreen();
    }

}
