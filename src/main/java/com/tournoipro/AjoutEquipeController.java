package com.tournoipro;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    @FXML
    public void VerifyName(Event event) {
        nomEquipe.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^([a-zA-Z0-9]+ )*[a-zA-Z0-9]+$")){
                nomEquipeValid = false;
                nomErreur.setText("Le nom doit contenir seuelement des lettres (Espace est seulement autorisé entre deux mots)");
            }
            else {
                nomErreur.setText("");
                nomEquipeValid = true;
            }
        });
    }

    @FXML
    public void VerifyNumber(Event event) {
        //Gestion de nombre
    }

    @FXML
    public void Add(Event event) {
    }
}
