package com.tournoipro;

import com.Entity.Tournoi;
import com.Entity.Utilisateur;
import com.Service.TournoiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AjouterTournoiController {
    @FXML
    private TextField NomTournoi;
    @FXML
    private DatePicker DateDeb;
    @FXML
    private DatePicker DateFin;
    @FXML
    private TextField NbreEq;
    @FXML
    private Label NomTournoiError;
    @FXML
    private Label DateDebError;
    @FXML
    private Label DateFinError;
    @FXML
    private Label NbreEqError;

    @FXML
    public void AjoutTournoi(ActionEvent event) {
        if (validateInput()) {
            Instant instantD = Instant.from(DateDeb.getValue().atStartOfDay(ZoneId.systemDefault()));
            Instant instantF = Instant.from(DateFin.getValue().atStartOfDay(ZoneId.systemDefault()));
            Date dateD = Date.from(instantD);
            Date dateF = Date.from(instantF);

            Tournoi tournoi = new Tournoi(NomTournoi.getText(), dateD, dateF, Integer.parseInt(NbreEq.getText()), new Utilisateur(1));

            TournoiService tournoiService = new TournoiService();
            try {
                tournoiService.ajout(tournoi);
                // Tournoi ajouté avec succès, vous pouvez faire quelque chose ici si nécessaire
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean validateInput() {
        boolean isValid = true;
        NomTournoiError.setText("");
        DateDebError.setText("");
        DateFinError.setText("");
        NbreEqError.setText("");

        if (NomTournoi.getText().isEmpty()) {
            NomTournoiError.setText("Le nom du tournoi est requis.");
            isValid = false;
        } else if (!NomTournoi.getText().matches("[a-zA-Z]{8,}")) {
            NomTournoiError.setText("Le nom du tournoi doit contenir au moins 8 lettres.");
            isValid = false;
        }

        if (DateDeb.getValue() == null) {
            DateDebError.setText("La date de début est requise.");
            isValid = false;
        } else if (DateDeb.getValue().isBefore(LocalDate.now())) {
            DateDebError.setText("La date de début doit être égale ou supérieure à la date actuelle.");
            isValid = false;
        }

        if (DateFin.getValue() == null) {
            DateFinError.setText("La date de fin est requise.");
            isValid = false;
        } else if (DateFin.getValue().isBefore(DateDeb.getValue())) {
            DateFinError.setText("La date de fin doit être postérieure à la date de début.");
            isValid = false;
        }

        if (NbreEq.getText().isEmpty()) {
            NbreEqError.setText("Le nombre d'équipes est requis.");
            isValid = false;
        } else {
            try {
                int nbreEquipes = Integer.parseInt(NbreEq.getText());
                if (nbreEquipes <= 0) {
                    NbreEqError.setText("Le nombre d'équipes doit être supérieur à zéro.");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                NbreEqError.setText("Le nombre d'équipes doit être un nombre entier.");
                isValid = false;
            }
        }

        return isValid;
    }
}
