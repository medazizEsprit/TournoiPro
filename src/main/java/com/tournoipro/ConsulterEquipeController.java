package com.tournoipro;

import com.Entity.Equipe;
import com.Entity.Joueur;
import com.Entity.Stade;
import com.Service.JoueurService;
import com.Service.StadeService;
import com.Utils.SwitchScenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterEquipeController implements Initializable {

    @FXML
    private TableView<Joueur> lstJoeursTV;
    @FXML
    private TableColumn<Joueur, Integer> nbrButs;
    @FXML
    private Text nomEquipe;
    @FXML
    private TableColumn<Joueur, String> position;
    @FXML
    private TableColumn<Joueur, String> nomJoueurs;
    @FXML
    private TableColumn<Joueur, String> prenomJoueur;
    @FXML
    private Text msgAlert;

    private ObservableList<Joueur> JoueursList = FXCollections.observableArrayList();
    private int id;
    private int idEquipe;
    private JoueurService joueurService= new JoueurService();
    private SwitchScenes switchScenes = new SwitchScenes();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    public void setId(int id) {
        this.id = id;
        try {
            Joueur joueur = joueurService.recuperer(id);
            this.idEquipe = joueur.getEquipe().getID_Equipe();
            List<Joueur> lstJouers = joueurService.getListJoueur();

            nomEquipe.setText(joueur.getEquipe().getNom_Equipe());
            for (Joueur player : lstJouers){
                if (player.getEquipe().getID_Equipe() == joueur.getEquipe().getID_Equipe()){
                    JoueursList.add(player);
                }
            }
            lstJoeursTV.setItems(JoueursList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadData() {
        // refreshTable();
        nomJoueurs.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        prenomJoueur.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        position.setCellValueFactory(new PropertyValueFactory<>("Position"));
        nbrButs.setCellValueFactory(new PropertyValueFactory<>("Nbr_Buts"));
    }

    @FXML
    public void consultTournois() throws IOException {
        for (Joueur joueur : JoueursList){
            if (joueur.getCapitaine() == 1){
                switchScenes.Switch("consultTournoi", new Stage());
            }
            else {
                msgAlert.setText("Accès interdit : seulement les capitaines ");
            }
        }
    }

    @FXML
    public void consultDemande() throws IOException {
        for (Joueur joueur : JoueursList){
            if (joueur.getCapitaine() == 1){
                switchScenes.Switch("Demande", new Stage());
            }
            else {
                msgAlert.setText("Accès interdit : seulement les capitaines ");
            }
        }
    }

    @FXML
    public void checkStat(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stat.fxml"));
            Parent root = loader.load();
            StatController statController = loader.getController();
            statController.setIdEquipe(this.idEquipe);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
