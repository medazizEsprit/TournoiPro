package com.tournoipro;

import com.Entity.Joueur;
import com.Entity.Participation;
import com.Entity.Partie;
import com.Entity.Tournoi;
import com.Service.*;
import com.Utils.Session;
import com.Utils.SwitchScenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterTournoiController implements Initializable {
    @FXML
    private TableColumn<Tournoi, Date> dateDebut;
    @FXML
    private TableColumn<Tournoi, Date> dateFin;
    @FXML
    private TableView<Tournoi> lstTournoiJoueur;
    @FXML
    private TableColumn<Tournoi, Integer> nbrEquipe;
    @FXML
    private TableColumn<Tournoi, String> nomTournoi;

    private int id;
    private PartieService partieService = new PartieService();
    private TournoiService TournoiService = new TournoiService();
    private JoueurService joueurService = new JoueurService();
    private EquipeService equipeService = new EquipeService();
    private ParticipationService participationService = new ParticipationService();
    private ObservableList<Tournoi> TournoiList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
        nbrEquipe.setCellValueFactory(new PropertyValueFactory<>("Nbr_Equipe"));
        nomTournoi.setCellValueFactory(new PropertyValueFactory<>("Nom_Tournoi"));
    }

    public void checkTournoi(int id) {
        this.id = id;
        try {
            List<Participation> lstParticipation = participationService.RecupererParticipations(id);
            Joueur joueur = joueurService.recuperer(id);
            for (Participation par : lstParticipation){
                Tournoi tournoi = TournoiService.recuperer(par.getTournoi().getID_Tournoi());
                TournoiList.add(tournoi);
            }
            lstTournoiJoueur.setItems(TournoiList);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void Cancel(ActionEvent event) {
        try {
            SwitchScenes.getInstance().SwitchToCheckTeam("consultEquipe", (Stage) (((Node) event.getSource()).getScene().getWindow()), Session.getJoueurConnected());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
