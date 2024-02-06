package com.Service;



import com.Entity.*;
import com.Utils.Datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TournoiService implements IService<Tournoi>{
    Connection con;
    Statement statement;
    String request;

    @Override
    public int ajout(Tournoi tournoi) throws SQLException {
        int generatedID = 0;
        try {
            request = "INSERT INTO `tournoi`(`ID_Tournoi`, `Nom_Tournoi`, `Date_Debut`, `Date_Fin`, `Nbr_Equipe`, `ID_Createur`) " +
                    "VALUES ('" + tournoi.getID_Tournoi() + "','" + tournoi.getNom_Tournoi() + "','" + tournoi.getDate_Debut() + "','" +
                    tournoi.getDate_Fin() + "','" + tournoi.getNbr_Equipe() + "','" + tournoi.getCreateur() + "')";
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
                    "',`Date_Fin`='" + tournoi.getDate_Fin() + "',`Nbr_Equipe`='" + tournoi.getNbr_Equipe() + "',`ID_Createur`='" +
                    tournoi.getCreateur() + "' WHERE `ID_Tournoi`='" + tournoi.getID_Tournoi() + "'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public Tournoi recuperer(int idTournoi) throws SQLException {
        // Implémenter la logique pour récupérer un tournoi
        return null;
    }
}
