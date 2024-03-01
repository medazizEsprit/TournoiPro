package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.Joueur;
import com.Service.JoueurService;
import com.Utils.SwitchScenes;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListeJoueurAdminController implements Initializable {
    @FXML
    private Button btnSupp;
    @FXML
    private TableView joueurTV;
    @FXML
    private TableColumn <Joueur,String> nomJoueur;
    @FXML
    private TableColumn <Joueur,String> login;
    @FXML
    private TableColumn <Joueur, Integer> buts;
    @FXML
    private TableColumn <Joueur,String> equipe;
    @FXML
    private TableColumn <Joueur,Integer> assissts;
    @FXML
    private TableColumn <Joueur,String> prenomJoueur;

    private JoueurService joueurService = new JoueurService();
    private List<Joueur> joueurList;
    private ObservableList<Joueur> JoueurList = FXCollections.observableArrayList();

    @FXML
    public void deleteData(ActionEvent actionEvent) {
        TableView.TableViewSelectionModel<Joueur> selectedModel = joueurTV.getSelectionModel();
        if (selectedModel.isEmpty()) {
            System.out.println("You should select a row to delete !");
            return;
        }

        ObservableList<Joueur> selectedItems = selectedModel.getSelectedItems();

        for (Joueur joueur : selectedItems) {
            try {
                System.out.println(joueur.getID_Joueur() +" -ID- "+joueur.getID_Utilisateur());
                joueurService.supprimer(joueur.getID_Joueur());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        joueurTV.getItems().removeAll(selectedItems);
    }
    public void refreshTable(){
        try {
            joueurList = joueurService.getListJoueur();
            for (Joueur j : joueurList){
                Joueur joueur = new Joueur(j.getID_Utilisateur(),j.getLogin(),j.getPassword(),j.getType(),j.getFirstName(),j.getLastName(),j.getEquipe(),j.getNbr_Buts(),j.getNbr_Assists(),j.getPosition(),j.getCapitaine());
                JoueurList.add(joueur);
            }
            joueurTV.setItems(JoueurList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {loadData();}
    private void loadData() {
        refreshTable();
        login.setCellValueFactory(new PropertyValueFactory<>("Login"));
        nomJoueur.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        prenomJoueur.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        buts.setCellValueFactory(new PropertyValueFactory<>("Nbr_Buts"));
        assissts.setCellValueFactory(new PropertyValueFactory<>("Nbr_Assists"));
        equipe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getNom_Equipe()));
        editData();
    }

    @FXML
    void Retour(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("HomeAdmin", new HomeAdminController(), "HomeStyle");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editData (){
        changerField(buts);
        buts.setOnEditCommit(event ->{
            Joueur joueur = event.getTableView().getItems().get(event.getTablePosition().getRow());
            joueur.setNbr_Buts(event.getNewValue());
            System.out.println("EDIT DONE");
            try {
                joueurService.modifier(joueur);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        changerField(assissts);
        assissts.setOnEditCommit(event ->{
            Joueur joueur = event.getTableView().getItems().get(event.getTablePosition().getRow());
            joueur.setNbr_Assists(event.getNewValue());
            System.out.println("EDIT DONE");
            try {
                joueurService.modifier(joueur);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void changerField(TableColumn<Joueur, Integer> field) {
        field.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        }));
    }
}
