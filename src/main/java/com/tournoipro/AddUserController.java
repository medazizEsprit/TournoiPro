package com.tournoipro;
import com.Entity.Joueur;
import com.Service.JoueurService;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;

public class AddUserController {
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

    private boolean loginValid = true;
    @FXML
    public void Add(Event event) throws SQLException {
        JoueurService joueurService = new JoueurService();
        Joueur joueur = new Joueur(login.getText(),password.getText(),firstname.getText(),lastname.getText(),0,0,"",0);
        joueurService.ajout(joueur);
    }

    @FXML
    public void VerifyLoginLength(KeyEvent event) {
        // Add a listener to the text field to check the length of input
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 6) {
                loginValid = false;
                loginError.setText("Login ne doit pas dépasser 6 caractères");
            }
            else loginError.setText("");
        });    }
}
