package com.Service;



import com.Entity.*;
import com.Utils.Datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiService implements IService<Tournoi>{
    Connection con;
    Statement statement;
    String request;
    ResultSet resultSet;

    @Override
    public int ajout(Tournoi tournoi) throws SQLException {
        int generatedID = 0;
        try {
            request = "INSERT INTO `tournoi`( `Nom_Tournoi`, `Date_Debut`, `Date_Fin`, `Nbr_Equipe`, `ID_Createur`) " +
                    "VALUES ('" + tournoi.getNom_Tournoi() + "','" + tournoi.getDate_Debut().getYear() + "-" + tournoi.getDate_Debut().getMonth() + "-" + tournoi.getDate_Debut().getDay() + "','" +
                    tournoi.getDate_Fin().getYear() + "-" + tournoi.getDate_Fin().getMonth() + "-" + tournoi.getDate_Fin().getDay() + "'," + tournoi.getNbr_Equipe() + "," + tournoi.getCreateur().getID_Utilisateur() + ")";
            generatedID = Datasource.getInstance().getCon().createStatement().executeUpdate(request, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return generatedID;
    }

    @Override
    public void supprimer(int idTournoi) throws SQLException {
        try {
            request = "DELETE FROM `tournoi` WHERE `ID_Tournoi` ='" + idTournoi + "'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void modifier(Tournoi tournoi) throws SQLException {
        try {
            request = "UPDATE `tournoi` SET `Nom_Tournoi`='" + tournoi.getNom_Tournoi() + "',`Date_Debut`='" + tournoi.getDate_Debut() +
                    "',`Date_Fin`='" + tournoi.getDate_Fin() + "',`Nbr_Equipe`='" + tournoi.getNbr_Equipe()  + "' WHERE `ID_Tournoi`='" + tournoi.getID_Tournoi() + "'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }


//    public List<Tournoi> getListTournoi() throws SQLException {
//        List<Tournoi> tournoiList = new ArrayList<>();
//        Tournoi tournoi;
//        try {
//            request = "SELECT * FROM tournoi";
//            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
//            while (resultSet.next()) {
//                // Extraire les données du ResultSet
//                int ID_Tournoi = resultSet.getInt("ID_Tournoi");
//                String nom_Tournoi = resultSet.getString("Nom_Tournoi");
//                Date date_Debut = resultSet.getDate("Date_Debut");
//                Date date_Fin = resultSet.getDate("Date_Fin");
//                int nbr_Equipe = resultSet.getInt("Nbr_Equipe");
//                int id_Createur = resultSet.getInt("ID_Createur");
//
//                // Créer un nouvel objet Tournoi avec les données extraites
//                tournoi = new Tournoi(ID_Tournoi, nom_Tournoi, date_Debut, date_Fin, nbr_Equipe, new Utilisateur(id_Createur));
//                tournoiList.add(tournoi);
//            }
//        } catch (SQLException exception) {
//            System.out.println(exception);
//        }
//        return tournoiList;
//    }


@Override
    public Tournoi recuperer(int idTournoi) throws SQLException {
        Tournoi tournoi = new Tournoi();

        UtilisateurService utilisateurService= new UtilisateurService();

        try {
            String request = "SELECT * FROM `tournoi` WHERE `ID_Tournoi`='" + idTournoi +"'";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){

                tournoi.setID_Tournoi(resultSet.getInt(1));
                tournoi.setNom_Tournoi(resultSet.getString(2));
                tournoi.setDate_Debut(resultSet.getDate(3));
                tournoi.setDate_Fin(resultSet.getDate(4));
                tournoi.setNbr_Equipe(resultSet.getInt(5));
                tournoi.setCreateur(utilisateurService.recuperer(resultSet.getInt(6)));
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return tournoi;
    }

    public List<Tournoi> getListTournoi() throws SQLException {
        List<Tournoi> tournoiList = new ArrayList<>();
        Tournoi tournoi;
        try{
            request = "SELECT * FROM tournoi";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                tournoi=new Tournoi(resultSet.getInt(1),resultSet.getString(2),resultSet.getDate(3),resultSet.getDate(4),resultSet.getInt(5));
                tournoiList.add(tournoi);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return tournoiList;
    }

    public List<Equipe> GetListEquipeByTournoi(int idTournoi) throws SQLException {
        List<Equipe> equipeList = new ArrayList<>();
        Equipe equipe;
        try{
            request = "SELECT e.* FROM equipe e,participation p WHERE p.ID_Equipe = e.ID_Equipe and p.ID_Tournoi = " + idTournoi;
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                equipe = new Equipe(resultSet.getInt("ID_Equipe"), resultSet.getString("Nom_Equipe"), resultSet.getInt("Nbr_Joueur"));
                equipeList.add(equipe);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return equipeList;
    }

    private Tournoi getTournoi() throws SQLException {
        Tournoi tournoi;
        Utilisateur user = new Utilisateur(resultSet.getInt(6));
        tournoi = new Tournoi(resultSet.getInt(1),resultSet.getString(2), resultSet.getDate(3), resultSet.getDate(4), resultSet.getInt(5), user);
        return tournoi;
    }
}
