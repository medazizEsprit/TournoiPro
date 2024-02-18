package com.tournoipro;

import com.Entity.Stade;
import com.Service.StadeService;
import com.Utils.SwitchScenes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ListStadeController implements Initializable {

    @FXML
    private ListView<String> ListStade;
    @FXML
    private Label NomStade;
    @FXML
    private Label NumSpectateurs;
    @FXML
    private Label LieuStade;

    @FXML
    private Button btnsup;

    StadeService stadeService = new StadeService();
    ///liste stade
    List<Stade> liststade;
    //// liste Libelle
    List<String> listLibelle = new ArrayList<>();
    //// Current Stade
    String CurrentStade;
    Stade stade;
    @FXML
    ImageView Map1;
    @FXML
    Hyperlink link;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            liststade = stadeService.getListStade();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Stade elm : liststade) {

            listLibelle.add(elm.getNomStade());
        }
        NomStade.setVisible(false);
        LieuStade.setVisible(false);
        NumSpectateurs.setVisible(false);
        ListStade.getItems().addAll(listLibelle);


        link.setGraphic(Map1);
        link.setOnAction(event -> {
            // Replace with the action you want to perform, such as opening a web page
            System.out.println("Image link clicked!");
        });
        ListStade.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                CurrentStade = ListStade.getSelectionModel().getSelectedItem();
                for (Stade elm : liststade) {
                     if (elm.getNomStade().equals(CurrentStade)){
                         stade=elm;
                     }
                }
                NomStade.setVisible(true);
                LieuStade.setVisible(true);
                NumSpectateurs.setVisible(true);

                NomStade.setText(stade.getNomStade());
                LieuStade.setText(stade.getLieu());
                NumSpectateurs.setText( Integer.toString(stade.getNumSpectateurs()));
                btnsup.setOnAction(event -> {
                    try {
                        supprimerStadeSelectionne();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }

    private void supprimerStadeSelectionne() throws SQLException {
        // Récupérer l'index de l'élément sélectionné
        int selectedIndex = ListStade.getSelectionModel().getSelectedIndex();
        String selectedName = ListStade.getSelectionModel().getSelectedItem();

        // Vérifier si un élément est sélectionné
        if (selectedIndex >= 0) {
            // Supprimer l'élément de la liste
            ListStade.getItems().remove(selectedIndex);
            stadeService.supprimer(selectedName);
            System.out.println("Stade supprimé avec succès.");
        } else {
            System.out.println("Aucun stade sélectionné.");
        }
    }

    @FXML
    void GoModifier(ActionEvent event) throws IOException {
        SwitchScenes.getInstance().SwitchToUpdateStade("ModifierStade", (Stage) (((Node) event.getSource()).getScene().getWindow()), stade.getID_Stade());

    }

}
