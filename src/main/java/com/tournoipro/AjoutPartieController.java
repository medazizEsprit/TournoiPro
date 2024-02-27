package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.Stade;
import com.Entity.Tournoi;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AjoutPartieController implements Initializable {
    @FXML
    private DatePicker DateP;

    @FXML
    private ComboBox<Equipe> Equipe1;

    @FXML
    private ComboBox<Equipe> Equipe2;

    @FXML
    private ComboBox<Stade> Stade;

    @FXML
    private ComboBox<String> Tournoi;

    private Map<Integer, Tournoi> mapTournoi = new java.util.HashMap<>();

    @FXML
    void AjouterPartie(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Tournoi> tournois = new ArrayList<>();
        TournoiService tournoiService = new TournoiService();
        try {
            tournois = tournoiService.getListTournoi();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < tournois.size(); i++) {
            Tournoi.getItems().add(i, tournois.get(i).getNom_Tournoi());
            mapTournoi.put(i, tournois.get(i));
        }

        Tournoi.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                int selectedIndex = Tournoi.getSelectionModel().getSelectedIndex();
                Object selectedItem = Tournoi.getSelectionModel().getSelectedItem();

                System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
                System.out.println("   ComboBox.getValue(): " + Tournoi.getValue());
                System.out.println("ID: " + mapTournoi.get(selectedIndex).getID_Tournoi());
            }
        });
    }
}
