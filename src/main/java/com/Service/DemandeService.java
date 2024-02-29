package com.Service;

import com.Entity.Demande;
import com.Entity.Equipe;
import com.Entity.Joueur;
import com.Utils.Datasource;
import javafx.concurrent.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DemandeService implements IService<Demande>{
    String request;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @Override
    public int ajout(Demande demande) throws SQLException {
        int generatedID=0;
        try
        {
            request ="INSERT INTO  demande ( ID_Tournoi ,  ID_Equipe  ) " + "VALUES ('"+demande.getTournoi().getID_Tournoi()+"','"+demande.getEquipe().getID_Equipe()+"')";
            preparedStatement =  Datasource.getInstance().getCon().prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            generatedID = preparedStatement.getGeneratedKeys().getInt(1) ;
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                generatedID = resultSet.getInt(1);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return generatedID;    }

    @Override
    public void supprimer(int t) throws SQLException {

    }


    public void supprimer(int idTournoi,int idequipe) throws SQLException {
        try {
            request = "DELETE FROM `demande` WHERE `ID_Tournoi` ='" + idTournoi + "' AND  `ID_Equipe` ='" + idequipe + "'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void modifier(Demande demande) throws SQLException {

    }

    @Override
    public Demande recuperer(int t) throws SQLException {
        return null;
    }
    public List<Demande> ListDemande() {
        List<Demande> DemandeList = new ArrayList<>();
        Demande Demande = new Demande();
        EquipeService equipeService= new EquipeService();
        TournoiService tournoiService= new TournoiService();
        int x=0;
        int y=0;

        try{
            request = "SELECT * FROM `demande`";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){

                Demande.setTournoi(tournoiService.recuperer(resultSet.getInt(1)));
                x=resultSet.getInt(1);
                Demande.setEquipe(equipeService.recuperer(resultSet.getInt(2)));
               y=resultSet.getInt(2);
                DemandeList.add(Demande);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return DemandeList;
    }

    public boolean IsCaptine(int Id) throws SQLException {
        int i=0;
        try{
            request = "SELECT * FROM `joueur` WHERE `ID_Joueur`='"+Id+"' AND `Capitaine`='1';";
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
    public boolean exsit(int id_tournoi, int id_equipe) {
        int i=0;
        try{
            request = "SELECT * FROM `demande` WHERE `ID_Tournoi`='"+id_tournoi+"' AND `ID_Equipe`='"+id_equipe+"';";
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
