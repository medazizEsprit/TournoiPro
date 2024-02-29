package com.Service;


import com.Entity.*;
import com.Utils.Datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquipeService implements IService<Equipe> {

    Connection con;
    Statement statement;
    String request;
    ResultSet resultSet;

    @Override
    public int ajout(Equipe equipe) throws SQLException {
        int generatedID = 0;
        try {
            String request = "INSERT INTO `equipe`(`Nom_Equipe`, `Nbr_Joueur`) " +
                    "VALUES ('" + equipe.getNom_Equipe() + "','" + equipe.getNbr_Joueur() + "')";
            Statement statement = Datasource.getInstance().getCon().createStatement();
            generatedID = statement.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return generatedID;
    }

    @Override
    public void supprimer(int idEquipe) throws SQLException {
        try {
            String request = "DELETE FROM `equipe` WHERE `ID_Equipe` ='" + idEquipe + "'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void modifier(Equipe equipe) throws SQLException {
        try {
            String request = "UPDATE `equipe` SET `ID_Equipe`='" + equipe.getID_Equipe() + "',`Nom_Equipe`='" + equipe.getNom_Equipe() + "',`Nbr_Joueur`='" + equipe.getNbr_Joueur() + "' WHERE `ID_Equipe`='" + equipe.getID_Equipe() + "'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public Equipe recuperer(int idEquipe) throws SQLException {
        Equipe equipe = new Equipe();

        try {
            String request = "SELECT * FROM `equipe` WHERE `ID_Equipe`='" + idEquipe +"'";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                equipe.setID_Equipe(resultSet.getInt(1));
                equipe.setNom_Equipe(resultSet.getString(2));
                equipe.setNbr_Joueur(resultSet.getInt(3));
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return equipe;
    }

    public boolean existName(String nom) throws  SQLException{
        boolean exist = false;
        try {
            String request = "SELECT * FROM `equipe` WHERE `Nom_Equipe`='" + nom +"'";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            if (resultSet.next()){
                exist = true;
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return exist;
    }

    public List<Equipe> getListEquipe() throws SQLException {
        List<Equipe> equipeList = new ArrayList<>();
        Equipe equipe;
        try{
            request = "SELECT * FROM equipe";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                equipe= new Equipe(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getInt(3));
                equipeList.add(equipe);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return equipeList;
    }

    public List<Equipe> getListEquipeDisponibles() throws SQLException {
        List<Equipe> equipeList = new ArrayList<>();
        Equipe equipe;
        try{
            request = "SELECT * FROM equipe WHERE Nbr_Joueur < 25";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                equipe= new Equipe(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getInt(3));
                equipeList.add(equipe);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return equipeList;
    }
}

