package com.tournoipro;

import com.Utils.SwitchScenes;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HomeJoueurController {

    public void checkEquipe(ActionEvent event) {
            try {
                //test en dur
                SwitchScenes.getInstance().SwitchToCheckTeam("consultEquipe", (Stage) (((Node) event.getSource()).getScene().getWindow()), 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
