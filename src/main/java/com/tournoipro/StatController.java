package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.Joueur;
import com.Service.JoueurService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StatController implements Initializable {

    @FXML
    private Text nomEquipe;
    @FXML
    private PieChart pieChart;

    int idEquipe;
    private JoueurService joueurService = new JoueurService();
    private ObservableList<Joueur> JoueursList = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

   }

    private void initializeStatistics() {
        try {
            Joueur joueur = joueurService.recuperer(idEquipe);
            List<Joueur> lstJouers = joueurService.getListJoueur();

            nomEquipe.setText(joueur.getEquipe().getNom_Equipe());

            for (Joueur player : lstJouers) {
                if (player.getEquipe().getID_Equipe() == idEquipe) {
                    JoueursList.add(player);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (Joueur joueur : JoueursList) {
            pieChartData.add(new PieChart.Data(joueur.getFirstName() + " " + joueur.getLastName(),
                    joueur.getNbr_Buts()));
        }

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " Nombre de but: ", data.pieValueProperty()
                        )
                )
        );

        pieChart.getData().addAll(pieChartData);
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
        initializeStatistics();
    }

}
