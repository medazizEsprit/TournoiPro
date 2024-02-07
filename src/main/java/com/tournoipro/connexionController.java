package com.tournoipro;

import com.Entity.Utilisateur;
import com.Service.UtilisateurService;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;


public class connexionController {
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
    public void VerifyLoginLength(KeyEvent event) {
        // Add a listener to the text field to check the length of input
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 8) {
                loginValid = false;
                loginError.setText("Login ne doit pas dépasser 8 caractères");
            }
            else loginError.setText("");
        });
    }

    @FXML
    public void Connect(Event event) throws SQLException {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = utilisateurService.recuperer(login.getText(),password.getText());
        System.out.println(utilisateur);
    }
}
