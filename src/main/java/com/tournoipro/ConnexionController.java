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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
                /*if (!newValue.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){
                    loginValid = false;
                    loginError.setText("Adresse mail invalide");
                }
                else {
                    loginError.setText("");
                    loginValid = true;
                }*/
        });
    }

    @FXML
    public void Connect(Event event) throws SQLException, IOException {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = utilisateurService.recuperer(login.getText(),password.getText());
        JoueurService joueurService = new JoueurService();
        AdministrateurService administrateurService = new AdministrateurService();
        if (utilisateur == null)
            UserMessages.getInstance().Error("Utilisateur introuvable","Coordonnées erronées","Veuillez vérifier vos coordonnées");
        else{
            if ( utilisateur.getType().equals("JOU")){
                Session.getInstance().setJoueurConnected(utilisateur.getID_Utilisateur());
                Joueur j = joueurService.recuperer(utilisateur.getID_Utilisateur());
                SwitchScenes.getInstance().Switch("HomeJoueur", new HomeJoueurController(), "HomeStyle" );
            }
            else {
                Session.getInstance().setAdministrateurConnected(utilisateur.getID_Utilisateur());
                //accés à l'interface administrateur
                System.out.println(Session.getInstance().getAdministrateurConnected());
                SwitchScenes.getInstance().Switch("HomeAdmin",new HomeAdminController(), "HomeStyle");
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
