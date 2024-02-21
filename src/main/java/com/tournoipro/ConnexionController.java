package com.tournoipro;

import com.Entity.Joueur;
import com.Entity.Utilisateur;
import com.Service.AdministrateurService;
import com.Service.JoueurService;
import com.Service.UtilisateurService;
import com.Utils.Session;
import com.Utils.SwitchScenes;
import com.Utils.UserMessages;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.sql.SQLException;


public class ConnexionController {
    @javafx.fxml.FXML
    private TextField login;
    @javafx.fxml.FXML
    private PasswordField password;
    @FXML
    private Label passwordError;
    @FXML
    private Label loginError;
    private boolean loginValid;
    private boolean passwordValid;




    @FXML
    public void VerifyLogin(KeyEvent event) {
        // Add a listener to the text field to check the length of input
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 8) {
                loginValid = false;
                loginError.setText("Identifiant ne doit pas dépasser 8 caractères");
            }
            else {
                if (!newValue.matches("^[a-zA-Z0-9_]*$")){
                    loginValid = false;
                    loginError.setText("Identifiant doit contenir seuelement des caractères alphanumériques");
                }
                else {
                    loginError.setText("");
                    loginValid = true;
                }
            }
        });
    }

    @FXML
    public void Connect(Event event) throws SQLException {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = utilisateurService.recuperer(login.getText(),password.getText());
        JoueurService joueurService = new JoueurService();
        AdministrateurService administrateurService = new AdministrateurService();
        if (utilisateur == null)
            UserMessages.getInstance().Error("Utilisateur introuvable","Coordonnées erronées","Veuillez vérifier vos coordonnées");
        else{
            if ( utilisateur.getType().equals("JOU")){
                Session.getInstance().setJoueurConnected(joueurService.recuperer(utilisateur.getID_Utilisateur()));
                System.out.println(Session.getInstance().getJoueurConnected());
                //accés à l'interface joueur
            }
            else {
                Session.getInstance().setAdministrateurConnected(administrateurService.recuperer(utilisateur.getID_Utilisateur()));
                //accés à l'interface administrateur
                System.out.println(Session.getInstance().getAdministrateurConnected());
            }
        }

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
    public void goInscrit(Event event) throws IOException {
        SwitchScenes.getInstance().Switch("inscription");
    }
}
