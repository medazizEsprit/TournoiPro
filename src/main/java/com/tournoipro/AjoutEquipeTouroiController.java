package com.tournoipro;

import com.Entity.*;
import com.Service.*;
import com.Utils.SwitchScenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AjoutEquipeTouroiController  implements Initializable {
    @javafx.fxml.FXML
    private Button Rejoindre;
    UtilisateurService utilisateurService= new UtilisateurService();
    ParticipationService participationService= new ParticipationService();
    TournoiService tournoiService= new TournoiService();
    @javafx.fxml.FXML
    private TableView<Tournoi> ListTournoi;
    @javafx.fxml.FXML
    private TableColumn<Tournoi,String> name;
    List<Tournoi> tournoiList= new ArrayList<Tournoi>();
    List<Tournoi> tournoiList2= new ArrayList<Tournoi>();
    JoueurService joueurService = new JoueurService();
    DemandeService demandeService1= new DemandeService();
    Joueur  joueur= new Joueur();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            joueur = joueurService.recuperer(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            if (utilisateurService.IsCaptine(2)){
                Rejoindre.setVisible(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            tournoiList2=tournoiService.getListTournoi();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Tournoi tournoi :tournoiList2) {
            if ((participationService.exsit(tournoi.getID_Tournoi(),joueur.getEquipe().getID_Equipe())==false) &&(demandeService1.exsit(tournoi.getID_Tournoi(),joueur.getEquipe().getID_Equipe())==false)){
                tournoiList.add(tournoi);
            }
        }
        name.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("Nom_Tournoi"));
        ObservableList<Tournoi> TournoiObservableList1 = FXCollections.observableArrayList(tournoiList);
        ListTournoi.setItems(TournoiObservableList1);
        Rejoindre.setOnAction((event)->
                {
                    Tournoi tournoi = ListTournoi.getSelectionModel().getSelectedItem();
                    EquipeService equipeService = new EquipeService();
                    Demande demande = new Demande();
                    Joueur  joueur1= new Joueur();



                    try {
                        joueur1 = joueurService.recuperer(2);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                        demande.setEquipe(joueur1.getEquipe());


                    demande.setTournoi(tournoi);
                    DemandeService demandeService= new DemandeService();
                    try {
                        demandeService.ajout(demande);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    List<Tournoi> tournoiList3 = new ArrayList<Tournoi>();

                    try {
                         tournoiList3 = tournoiService.getListTournoi();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    try {
                        joueur = joueurService.recuperer(2);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    List<Tournoi> tournoiList4 = new ArrayList<Tournoi>();
                    for (Tournoi tournoi1 :tournoiList3) {
                        if ((participationService.exsit(tournoi1.getID_Tournoi(),joueur.getEquipe().getID_Equipe())==false) &&(demandeService.exsit(tournoi1.getID_Tournoi(),joueur.getEquipe().getID_Equipe())==false)){
                            tournoiList4.add(tournoi1);
                        }
                    }

                    name.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("Nom_Tournoi"));
                    ObservableList<Tournoi> TournoiObservableList = FXCollections.observableArrayList(tournoiList4);
                    ListTournoi.setItems(TournoiObservableList);

                }

        );


    }
}
