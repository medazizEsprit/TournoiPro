package com.tournoipro;

import com.Entity.Stade;
import com.Entity.Tournoi;
import com.Entity.Utilisateur;
import com.Service.StadeService;
import com.Service.TournoiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class AjouterTournoiController {
    @FXML
    private Button AddBtn;

    @FXML
    private Button CancelBtn;

    @FXML
    private DatePicker DateDeb;

    @FXML
    private DatePicker DateFin;

    @FXML
    private TextField NbreEq;

    @FXML
    private TextField NomTournoi;

    @FXML
    private Label NomTournoiError;
    @FXML
    private Label DateDebError;
    @FXML
    private Label DateFinError;
    @FXML
    private Label NbreEqError;

    @FXML
    void AjoutTournoi(ActionEvent event) {
        Instant instantD = Instant.from(DateDeb.getValue().atStartOfDay(ZoneId.systemDefault()));
        Instant instantF = Instant.from(DateDeb.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date dateD = Date.from(instantD);
        Date dateF = Date.from(instantF);
        Tournoi tournoi = new Tournoi(NomTournoi.getText(), dateD, dateF, Integer.parseInt(NbreEq.getText()), new Utilisateur(1));
        TournoiService tournoiService = new TournoiService();
        try {
            tournoiService.ajout(tournoi);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void CancelT(ActionEvent event) {

    }
}
