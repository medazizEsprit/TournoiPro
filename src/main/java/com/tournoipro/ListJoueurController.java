package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.Joueur;
import com.Entity.Stade;
import com.Entity.Utilisateur;
import com.Service.EquipeService;
import com.Service.JoueurService;
import com.Service.StadeService;
import com.Service.UtilisateurService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListJoueurController implements Initializable {

    @FXML
    private ListView<String> ListJoueur;
    @FXML
    private Label NomJoueur;
    @FXML
    private Label NomEquipe;
    @FXML
    private Label NbrButs;
    @FXML
    private Label NbrAssists;
    @FXML
    private Label Position;
    @FXML
    private Button btnsup;
    @FXML
    private ImageView Captin;

    JoueurService joueurservice = new JoueurService();
    UtilisateurService utilisateurservice= new UtilisateurService();
    List<Joueur> listjoueur;
    List<String> listLibelle = new ArrayList<>();
    String CurrentJoueur;
    Joueur joueur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            listjoueur = joueurservice.getListJoueur();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

         String NomComplet;
        for (Joueur elm : listjoueur) {

            try {
                NomComplet= utilisateurservice.recuperer(elm.getID_Utilisateur()).getFirstName() + " " + utilisateurservice.recuperer(elm.getID_Utilisateur()).getLastName();
                listLibelle.add(NomComplet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        NomJoueur.setVisible(false);
        NomEquipe.setVisible(false);
        NbrButs.setVisible(false);
        NbrAssists.setVisible(false);
        Position.setVisible(false);
        btnsup.setVisible(false);
       Captin.setVisible(false);
        ListJoueur.getItems().addAll(listLibelle);
        ListJoueur.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                btnsup.setVisible(true);
                CurrentJoueur = ListJoueur.getSelectionModel().getSelectedItem();
                String Nom = CurrentJoueur.substring(0, CurrentJoueur.indexOf(' '));
                NomJoueur.setVisible(true);
                NomJoueur.setText(CurrentJoueur);
                String NomC="";
               for (Joueur elm : listjoueur) {
                   NomC= elm.getFirstName()+" "+elm.getLastName();
                    if (NomC.equals(CurrentJoueur)){
                        joueur=elm;
                    }
                }

                NomEquipe.setVisible(true);
                NbrButs.setVisible(true);
                NbrAssists.setVisible(true);
                Position.setVisible(true);


                NomEquipe.setText(joueur.getEquipe().getNom_Equipe());
                NbrButs.setText(Integer.toString(joueur.getNbr_Buts()));
                NbrAssists.setText(Integer.toString(joueur.getNbr_Assists()));
                Position.setText(joueur.getPosition());
                if (joueur.getCapitaine()==1){
                    Captin.setVisible(true);
                }


                btnsup.setOnAction(event -> {
                    try {
                        supprimerStadeSelectionne();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }

    private void supprimerStadeSelectionne() throws SQLException {
        // Récupérer l'index de l'élément sélectionné
        int selectedIndex = ListJoueur.getSelectionModel().getSelectedIndex();
        String selectedName = ListJoueur.getSelectionModel().getSelectedItem();
        // Vérifier si un élément est sélectionné
        if (selectedIndex >= 0) {
            // Supprimer l'élément de la liste
            ListJoueur.getItems().remove(selectedIndex);
            joueurservice.supprimerWithName(selectedName);
            System.out.println("Joueur supprimé avec succès.");
        } else {
            System.out.println("Joueur stade sélectionné.");
        }
    }

}
