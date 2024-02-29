package com.Service;


import com.Entity.*;
import com.Utils.Datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParticipationService{
    Connection con;
    Statement statement;
    String request;
    ResultSet resultSet;

    public int ajout(Participation participation) throws SQLException {
        try
        {
            request ="INSERT INTO `participation`(`ID_Tournoi`, `ID_Equipe`, `Annule`) " + "VALUES ('"+participation.getTournoi().getID_Tournoi()+"','"+participation.getEquipe().getID_Equipe()+"',0)";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
            return 0;
        };
        return 1;
    }

    public void supprimer(Participation participation) throws SQLException {
        try
        {
            request ="DELETE FROM `participation` WHERE `ID_Tournoi` ='"+participation.getTournoi().getID_Tournoi()+"' and `ID_Equipe` ='"+participation.getEquipe().getID_Equipe()+"'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }

    public List<Equipe> RecupererEquipes(int idTournoi) throws SQLException {
        List<Equipe> equipes = new ArrayList<>();
        try{
            request = "SELECT * FROM `participation` WHERE `ID_Tournoi`='"+idTournoi+"'";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                Equipe equipe = new EquipeService().recuperer(resultSet.getInt(2));
                equipes.add(equipe);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return equipes;
    }

    public List<Tournoi> RecupererTournois(int idEquipe) throws SQLException {
        List<Tournoi> tournois = new ArrayList<>();
        try{
            request = "SELECT * FROM `participation` WHERE `ID_Equipe`='"+idEquipe+"'";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                Tournoi tournoi = new TournoiService().recuperer(resultSet.getInt(1));
                tournois.add(tournoi);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return tournois;
    }

    public void Annuler(Participation participation) throws SQLException {
        try
        {
            request ="UPDATE `participation` SET `Annule`=1 WHERE `ID_Tournoi` ='"+participation.getTournoi().getID_Tournoi()+"' and `ID_Equipe` ='"+participation.getEquipe().getID_Equipe()+"'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }

    public boolean exsit(int id_tournoi, int id_equipe) {
        int i=0;
        try{
            request = "SELECT * FROM `participation` WHERE `ID_Tournoi`='"+id_tournoi+"' AND `ID_Equipe`='"+id_equipe+"';";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);

            while (resultSet.next()) {
                i++;
            }

        }
        catch (SQLException exception){
            System.out.println(exception);


        };
        return i==1;
    }
}
