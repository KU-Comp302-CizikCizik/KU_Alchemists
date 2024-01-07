package com.KUAlchemists.ui.controllers;
import com.KUAlchemists.backend.enums.ApplicationMode;
import com.KUAlchemists.backend.handlers.ApplicationModeHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ApplicationModeSelectingController {

    @FXML
    private Button offline_b;

    @FXML
    private Button online_b;

    @FXML
    void offline_b(ActionEvent event) {
       SceneLoader.getInstance().loadNumberOfPlayersScreen();
        ApplicationModeHandler.getInstance().setApplicationMode(ApplicationMode.OFFLINE);
    }

    @FXML
    void online_b(ActionEvent event) {
        SceneLoader.getInstance().loadOnlineGameRoomScreen();
        ApplicationModeHandler.getInstance().setApplicationMode(ApplicationMode.ONLINE);

    }

}
