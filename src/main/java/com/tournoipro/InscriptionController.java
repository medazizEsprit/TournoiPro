package com.tournoipro;
import com.Entity.Joueur;
import com.Service.JoueurService;
import com.Utils.SwitchScenes;
import com.Utils.UserMessages;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class InscriptionController {
    @FXML
    private TextField firstname;
    @FXML
    private TextField login;
    @FXML
    private TextField lastname;
    @FXML
    private PasswordField password;
    @FXML
    private Label lastnameError;
    @FXML
    private Label passwordError;
    @FXML
    private Label loginError;
    @FXML
    private Label firstnameError;
    @FXML
    private Button inscrit;


    private boolean loginValid = true;
    private boolean passwordValid = true;
    private boolean firstnameValid = true;
    private boolean lastnameValid = true;
    @FXML
    private Label passwordError1;
    @FXML
    private Label loginError1;

    @FXML
    public void Add(Event event) throws SQLException {
        if((loginValid && passwordValid && firstnameValid && lastnameValid && !(firstname.getText().isEmpty()||lastname.getText().isEmpty()||login.getText().isEmpty()||password.getText().isEmpty())))
        {
            JoueurService joueurService = new JoueurService();
            if(!joueurService.VerifLogin(login.getText())){
                Joueur joueur = new Joueur(login.getText(),password.getText(),firstname.getText(),lastname.getText(),0,0,"",0);
                joueurService.ajout(joueur);
                try {
                    SwitchScenes.getInstance().Switch("connexion");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else UserMessages.getInstance().Error("Erreur inscription","Identifiant existant","l'identifiant désiré existe déjà !!");

        }
        else UserMessages.getInstance().Error("Erreur inscription","Champs invalides","Veuillez vérifier les champs !!");
    }

    @FXML
    public void VerifyLogin(KeyEvent event) {
        // Add a listener to the text field to check the length of input
        login.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){
                    loginValid = false;
                    loginError.setText("Adresse mail invalide");
                }
                else {
                    loginError.setText("");
                    loginValid = true;
                }
        });
    }


    @FXML
    public void VerifyPassword(Event event) {
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 10) {
                passwordValid = false;
                passwordError.setText("Mot de passe ne doit pas dépasser 10 caractères");
            }
            else {
                if (!newValue.matches("^[a-zA-Z0-9]*$")){
                    passwordValid = false;
                    passwordError.setText("Le mot de passe doit contenir seuelement des caractères alphanumériques");
                }
                else {
                    passwordError.setText("");
                    passwordValid = true;
                }
                }
        });
    }

    @FXML
    public void VerifyLastName(Event event) {
        lastname.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 16) {
                lastnameValid = false;
                lastnameError.setText("Le nom ne doit pas dépasser 16 caractères");
            }
            else {
                if (!newValue.matches("^([a-zA-Z0-9]+ )*[a-zA-Z0-9]+$")){
                    lastnameValid = false;
                    lastnameError.setText("Le nom doit contenir seuelement des lettres (Espace est seulement autorisé entre deux mots)");
                }
                else {
                    lastnameError.setText("");
                    lastnameValid = true;
                }
            }
        });
    }

    @FXML
    public void VerifyFirstName(Event event) {
        firstname.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 16) {
                firstnameValid = false;
                firstnameError.setText("Le prénom ne doit pas dépasser 16 caractères");
            }
            else {
                if (!newValue.matches("^([a-zA-Z0-9]+ )*[a-zA-Z0-9]+$")){
                    firstnameValid = false;
                    firstnameError.setText("Le prénom doit contenir seuelement des lettres (Espace est seulement autorisé entre deux mots)");
                }
                else {
                    firstnameError.setText("");
                    firstnameValid = true;
                }
            }
        });
    }

    @FXML
    public void goConnect(Event event) throws IOException {
        SwitchScenes.getInstance().Switch("connexion");
    }
}
