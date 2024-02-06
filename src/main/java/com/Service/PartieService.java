package com.Service;

import com.Entity.*;
import com.Utils.Datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PartieService implements IService<Partie>{
    Connection con;
    Statement statement;
    String request;
    ResultSet resultSet;

    @Override
    public int ajout(Partie partie) throws SQLException {
        int generatedID=0;
        try
        {
            request ="INSERT INTO `partie`(`ID_Partie`, `Date_Partie`, `Score_Partie`, `Equipe1_ID`,`Equipe2_ID` ,`Tournoi_ID`,`ID_Stade`) " + "VALUES ('"+partie.getId()+"','"+partie.getDateMatch()+"','"+partie.getScore()+"','"+partie.getEquipe1()+"','"+partie.getEquipe2()+"','"+partie.getTournoi()+"','"+partie.getStade()+"')";
            generatedID = Datasource.getInstance().getCon().createStatement().executeUpdate(request,Statement.RETURN_GENERATED_KEYS);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return generatedID;
    }

    @Override
    public void supprimer(int idPartie) throws SQLException {
        try
        {
            request ="DELETE FROM `partie` WHERE `ID_Partie` ='"+idPartie+"'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }

    @Override
    public void modifier(Partie partie) throws SQLException {
        try{
            request = "UPDATE `partie` SET `ID_Partie`='"+partie.getId()+"',`Date_Partie`='"+partie.getDateMatch()+"',`Score_Partie`='"+partie.getScore()+"',`Equipe1_ID`='"+partie.getEquipe1()+"',`Equipe2_ID`='"+partie.getEquipe2()+"' WHERE `ID_Utilisateur`='"+partie.getId()+"'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };

    }

    @Override
    public Partie recuperer(int idPartie) throws SQLException {
        return null;
    }



}
