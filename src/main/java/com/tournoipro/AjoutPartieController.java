package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.Stade;
import com.Entity.Tournoi;
import com.Service.PartieService;
import com.Service.StadeService;
import com.Service.TournoiService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

public class AjoutPartieController implements Initializable {
    @FXML
    private DatePicker DateP;

    @FXML
    private ComboBox<String> Equipe1;

    @FXML
    private ComboBox<String> Equipe2;

    @FXML
    private ComboBox<String> Stade;

    @FXML
    private ComboBox<String> Tournoi;

    TournoiService tournoiService = new TournoiService();
    StadeService stadeService = new StadeService();
    PartieService partieService = new PartieService();

    private Map<Integer, Tournoi> mapTournoi = new java.util.HashMap<>();
    private Map<Integer, Equipe> mapEquipe1 = new java.util.HashMap<>();
    private Map<Integer, Equipe> mapEquipe2 = new java.util.HashMap<>();
    private Map<Integer, Stade> mapStade = new java.util.HashMap<>();

    @FXML
    void AjouterPartie(ActionEvent event) {
        int selectedIndexTournoi = Tournoi.getSelectionModel().getSelectedIndex();
        int selectedIndexEquipe1 = Equipe1.getSelectionModel().getSelectedIndex();
        int selectedIndexEquipe2 = Equipe2.getSelectionModel().getSelectedIndex();
        int selectedIndexStade = Stade.getSelectionModel().getSelectedIndex();

        Instant instant = Instant.from(DateP.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date dateP = Date.from(instant);

        try {
            partieService.ajout(new com.Entity.Partie(dateP, mapEquipe1.get(selectedIndexEquipe1), mapEquipe2.get(selectedIndexEquipe2), mapTournoi.get(selectedIndexTournoi), mapStade.get(selectedIndexStade)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Tournoi> tournois = new ArrayList<>();

        try {
            tournois = tournoiService.getListTournoi();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < tournois.size(); i++) {
            Tournoi.getItems().add(i, tournois.get(i).getNom_Tournoi());
            mapTournoi.put(i, tournois.get(i));
        }

        List<Stade> stades = new ArrayList<>();

        try {
            stades = stadeService.getListStade();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < stades.size(); i++) {
            Stade.getItems().add(i, stades.get(i).getNomStade());
            mapStade.put(i, stades.get(i));
        }

        Tournoi.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                Equipe1.getItems().clear();
                Equipe2.getItems().clear();
                int selectedIndex = Tournoi.getSelectionModel().getSelectedIndex();
                Object selectedItem = Tournoi.getSelectionModel().getSelectedItem();

                try {
                    List<Equipe> equipes = tournoiService.GetListEquipeByTournoi(mapTournoi.get(selectedIndex).getID_Tournoi());
                    for (int i = 0; i < equipes.size(); i++) {
                        Equipe1.getItems().add(i, equipes.get(i).getNom_Equipe());
                        mapEquipe1.put(i, equipes.get(i));
                    }

                    for (int i = 0; i < equipes.size(); i++) {
                        Equipe2.getItems().add(i, equipes.get(i).getNom_Equipe());
                        mapEquipe2.put(i, equipes.get(i));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}
