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
    @FXML
    private Button inscrit;


    private boolean loginValid = true;
    private boolean passwordValid = true;
    private boolean firstnameValid = true;
    private boolean lastnameValid = true;
    @FXML
    public void Add(Event event) throws SQLException {
        JoueurService joueurService = new JoueurService();
        Joueur joueur = new Joueur(login.getText(),password.getText(),firstname.getText(),lastname.getText(),0,0,"",0);
        joueurService.ajout(joueur);
    }

    @FXML
    public void VerifyLogin(KeyEvent event) {
        // Add a listener to the text field to check the length of input
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 8) {
                loginValid = false;
                loginError.setText("Login ne doit pas dépasser 8 caractères");
            }
            else {
                if (!newValue.matches("^[a-zA-Z0-9_]*$")){
                    loginValid = false;
                    loginError.setText("Login doit contenir seuelement des caractères alphanumériques");
                }
                else {
                    loginError.setText("");
                    loginValid = true;
                }
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
}