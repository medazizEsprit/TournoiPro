package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.ListView.PartieListView;
import com.Entity.Partie;
import com.Entity.Stade;
import com.Entity.Tournoi;
import com.Service.EquipeService;
import com.Service.PartieService;
import com.Service.StadeService;
import com.Service.TournoiService;
import com.Utils.SwitchScenes;
import com.Utils.UserMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ListPartiesController implements Initializable {
    @FXML
    private TableView<PartieListView> PartiesTV;

    @FXML
    private TableColumn<PartieListView, String> DatePartie;

    @FXML
    private TableColumn<PartieListView, String> Equipe1;

    @FXML
    private TableColumn<PartieListView, String> Equipe2;

    @FXML
    private TableColumn<PartieListView, Integer> ScorePartie;

    @FXML
    private TableColumn<PartieListView, String> Stade;

    @FXML
    private TableColumn<PartieListView, String> Tournoi;

    private PartieService PartieService = new PartieService();
    private EquipeService EquipeService = new EquipeService();
    private TournoiService TournoiService = new TournoiService();
    private StadeService StadeService = new StadeService();
    private List<Partie> partiesListe;
    private ObservableList<PartieListView> PartieList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    @FXML
    public void refreshTable(){
        try {
            partiesListe = PartieService.getListParties();
            for (Partie pr : partiesListe){
                PartieListView partieListView = new PartieListView(pr.getId(), pr.getDateMatch().toString(), pr.getScore());
                partieListView.setEquipe1(EquipeService.recuperer(pr.getEquipe1().getID_Equipe()).getNom_Equipe());
                partieListView.setEquipe2(EquipeService.recuperer(pr.getEquipe2().getID_Equipe()).getNom_Equipe());
                partieListView.setTournoi(TournoiService.recuperer(pr.getTournoi().getID_Tournoi()).getNom_Tournoi());
                partieListView.setStade(StadeService.recuperer(pr.getStade().getID_Stade()).getNomStade());
                PartieList.add(partieListView);
            }
            PartiesTV.setItems(PartieList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editData (){
        DatePartie.setCellFactory(TextFieldTableCell.forTableColumn());
        DatePartie.setOnEditCommit(event ->{
            PartieListView partieLV = event.getTableView().getItems().get(event.getTablePosition().getRow());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);
            Partie partie = null;
            try {
                partie = new Partie(partieLV.getId(), formatter.parse(event.getNewValue()), partieLV.getScore());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                PartieService.modifier(partie);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        ScorePartie.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        }));
        ScorePartie.setOnEditCommit(event ->{
            PartieListView PartieLV = event.getTableView().getItems().get(event.getTablePosition().getRow());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);
            Partie partie = null;
            try {
                partie = new Partie(PartieLV.getId(), formatter.parse(PartieLV.getDateMatch()), event.getNewValue());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            System.out.println("EDIT DONE");
            try {
                PartieService.modifier(partie);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        Equipe1.setCellFactory(TextFieldTableCell.forTableColumn());
        Equipe1.setOnEditCommit(event ->{
            UserMessages.getInstance().Error("Erreur", "Impossible de modifier l'équipe 1", "Vous ne pouvez pas modifier l'équipe 1 d'une partie");
        });

        Equipe2.setCellFactory(TextFieldTableCell.forTableColumn());
        Equipe2.setOnEditCommit(event ->{
            UserMessages.getInstance().Error("Erreur", "Impossible de modifier l'équipe 2", "Vous ne pouvez pas modifier l'équipe 2 d'une partie");
        });

        Tournoi.setCellFactory(TextFieldTableCell.forTableColumn());
        Tournoi.setOnEditCommit(event ->{
            UserMessages.getInstance().Error("Erreur", "Impossible de modifier le tournoi", "Vous ne pouvez pas modifier le tournoi d'une partie");
        });

        Stade.setCellFactory(TextFieldTableCell.forTableColumn());
        Stade.setOnEditCommit(event ->{
            UserMessages.getInstance().Error("Erreur", "Impossible de modifier le stade", "Vous ne pouvez pas modifier le stade d'une partie");
        });
    }

    @FXML
    public void deleteData(ActionEvent event){

        TableView.TableViewSelectionModel<PartieListView> selectedModel = PartiesTV.getSelectionModel();
        if (selectedModel.isEmpty()) {
            System.out.println("You should select a row to delete !");
            return;
        }

        ObservableList<PartieListView> selectedItems = selectedModel.getSelectedItems();


        for (PartieListView partie : selectedItems) {
            try {
                PartieService.supprimer(partie.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        PartiesTV.getItems().removeAll(selectedItems);
    }

    @FXML
    void Ajouter(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("AjoutPartie");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Cancel(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("HomeAdmin",new HomeAdminController(), "HomeStyle");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadData() {
        refreshTable();
        ScorePartie.setCellValueFactory(new PropertyValueFactory<>("score"));
        DatePartie.setCellValueFactory(new PropertyValueFactory<>("dateMatch"));
        Equipe1.setCellValueFactory(new PropertyValueFactory<>("equipe1"));
        Equipe2.setCellValueFactory(new PropertyValueFactory<>("equipe2"));
        Tournoi.setCellValueFactory(new PropertyValueFactory<>("tournoi"));
        Stade.setCellValueFactory(new PropertyValueFactory<>("stade"));

        editData();
    }
}
