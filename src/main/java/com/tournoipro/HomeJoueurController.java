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
    private Button btnConsult;

    @FXML
    void IntegrerEquipe(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("JoueurDemandeRejoint");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void AjouterEquipe(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("ajoutEquipe");
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

    public void checkEquipe(ActionEvent event) {
            try {
                SwitchScenes.getInstance().SwitchToCheckTeam("consultEquipe", (Stage) (((Node) event.getSource()).getScene().getWindow()), Session.getJoueurConnected());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void checkTournoi(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            System.out.println(Session.getInstance().getJoueurConnected());
            HasTeam = joueurService.HasTeam(Session.getInstance().getJoueurConnected());
            IsCaptain = joueurService.IsCaptain(Session.getInstance().getJoueurConnected());
            BtnAjoutTeam.setVisible(!HasTeam);
            BtnIntegrerTeam.setVisible(!HasTeam);
            btnConsult.setVisible(HasTeam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
