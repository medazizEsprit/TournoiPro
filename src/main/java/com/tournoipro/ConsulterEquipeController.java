package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.Joueur;
import com.Entity.Stade;
import com.Service.JoueurService;
import com.Service.StadeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterEquipeController implements Initializable {

    @FXML
    private TableView<Joueur> lstJoeursTV;
    @FXML
    private TableColumn<Joueur, Integer> nbrButs;
    @FXML
    private Text nomEquipe;
    @FXML
    private TableColumn<Joueur, String> position;
    @FXML
    private TableColumn<Joueur, String> nomJoueurs;
    @FXML
    private TableColumn<Joueur, String> prenomJoueur;

    private ObservableList<Joueur> JoueursList = FXCollections.observableArrayList();
    private int id;
    private JoueurService joueurService= new JoueurService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    public void setId(int id) {
        this.id = id;
        try {
            Joueur joueur = joueurService.recuperer(id);
            List<Joueur> lstJouers = joueurService.getListJoueur();

            nomEquipe.setText(joueur.getEquipe().getNom_Equipe());
            for (Joueur player : lstJouers){
                if (player.getEquipe().getID_Equipe() == joueur.getEquipe().getID_Equipe()){
                    JoueursList.add(player);
                }
            }
            lstJoeursTV.setItems(JoueursList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void loadData() {
        // refreshTable();
        nomJoueurs.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        prenomJoueur.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        position.setCellValueFactory(new PropertyValueFactory<>("Position"));
        nbrButs.setCellValueFactory(new PropertyValueFactory<>("Nbr_Buts"));
    }
}
