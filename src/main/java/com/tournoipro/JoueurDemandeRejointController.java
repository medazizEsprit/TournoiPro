package com.tournoipro;

import com.Entity.Equipe;
import com.Service.DemandeJoueurEquipeService;
import com.Service.EquipeService;
import com.Utils.Session;
import com.Utils.SwitchScenes;
import com.Utils.UserMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JoueurDemandeRejointController implements Initializable {
    @FXML
    private TableView<Equipe> equipeTV;
    @FXML
    private TableColumn<Equipe, Integer> nbrJoueur;
    @FXML
    private TableColumn<Equipe, String> nomEquipe;

    private EquipeService equipeService = new EquipeService();
    private List<Equipe> equipeListe;
    private ObservableList<Equipe> EquipeList = FXCollections.observableArrayList();
    @FXML
    private TextField filter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        FilteredList<Equipe> filteredData = new FilteredList<>(EquipeList, b -> true);
        filter.textProperty().addListener((observable, oldVallue, newValue) -> {
            filteredData.setPredicate(equipe -> {
                if (newValue == null || newValue.isEmpty())
                    return true;
                String lowerCaseFilter = newValue.toLowerCase();
                if (equipe.getNom_Equipe().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else return false;
            });
        });
        SortedList<Equipe> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(equipeTV.comparatorProperty());
        equipeTV.setItems(sortedList);
    }

    @FXML
    void Cancel(ActionEvent event) {
        try {
            SwitchScenes.getInstance().Switch("HomeJoueur", new HomeJoueurController(), "HomeStyle");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void refreshTable() {
        try {
            equipeListe = equipeService.getListEquipeDisponibles();
            for (Equipe eq : equipeListe) {
                Equipe equipe = new Equipe(eq.getID_Equipe(), eq.getNom_Equipe(), eq.getNbr_Joueur());
                EquipeList.add(equipe);
            }
            equipeTV.setItems(EquipeList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadData() {
        refreshTable();
        nbrJoueur.setCellValueFactory(new PropertyValueFactory<>("Nbr_Joueur"));
        nomEquipe.setCellValueFactory(new PropertyValueFactory<>("Nom_Equipe"));
    }

    @FXML
    public void onClick(Event event) {
        String pos;

        TableView.TableViewSelectionModel<Equipe> selectedModel = equipeTV.getSelectionModel();
        if (selectedModel.isEmpty()) {
            UserMessages.getInstance().Error("Erreur", "Pas de selection", "Vous devez selectionner une équipe");
        } else {
            pos = getPosition();
            if (pos != null) {
                if (UserMessages.getInstance().Confirmation("Confirmer demande", "Confirmation de la demande de rejoint", "Veuiilez confirmer votre demande"))
                {
                    DemandeJoueurEquipeService demandeJoueurEquipeService = new DemandeJoueurEquipeService();
                    ObservableList<Equipe> selectedItems = selectedModel.getSelectedItems();
                    for (Equipe equipe : selectedItems)
                    {
                        if (!demandeJoueurEquipeService.postulerEquipe(equipe.getID_Equipe(), Session.getInstance().getJoueurConnected(), pos))
                            UserMessages.getInstance().Error("Erreur", "Demande existante", "Vous avez déjà envoyé une demande à cette équipe");
                    }
                }
            } else UserMessages.getInstance().Error("Erreur", "Erreur choix de position", "Choix invalide de position");

        }
    }

    public String getPosition() {
        String position = null;
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Gardien", "Defenseur", "Milieu Terrain",
                "Attaquant");
        choiceDialog.setTitle("Choix du poste");
        choiceDialog.setHeaderText("Quel est votre poste?");
        choiceDialog.setContentText("Veuillez choisir la poste que vous désirez");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent())
            position = result.get();
        return position;
    }
}
