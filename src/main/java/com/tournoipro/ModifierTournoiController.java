package com.tournoipro;

import com.Entity.Tournoi;
import com.Entity.Utilisateur;
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

public class ModifierTournoiController {
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
    void ModifierTournoi(ActionEvent event) {

    }

    @FXML
    void CancelTt(ActionEvent event) {

    }

}
