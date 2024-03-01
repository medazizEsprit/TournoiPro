package com.Service;

import com.Entity.Equipe;
import com.Entity.Joueur;
import com.Utils.Datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DemandeJoueurEquipeService {
    Connection con;
    Statement statement;
    String request;
    ResultSet resultSet;

    public boolean postulerEquipe(int idEquipe , int idJoueur,String Position){
        boolean verif = true;
        try
        {
            request ="INSERT INTO `DMNJREQ`(`ID_Joueur`,`ID_Equipe`,`Position`) " +
                    "VALUES ('"+idJoueur+"','"+idEquipe+"','"+Position+"')";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            //1062 => Duplicated key error code MYSQL
            if(exception.getErrorCode() == 1062)
                verif =false;
            System.out.println(exception);
        };
        return verif;
    }


    public List<Equipe> getListEquipe (int idJoueur){

        List<Equipe> equipeList = new ArrayList<>();
        Equipe equipe;
        try{
            request = "SELECT * FROM equipe,dmnjreq WHERE dmnjreq.ID_Joueur ='"+idJoueur+"' AND dmnjreq.ID_Equipe = equipe.ID_Equipe";
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

    public List<Joueur> getListDemande (int idEquipe){

        List<Joueur> JoueurList = new ArrayList<>();
        Joueur joueur;

        try{
            request = "SELECT * FROM utilisateur,joueur,dmnjreq WHERE joueur.ID_Joueur = utilisateur.ID_Utilisateur AND joueur.ID_Joueur =  dmnjreq.ID_Joueur AND dmnjreq.ID_Equipe = '"+idEquipe+"' AND joueur.ID_Equipe IS NULL;";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                joueur = getJoueurWithouTeam();
                JoueurList.add(joueur);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return JoueurList;
    }
    private Joueur getJoueurWithouTeam() throws SQLException {
        Joueur joueur;
        Equipe equipe = new Equipe(0,"Sans Equipe",0);
        joueur=new Joueur(resultSet.getInt(1),resultSet.getString (2), resultSet.getString (3), resultSet.getString (4), resultSet.getString (5), resultSet.getString (6), equipe, resultSet.getInt (9), resultSet.getInt (10), resultSet.getString (15), resultSet.getInt(12));
        return joueur;
    }

    public void affecterJoueurEquipe(int idEquipe,int idJoueur, String position){
        try{
            request = "UPDATE joueur SET ID_Equipe ="+idEquipe+" , Position ='"+position+"' WHERE ID_Joueur ="+idJoueur+"";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };

        try{
            request = "DELETE FROM DMNJREQ WHERE ID_Equipe ='"+idEquipe+"' AND ID_Joueur ='"+idJoueur+"'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }

    public void refuserDemande(int idEquipe,int idJoueur){
        try{
            request = "DELETE FROM DMNJREQ WHERE ID_Equipe ='"+idEquipe+"' AND ID_Joueur ='"+idJoueur+"'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }

}
