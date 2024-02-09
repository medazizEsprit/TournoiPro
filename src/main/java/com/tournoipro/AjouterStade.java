package com.tournoipro;

import com.Entity.Stade;
import com.Service.StadeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AjouterStade {
    @FXML
    private Button BtnAjout;

    @FXML
    private TextField Lieu;

    @FXML
    private TextField NbPart;

    @FXML
    private TextField NomST;

    @FXML
    private Label nomSTError;

    @FXML
    private Label lieuError;

    @FXML
    private Label nbPartError;

    @FXML
    void BtnAjouter(ActionEvent event) {
        if(NomST.getText().length() == 0){
            nomSTError.setText("Nom du stade est obligatoire");
        }else {
            nomSTError.setText("");
            if (Lieu.getText().length() == 0) {
                lieuError.setText("Lieu est obligatoire");
            } else {
                lieuError.setText("");
                if (NbPart.getText().length() == 0) {
                    nbPartError.setText("Nombre de participants est obligatoire");
                }else {
                    try {
                        int d = Integer.parseInt(NbPart.getText());
                        nbPartError.setText("");
                        Stade stade = new Stade(NomST.getText(), Integer.parseInt(NbPart.getText()), Lieu.getText());
                        StadeService stadeService = new StadeService();
                        try {
                            stadeService.ajout(stade);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (NumberFormatException nfe) {
                        nbPartError.setText("Nombre de participants doit être numérique");
                    }
                }
            }
        }
    }

    @FXML
    public void VerifyNomST(KeyEvent event) {
        NomST.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 0) {
                nomSTError.setText("Nom du stade est obligatoire");
            }
            else nomSTError.setText("");
        });
    }

    @FXML
    public void VerifyLieu(KeyEvent event) {
        Lieu.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 0) {
                lieuError.setText("Lieu est obligatoire");
            }
            else lieuError.setText("");
        });
    }

    @FXML
    public void VerifyNbPart(KeyEvent event) {
        NbPart.textProperty().addListener((observable, oldValue, newValue) -> {
            Boolean isValid = true;
            if (newValue.length() == 0) {
                nbPartError.setText("Nombre de participants est obligatoire");
                isValid = false;
            }

            if (isValid) {
                try {
                    int d = Integer.parseInt(newValue);
                } catch (NumberFormatException nfe) {
                    nbPartError.setText("Nombre de participants doit être numérique");
                    isValid = false;
                }
            }

            if (isValid)
                nbPartError.setText("");
        });
    }
}
