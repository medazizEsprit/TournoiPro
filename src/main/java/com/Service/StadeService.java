package com.Service;


import com.Entity.*;
import com.Utils.Datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StadeService implements IService<Stade>{
    String request;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @Override
    public int ajout(Stade stade) throws SQLException {
        int generatedID=0;
        try
        {
            request ="INSERT INTO  stade ( NomStade ,  NumSpectateurs ,  Lieu ) " + "VALUES ('"+stade.getNomStade()+"','"+stade.getNumSpectateurs()+"','"+stade.getLieu()+"')";
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
        return generatedID;
    }

    @Override
    public void supprimer(int idStade) throws SQLException {
        try
        {
            request ="DELETE FROM  stade  WHERE ID_Stade ='"+idStade+"'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }

    @Override
    public void modifier(Stade stade) throws SQLException {
        try{
            request = "UPDATE  stade SET NomStade = '"+stade.getNomStade()+"', NumSpectateurs = '"+stade.getNumSpectateurs()+"', Lieu = '"+stade.getLieu()+"' WHERE ID_Stade ='"+stade.getID_Stade()+"'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }

    @Override
    public Stade recuperer(int idStade) throws SQLException {
        Stade stade = null;
        try{
            request = "SELECT * FROM stade WHERE ID_Stade='"+idStade+"'";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                stade=new Stade(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4));
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return stade;
    }
    public List<Stade> getListStade() throws SQLException {
        List<Stade> stadeList = new ArrayList<>();
        Stade stade;
        try{
            request = "SELECT * FROM stade";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                stade=new Stade(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4));
                stadeList.add(stade);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return stadeList;
    }
}
