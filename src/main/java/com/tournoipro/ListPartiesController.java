package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.ListView.PartieListView;
import com.Entity.Partie;
import com.Entity.Stade;
import com.Entity.Tournoi;
import com.Service.PartieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ListPartiesController implements Initializable {
    @FXML
    private TableView<PartieListView> PartiesTV;

    @FXML
    private TableColumn<PartieListView, Date> DatePartie;

    @FXML
    private TableColumn<PartieListView, Integer> Equipe1;

    @FXML
    private TableColumn<PartieListView, Integer> Equipe2;

    @FXML
    private TableColumn<PartieListView, Integer> ScorePartie;

    @FXML
    private TableColumn<PartieListView, Integer> Stade;

    @FXML
    private TableColumn<PartieListView, Integer> Tournoi;

    private PartieService PartieService = new PartieService();
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
                PartieListView partieListView = new PartieListView(pr.getId(), pr.getDateMatch(), pr.getScore(), pr.getEquipe1().getID_Equipe(), pr.getEquipe2().getID_Equipe(), pr.getTournoi().getID_Tournoi(), pr.getStade().getID_Stade());
                PartieList.add(partieListView);
            }
            PartiesTV.setItems(PartieList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editData (){
        DatePartie.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        DatePartie.setOnEditCommit(event ->{
            PartieListView partieLV = event.getTableView().getItems().get(event.getTablePosition().getRow());
            Partie partie = new Partie(partieLV.getId(), event.getNewValue(), partieLV.getScore(), new Equipe(partieLV.getEquipe1()), new Equipe(partieLV.getEquipe2()), new Tournoi(partieLV.getTournoi()), new Stade(partieLV.getStade()));
            System.out.println("EDIT DONE");
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
            Partie partie = new Partie(PartieLV.getId(), PartieLV.getDateMatch(), event.getNewValue(), new Equipe(PartieLV.getEquipe1()), new Equipe(PartieLV.getEquipe2()), new Tournoi(PartieLV.getTournoi()), new Stade(PartieLV.getStade()));
            System.out.println("EDIT DONE");
            try {
                PartieService.modifier(partie);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        Equipe1.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        }));
        Equipe1.setOnEditCommit(event ->{

        });

        Equipe2.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        }));
        Equipe2.setOnEditCommit(event ->{

        });

        Tournoi.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        }));
        Tournoi.setOnEditCommit(event ->{

        });

        Stade.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        }));
        Stade.setOnEditCommit(event ->{

        });
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
