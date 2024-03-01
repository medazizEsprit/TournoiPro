package com.tournoipro;

import com.Entity.Stade;
import com.Service.StadeService;
import com.Entity.*;
import com.Utils.SwitchScenes;
import javafx.beans.property.Property;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Liststade1Controller implements Initializable {
    @FXML
    private Button Ajouter;
    @FXML
    private TableView<Stade> TableStade;
    @FXML
    private Button Supprimer;
    List<Stade> liststade;
    StadeService stadeService = new StadeService();
    @FXML
    private TableColumn<Stade,String> nom;
    @FXML
    private TableColumn <Stade,Integer> numspec;
    @FXML
    private TableColumn<Stade,String> lieu;
    @FXML
    private TableColumn <Stade, Integer> Id;
    @FXML
    private Button Retour;

    public void initialize(URL url, ResourceBundle resourceBundle) {
          StadeService stadeService = new StadeService();
        try {
            liststade = stadeService.getListStade();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Définir la cellule de la colonne du nom du stade
        Id.setCellValueFactory(new PropertyValueFactory<Stade, Integer>("ID_Stade"));
        nom.setCellValueFactory(new PropertyValueFactory<Stade, String>("NomStade"));
        numspec.setCellValueFactory(new PropertyValueFactory<Stade, Integer>("NumSpectateurs"));
        lieu.setCellValueFactory(new PropertyValueFactory<Stade, String>("Lieu"));
        ObservableList<Stade> stadeObservableList = FXCollections.observableArrayList(
                liststade


        );
        TableStade.setItems(stadeObservableList);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        nom.setOnEditCommit(event ->{
            Stade stade = event.getTableView().getItems().get(event.getTablePosition().getRow());
            stade.setNomStade(event.getNewValue());
            System.out.println("EDIT DONE");
            try {
                stadeService.modifier(stade);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        lieu.setCellFactory(TextFieldTableCell.forTableColumn());
        lieu.setOnEditCommit(event ->{
            Stade stade = event.getTableView().getItems().get(event.getTablePosition().getRow());
            stade.setLieu(event.getNewValue());
            System.out.println("EDIT DONE");
            try {
                stadeService.modifier(stade);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        numspec.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }


            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        }));
        numspec.setOnEditCommit(event ->{
            Stade stade = event.getTableView().getItems().get(event.getTablePosition().getRow());
            stade.setNumSpectateurs(event.getNewValue());
            System.out.println("EDIT DONE");
            try {
                stadeService.modifier(stade);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        Supprimer.setOnAction((event) -> {



                Stade stade = TableStade.getSelectionModel().getSelectedItem();
            try {
                stadeService.supprimer(stade.getID_Stade());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                    liststade = stadeService.getListStade();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                // Définir la cellule de la colonne du nom du stade
                Id.setCellValueFactory(new PropertyValueFactory<Stade, Integer>("ID_Stade"));
                nom.setCellValueFactory(new PropertyValueFactory<Stade, String>("NomStade"));
                numspec.setCellValueFactory(new PropertyValueFactory<Stade, Integer>("NumSpectateurs"));
                lieu.setCellValueFactory(new PropertyValueFactory<Stade, String>("Lieu"));
                ObservableList<Stade> stadeObservableList1 = FXCollections.observableArrayList(liststade);
                TableStade.setItems(stadeObservableList1);



            } );

    }

    @FXML
    void Cancel(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("HomeAdmin", new HomeAdminController(), "HomeStyle");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GoAjout(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("AjouterStade");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



