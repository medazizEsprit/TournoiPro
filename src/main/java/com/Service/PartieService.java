package com.Service;

import com.Entity.*;
import com.Utils.Datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<Partie> getListPartie() throws SQLException {
        List<Partie> partieList = new ArrayList<>();
        Partie partie;
        try{
            request = "SELECT * FROM partie";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                Equipe equipe1 = new Equipe(resultSet.getInt(4));
                Equipe equipe2 = new Equipe(resultSet.getInt(5));
                Tournoi tournoi = new Tournoi(resultSet.getInt(6));
                Stade stade = new Stade(resultSet.getInt(7));
                partie=new Partie(resultSet.getInt(1),resultSet.getDate(2),resultSet.getInt(3),equipe1, equipe2, tournoi, stade);
                partieList.add(partie);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return partieList;
    }



}
