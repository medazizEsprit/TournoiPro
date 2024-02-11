package com.tournoipro;

import com.Entity.Stade;
import com.Service.StadeService;
import com.Utils.SwitchScenes;
import com.Utils.UserMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifierStadeController implements Initializable {
    @FXML
    private Button BtnModif;

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

    private int id;

    public void setId(int id) {
        this.id = id;
        StadeService stadeService = new StadeService();
        try {
            Stade stade = stadeService.recuperer(id);
            NomST.setText(stade.getNomStade());
            Lieu.setText(stade.getLieu());
            NbPart.setText(Integer.toString(stade.getNumSpectateurs()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @FXML
    void BtnModifier(ActionEvent event) {
        var a = id;
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
                        Stade stade = new Stade(id ,NomST.getText(), Integer.parseInt(NbPart.getText()), Lieu.getText());
                        StadeService stadeService = new StadeService();
                        try {
                            stadeService.modifier(stade);
                            Optional<ButtonType> result= UserMessages.getInstance().Information("Information","Stade modifié","Stade modifié avec succès");
                            if (result.get() == ButtonType.OK){
                                SwitchScenes.getInstance().Switch("ListStade");
                            }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
