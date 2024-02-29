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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;

public class ListEquipeController implements Initializable {
    @FXML
    private Text alertMsg;
    @FXML
    private TableView<Equipe> equipeTV;
    @FXML
    private TableColumn<Equipe, Integer> nbrJoueur;
    @FXML
    private TableColumn<Equipe, String> nomEquipe;

    private EquipeService equipeService = new EquipeService();
    private List<Equipe> equipeListe;
    private ObservableList<Equipe> EquipeList = FXCollections.observableArrayList();
    private SwitchScenes switchScenes = new SwitchScenes();

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
            String newNomEquipe = event.getNewValue();
            if (isValidNomEquipe(newNomEquipe)) {
                alertMsg.setText("");
                equipe.setNom_Equipe(event.getNewValue());
                System.out.println("EDIT DONE");
                try {
                    equipeService.modifier(equipe);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Nom d'équipe invalide");
                alertMsg.setText("Nom d'équipe invalide");

                // Keep the text field focused
                event.consume(); // Prevent the default behavior of committing the edit
                Platform.runLater(() -> event.getTableView().edit(event.getTablePosition().getRow(), event.getTableColumn()));
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
            int newNbrJoueur = event.getNewValue();
            if (isValidNbrJoueur(newNbrJoueur)){
                alertMsg.setText("");
                equipe.setNbr_Joueur(event.getNewValue());
                System.out.println("EDIT DONE");
                try {
                    equipeService.modifier(equipe);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else{
                System.out.println("Nombre de joueurs invalide");
                alertMsg.setText("Nombre de joueurs invalide");

                // Keep the text field focused
                event.consume(); // Prevent the default behavior of committing the edit
                Platform.runLater(() -> event.getTableView().edit(event.getTablePosition().getRow(), event.getTableColumn()));
            }

        });
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

    @FXML
    public void insertData() throws IOException {
        switchScenes.Switch("ajoutEquipe", new Stage());
    }

    private void loadData() {
        refreshTable();
        nbrJoueur.setCellValueFactory(new PropertyValueFactory<>("Nbr_Joueur"));
        nomEquipe.setCellValueFactory(new PropertyValueFactory<>("Nom_Equipe"));

        editData();
    }

    private boolean isValidNomEquipe(String nomEquipe) {
        return nomEquipe != null && !nomEquipe.isEmpty() && nomEquipe.matches("[a-zA-Z ]+");
    }

    private boolean isValidNbrJoueur(int nbrJoueur) {
        return nbrJoueur != 0;
    }
}
