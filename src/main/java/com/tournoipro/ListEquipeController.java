package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.Stade;
import com.Service.StadeService;
import com.Utils.SwitchScenes;
import javafx.beans.Observable;
import com.Service.EquipeService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ListEquipeController implements Initializable {
    @FXML
    private TableView<Equipe> equipeTV;
    @FXML
    private TableColumn<Equipe, Integer> nbrJoueur;
    @FXML
    private TableColumn<Equipe, String> nomEquipe;

    private EquipeService equipeService = new EquipeService();
    private List<Equipe> equipeListe;
    private ObservableList<Equipe> EquipeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    @FXML
    public void refreshTable(){
        try {
            equipeListe = equipeService.getListEquipe();
            for (Equipe eq : equipeListe){
                Equipe equipe = new Equipe(eq.getID_Equipe(), eq.getNom_Equipe(), eq.getNbr_Joueur());
                EquipeList.add(equipe);
            }
            equipeTV.setItems(EquipeList);
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    public void editData (){
        nomEquipe.setCellFactory(TextFieldTableCell.forTableColumn());
        nomEquipe.setOnEditCommit(event ->{
            Equipe equipe = event.getTableView().getItems().get(event.getTablePosition().getRow());
            equipe.setNom_Equipe(event.getNewValue());
            System.out.println("EDIT DONE");
            try {
                equipeService.modifier(equipe);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        nbrJoueur.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        }));
        nbrJoueur.setOnEditCommit(event ->{
            Equipe equipe = event.getTableView().getItems().get(event.getTablePosition().getRow());
            equipe.setNbr_Joueur(event.getNewValue());
            System.out.println("EDIT DONE");
            try {
                equipeService.modifier(equipe);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void Ajouter(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("ajoutEquipe");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Retour(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("HomeAdmin", new HomeAdminController(), "HomeStyle");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void deleteData(ActionEvent event){

        TableView.TableViewSelectionModel<Equipe> selectedModel = equipeTV.getSelectionModel();
        if (selectedModel.isEmpty()) {
            System.out.println("You should select a row to delete !");
            return;
        }

        ObservableList<Equipe> selectedItems = selectedModel.getSelectedItems();

        for (Equipe equipe : selectedItems) {
            try {
                equipeService.supprimer(equipe.getID_Equipe());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        equipeTV.getItems().removeAll(selectedItems);
    }

    private void loadData() {
        refreshTable();
        nbrJoueur.setCellValueFactory(new PropertyValueFactory<>("Nbr_Joueur"));
        nomEquipe.setCellValueFactory(new PropertyValueFactory<>("Nom_Equipe"));

        editData();
    }
}
