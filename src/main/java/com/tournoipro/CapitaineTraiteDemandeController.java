package com.tournoipro;
import com.Entity.Joueur;
import com.Service.DemandeJoueurEquipeService;
import com.Service.EquipeService;
import com.Service.JoueurService;
import com.Utils.Session;
import com.Utils.SwitchScenes;
import com.Utils.UserMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CapitaineTraiteDemandeController implements Initializable {
    @FXML
    private TableColumn nomJoueur;
    @FXML
    private TableView joueurTV;
    @FXML
    private TableColumn prenomJoueur;

    @FXML
    private Button refuser;
    @FXML
    private TableColumn position;
    @FXML
    private Button accepter;

    private JoueurService joueurService = new JoueurService();
    private EquipeService equipeService = new EquipeService();
    private DemandeJoueurEquipeService demandeJoueurEquipeService = new DemandeJoueurEquipeService();
    private List<Joueur> joueurList;
    private ObservableList<Joueur> JoueurList = FXCollections.observableArrayList();
    @FXML
    private TextField filter;
    FilteredList<Joueur> filteredData = new FilteredList<>(JoueurList, b -> true);
    SortedList<Joueur> sortedList = new SortedList<>(filteredData);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();

    }

    @FXML
    void Cancel(ActionEvent event) {
        try {
            SwitchScenes.getInstance().SwitchToCheckTeam("consultEquipe", (Stage) (((Node) event.getSource()).getScene().getWindow()), Session.getJoueurConnected());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadData(){
        refreshTable();
        nomJoueur.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        prenomJoueur.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        position.setCellValueFactory(new PropertyValueFactory<>("Position"));

        filter.textProperty().addListener((observable, oldVallue, newValue) -> {
            filteredData.setPredicate(joueur -> {
                if (newValue == null || newValue.isEmpty())
                    return true;
                String lowerCaseFilter = newValue.toLowerCase();
                if (joueur.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else if (joueur.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else if (joueur.getPosition().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else return false;
            });
        });

        sortedList.comparatorProperty().bind(joueurTV.comparatorProperty());
        joueurTV.setItems(sortedList);
    }
    public void refreshTable(){
        try {
            joueurList = demandeJoueurEquipeService.getListDemande(equipeService.getEquipeByJoueur(Session.getJoueurConnected()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Joueur j : joueurList){
            Joueur joueur = new Joueur(j.getID_Utilisateur(),j.getLogin(),j.getPassword(),j.getType(),j.getFirstName(),j.getLastName(),j.getEquipe(),j.getNbr_Buts(),j.getNbr_Assists(),j.getPosition(),j.getCapitaine());
            JoueurList.add(joueur);
        }
        joueurTV.setItems(JoueurList);
        System.out.println(joueurList);
    }

    @FXML
    public void doAccept(Event event) throws SQLException {
        TableView.TableViewSelectionModel<Joueur> selectedModel = joueurTV.getSelectionModel();
        if (selectedModel.isEmpty()) {
            UserMessages.getInstance().Error("Erreur", "Pas de selection", "Vous devez selectionner un joueur");
        } else {
            if (UserMessages.getInstance().Confirmation("Confirmer acceptation", "Confirmation de l'acceptation' de la demande", "Veuiilez confirmer votre acceptation de la demande"))
            {
                Joueur joueur = selectedModel.getSelectedItem();
                //demandeJoueurEquipeService.affecterJoueurEquipe(Session.getJoueurConnected().getEquipe().getID_Equipe(), joueur.getID_Joueur(),joueur.getPosition());
                int id = equipeService.getEquipeByJoueur(joueur.getID_Joueur());
                demandeJoueurEquipeService.affecterJoueurEquipe(equipeService.getEquipeByJoueur(Session.getInstance().getJoueurConnected()), joueur.getID_Joueur(),joueur.getPosition());
                JoueurList.remove(joueur);
                joueurTV.setItems(JoueurList);
            }
        }
    }


    @FXML
    public void doRefuse(Event event) throws SQLException {
        TableView.TableViewSelectionModel<Joueur> selectedModel = joueurTV.getSelectionModel();
        if (selectedModel.isEmpty()) {
            UserMessages.getInstance().Error("Erreur", "Pas de selection", "Vous devez selectionner un joueur");
        } else {
            if (UserMessages.getInstance().Confirmation("Confirmer refus", "Confirmation de le refus de la demande", "Veuiilez confirmer votre refus de la demande"))
            {
                    Joueur joueur = selectedModel.getSelectedItem();
                    //demandeJoueurEquipeService.refuserDemande(Session.getJoueurConnected().getEquipe().getID_Equipe(), joueur.getID_Joueur());
                int id = equipeService.getEquipeByJoueur(Session.getInstance().getJoueurConnected());
                demandeJoueurEquipeService.refuserDemande(equipeService.getEquipeByJoueur(Session.getInstance().getJoueurConnected()), joueur.getID_Joueur());
                    JoueurList.remove(joueur);
                    joueurTV.setItems(JoueurList);
            }
        }
    }
}
