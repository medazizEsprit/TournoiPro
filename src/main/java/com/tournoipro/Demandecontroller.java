package com.tournoipro;

import com.Entity.*;
import com.Service.DemandeService;
import com.Service.ParticipationService;
import com.Service.StadeService;
import com.Service.TournoiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class Demandecontroller implements Initializable {


    @javafx.fxml.FXML
    private Button Accepter;
    @javafx.fxml.FXML
    private Button Refuser;
    @javafx.fxml.FXML
    private TableView<Demande> ListeDemande;
    @javafx.fxml.FXML
    private TableColumn<Demande,String> Equipe;
    @javafx.fxml.FXML
    private TableColumn<Demande, String>  Tournoi;

    DemandeService demandeService= new DemandeService();
    TournoiService tournoiService= new TournoiService();
    ParticipationService participationService=new ParticipationService();
    List<Demande> listdemande;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            listdemande = demandeService.ListDemande();
        for (Demande elmet: listdemande) {
            elmet.setLibelleEquipe(elmet.getEquipe().getNom_Equipe());
            elmet.setLibelleTournoi(elmet.getTournoi().getNom_Tournoi());
        }



       Equipe.setCellValueFactory(new PropertyValueFactory<Demande,String>("LibelleEquipe"));
       Tournoi.setCellValueFactory(new PropertyValueFactory<Demande, String>("LibelleTournoi"));

        ObservableList<Demande> stadeObservableList = FXCollections.observableArrayList(
                listdemande


        );
        ListeDemande.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Accepter.setDisable(false);
                Refuser.setDisable(false
                );
            }
            else {
                Accepter.setDisable(true);
                Refuser.setDisable(true);

            }
        });
        ListeDemande.setItems(stadeObservableList);
        Accepter.setOnAction((actionEvent -> {
            Demande demande = ListeDemande.getSelectionModel().getSelectedItem();
            Participation participation = new Participation();
            participation.setEquipe(demande.getEquipe());
            participation.setTournoi(demande.getTournoi());
            try {
                participationService.ajout(participation);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                demandeService.supprimer(demande.getTournoi().getID_Tournoi(),demande.getEquipe().getID_Equipe());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            listdemande = demandeService.ListDemande();
            for (Demande elmet: listdemande) {
                elmet.setLibelleEquipe(elmet.getEquipe().getNom_Equipe());
                elmet.setLibelleTournoi(elmet.getTournoi().getNom_Tournoi());
            }



            Equipe.setCellValueFactory(new PropertyValueFactory<Demande,String>("LibelleEquipe"));
            Tournoi.setCellValueFactory(new PropertyValueFactory<Demande, String>("LibelleTournoi"));

            ObservableList<Demande> stadeObservableList1 = FXCollections.observableArrayList(
                    listdemande


            );
            ListeDemande.setItems(stadeObservableList1);


        }));
        Refuser.setOnAction(actionEvent -> {
            Demande demande = ListeDemande.getSelectionModel().getSelectedItem();
            try {
                demandeService.supprimer(demande.getTournoi().getID_Tournoi(),demande.getEquipe().getID_Equipe());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            listdemande = demandeService.ListDemande();
            for (Demande elmet: listdemande) {
                elmet.setLibelleEquipe(elmet.getEquipe().getNom_Equipe());
                elmet.setLibelleTournoi(elmet.getTournoi().getNom_Tournoi());
            }



            Equipe.setCellValueFactory(new PropertyValueFactory<Demande,String>("LibelleEquipe"));
            Tournoi.setCellValueFactory(new PropertyValueFactory<Demande, String>("LibelleTournoi"));

            ObservableList<Demande> stadeObservableList1 = FXCollections.observableArrayList(
                    listdemande


            );
            ListeDemande.setItems(stadeObservableList1);
            sendEmail("walaamiri02@gmail.com");
        });
        
    }
    public static void sendEmail(String destinataire) {
        // Propriétés du serveur de messagerie
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Session de messagerie avec authentification
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("medleithbd@gmail.com", "fcat synu hmyy lwpj");
            }
        });

        try {
            // Création d'un objet MimeMessage par défaut
            MimeMessage message = new MimeMessage(session);

            // Paramétrage des adresses du destinataire et de l'expéditeur
            message.setFrom(new InternetAddress("medleithbd@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinataire));

            // Paramétrage du sujet et du contenu du message
            message.setSubject("désolé");
            message.setText("Demande refusée ");

            // Envoi du message
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}