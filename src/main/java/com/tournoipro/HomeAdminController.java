package com.tournoipro;

import com.Utils.SwitchScenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeAdminController {

    @FXML
    void GoEquipes(ActionEvent event) {

    }

    @FXML
    void GoJoueurs(ActionEvent event) {

    }

    @FXML
    void GoParties(ActionEvent event) throws IOException {
        SwitchScenes.getInstance().Switch("ListParties", (Stage) (((Node) event.getSource()).getScene().getWindow()));
    }

    @FXML
    void GoStades(ActionEvent event) {

    }

    @FXML
    void GoTournois(ActionEvent event) {

    }
}
