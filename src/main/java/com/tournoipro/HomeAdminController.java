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
        try {
            SwitchScenes.getInstance().Switch("ListEquipe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Deconnect(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("connexion");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GoJoueurs(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("ListJoueurAdmin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GoParties(ActionEvent event) throws IOException {
        try {
            SwitchScenes.getInstance().Switch("ListParties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GoStades(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("Liststade1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GoTournois(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("ListTournoi");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GoTraiterEquipes(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("Demande");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GoTraiterTournois(ActionEvent event) {

    }
}
