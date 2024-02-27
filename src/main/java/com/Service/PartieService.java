package com.Service;

import com.Entity.*;
import com.Utils.Datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(partie.getDateMatch());
            request ="INSERT INTO `partie`(`Date_Partie`, `Score_Partie`, `Equipe1_ID`,`Equipe2_ID` ,`Tournoi_ID`,`ID_Stade`) " + "VALUES ('"+cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH)+"',"+partie.getScore()+","+partie.getEquipe1().getID_Equipe()+","+partie.getEquipe2().getID_Equipe()+","+partie.getTournoi().getID_Tournoi()+","+partie.getStade().getID_Stade()+")";
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
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(partie.getDateMatch());
            request = "UPDATE `partie` SET `Date_Partie`='"+cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH)+"',`Score_Partie`="+partie.getScore()+" WHERE `ID_Partie`="+partie.getId();
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

    public List<Partie> getListParties() throws SQLException {
        List<Partie> partiesList = new ArrayList<>();
        Partie partie;
        try{
            request = "SELECT * FROM partie";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                partie= new Partie(resultSet.getInt(1), resultSet.getDate(2), resultSet.getInt(3), new Equipe(resultSet.getInt(4)), new Equipe(resultSet.getInt(5)), new Tournoi(resultSet.getInt(6)), new Stade(resultSet.getInt(7)));
                partiesList.add(partie);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return partiesList;
    }

}
