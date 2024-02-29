package com.tournoipro;

import com.Service.JoueurService;
import com.Utils.Session;
import com.Utils.SwitchScenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeJoueurController implements Initializable {

    private Boolean HasTeam;
    private Boolean IsCaptain;
    private JoueurService joueurService = new JoueurService();
    @FXML
    private Button BtnAjoutTeam;

    @FXML
    private Button BtnIntegrerTeam;

    @FXML
    void AjouterEquipe(ActionEvent event) {
        System.out.println(Session.getJoueurConnected());
    }


    public void checkEquipe(ActionEvent event) {
            try {
                //test en dur
                SwitchScenes.getInstance().SwitchToCheckTeam("consultEquipe", (Stage) (((Node) event.getSource()).getScene().getWindow()), Session.getJoueurConnected().getID_Joueur());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void checkTournoi(ActionEvent event){
        try {
            //test en dur
            SwitchScenes.getInstance().SwitchToCheckTournoi("consultTournoi", (Stage) (((Node) event.getSource()).getScene().getWindow()), Session.getJoueurConnected().getID_Joueur());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            HasTeam = joueurService.HasTeam(Session.getJoueurConnected().getID_Joueur());
            IsCaptain = joueurService.IsCaptain(Session.getJoueurConnected().getID_Joueur());
            BtnAjoutTeam.setVisible(!HasTeam);
            BtnIntegrerTeam.setVisible(!HasTeam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
