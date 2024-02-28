package com.tournoipro;
import com.Entity.Joueur;
import com.Service.JoueurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CapitaineTraiteDemandeController implements Initializable {
    @FXML
    private TableColumn nomJoueur;
    @FXML
    private TableView joueurTV;
    @FXML
    private TableColumn prenomJoueur;

    @FXML
    private Button refuser;
    @FXML
    private TableColumn position;
    @FXML
    private Button accepter;

    private JoueurService joueurService = new JoueurService();
    private List<Joueur> joueurList;
    private ObservableList<Joueur> JoueurList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            joueurList = joueurService.getListJoueur();
            for (Joueur j : joueurList){
                Joueur joueur = new Joueur(j.getID_Utilisateur(),j.getLogin(),j.getPassword(),j.getType(),j.getFirstName(),j.getLastName(),j.getEquipe(),j.getNbr_Buts(),j.getNbr_Assists(),j.getPosition(),j.getCapitaine());
                JoueurList.add(joueur);
            }
            joueurTV.setItems(JoueurList);
            System.out.println(joueurList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
