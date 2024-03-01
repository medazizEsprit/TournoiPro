package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.Stade;
import com.Entity.Tournoi;
import com.Service.StadeService;
import com.Service.TournoiService;
import javafx.beans.Observable;
import com.Service.EquipeService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ListeTournoiController implements Initializable {
    @FXML
    private TableView<Tournoi> tournoiTV;
    @FXML
    private TableColumn<Tournoi, Integer> ID_Tournoi;
    @FXML
    private TableColumn<Tournoi, String> Nom_Tournoi;

    @FXML
    private TableColumn<Tournoi, Date> Date_Debut;

    @FXML
    private TableColumn<Tournoi, Date> Date_Fin;
    @FXML
    private TableColumn<Tournoi, Integer> Nbr_Equipe;
    @FXML
    private Button Ajouter;

    private TournoiService tournoiService = new TournoiService();
    private List<Tournoi> tournoiListe;
    private ObservableList<Tournoi> TournoiList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    @FXML
    public void refreshTable(){
        try {
            tournoiListe = tournoiService.getListTournoi();
            for (Tournoi t : tournoiListe){
                Tournoi tournoi = new Tournoi(t.getID_Tournoi(), t.getNom_Tournoi(),t.getDate_Debut(),t.getDate_Fin(),t.getNbr_Equipe());
                TournoiList.add(tournoi);
            }
            tournoiTV.setItems(TournoiList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editData (){
        Nom_Tournoi.setCellFactory(TextFieldTableCell.forTableColumn());
        Nom_Tournoi.setOnEditCommit(event ->{
            Tournoi tournoi = event.getTableView().getItems().get(event.getTablePosition().getRow());
            tournoi.setNom_Tournoi(event.getNewValue());
            System.out.println("EDIT DONE");
            try {
                tournoiService.modifier(tournoi);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        Nbr_Equipe.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        }));
        Nbr_Equipe.setOnEditCommit(event ->{
            Tournoi tournoi = event.getTableView().getItems().get(event.getTablePosition().getRow());
            tournoi.setNbr_Equipe(event.getNewValue());
            System.out.println("EDIT DONE");
            try {
                tournoiService.modifier(tournoi);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });




    }

    @FXML
    public void deleteData(ActionEvent event){

        TableView.TableViewSelectionModel<Tournoi> selectedModel = tournoiTV.getSelectionModel();
        if (selectedModel.isEmpty()) {
            System.out.println("You should select a row to delete !");
            return;
        }

        ObservableList<Tournoi> selectedItems = selectedModel.getSelectedItems();

        for (Tournoi tournoi : selectedItems) {
            try {
                tournoiService.supprimer(tournoi.getID_Tournoi());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        tournoiTV.getItems().removeAll(selectedItems);
    }

    @FXML
    void handleAjouter(ActionEvent event) {
        try {
            // Charger l'interface FXML pour ajouter un tournoi existant
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTournoi.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec la racine chargée
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir du bouton et changer sa racine
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs d'entrée/sortie si le chargement de l'interface échoue
        }
    }


    private void loadData() {
        refreshTable();
        ID_Tournoi.setCellValueFactory(new PropertyValueFactory<>("ID_Tournoi"));
        Nom_Tournoi.setCellValueFactory(new PropertyValueFactory<>("Nom_Tournoi"));
        Date_Debut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
        Date_Fin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
        Nbr_Equipe.setCellValueFactory(new PropertyValueFactory<>("Nbr_Equipe"));

        editData();
    }
}
