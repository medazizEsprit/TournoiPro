package com.tournoipro;

import com.Entity.Stade;
import com.Service.StadeService;
import com.Entity.*;
import com.Utils.SwitchScenes;
import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private Button Modifier;
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

    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        Modifier.setOnAction((event)->
                {
                    Stade stade = TableStade.getSelectionModel().getSelectedItem();

                    try {
                        SwitchScenes.getInstance().SwitchToUpdateStade("ModifierStade",(Stage) (((Node) event.getSource()).getScene().getWindow()), stade.getID_Stade());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                );
    }


}


