package com.tournoipro;

import com.Entity.Equipe;
import com.Service.EquipeService;
import com.Utils.UserMessages;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class AjoutEquipeController {
    @FXML
    private TextField nomEquipe;
    @FXML
    private Label nomErreur;
    @FXML
    private Label nbrJoueursErreur;
    @FXML
    private TextField nbrJoueurs;

    private boolean nomEquipeValid;
    private boolean nbrJoueursValid;
    @FXML
    public void VerifyName(Event event) {
        nomEquipe.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^([a-zA-Z0-9]+ )*[a-zA-Z0-9]+$")){
                nomEquipeValid = false;
                nomErreur.setText("Le nom doit contenir seuelement des lettres \n (Espace est seulement autorisé entre deux mots)");
            }
            else {
                nomErreur.setText("");
                nomEquipeValid = true;
            }
        });
    }

    @FXML
    public void VerifyNumber(Event event) {
        nbrJoueurs.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[0-9]*$")){
                nbrJoueursValid = false;
                nbrJoueursErreur.setText("Veuillez saisir seulement des chiffres");
            }
            else {
                nbrJoueursErreur.setText("");
                nbrJoueursValid = true;
            }
        });
    }

    @FXML
    public void Add(Event event) throws SQLException {
        EquipeService equipeService = new EquipeService();
        if (nomEquipeValid && nbrJoueursValid && !(nomEquipe.getText().isEmpty() || nbrJoueurs.getText().isEmpty())){
            if (!equipeService.existName(nomEquipe.getText())) {
                Equipe equipe = new Equipe(nomEquipe.getText(), Integer.parseInt(nbrJoueurs.getText()));
                equipeService.ajout(equipe);
            }else UserMessages.getInstance().Error("Erreur ajout","Nom d'équipe existe","Veuillez changer le nom de votre équipe!!");
        }else  UserMessages.getInstance().Error("Erreur ajout","Champs invalide","Veuillez vérifier vos champs!!");


    }
}
