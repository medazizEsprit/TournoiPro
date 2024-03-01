package com.Service;


import com.Entity.*;
import com.Utils.Datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JoueurService implements IService<Joueur> {
    UtilisateurService utilisateurService = new UtilisateurService();
    Utilisateur utilisateur;
    PreparedStatement preparedStatement;
    String request;
    ResultSet resultSet;
    public Utilisateur joueurToUtilisateur(Joueur joueur){
        return new Utilisateur(joueur.getLogin(),joueur.getPassword(),joueur.getType(),joueur.getFirstName(),joueur.getLastName());
    }
    @Override
    public int ajout(Joueur joueur) throws SQLException {
        int generatedID=0;
        try
        {
            generatedID = utilisateurService.ajout(joueurToUtilisateur(joueur));
            System.out.println("ID : "+generatedID);
            request ="INSERT INTO `joueur`(`ID_Joueur`,`Nbr_Buts`, `Nbr_Assists`, `Position`, `Capitaine`) " +
                    "VALUES ('"+generatedID+"','"+joueur.getNbr_Buts()+"','"+joueur.getNbr_Assists()+"','"+joueur.getPosition()+"','"+joueur.getCapitaine()+"')";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return generatedID;
    }

    @Override
    public void supprimer(int idJoueur) throws SQLException {
        try
        {
            //request ="DELETE FROM `joueur` WHERE `ID_Joueur` ='"+idJoueur+"'";
            //Datasource.getInstance().getCon().createStatement().executeUpdate(request);
            //ON DELETE CASCADE
            utilisateurService.supprimer(idJoueur);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }
    public void supprimerWithName(String Nom) throws SQLException {
        try
        {
            //ON DELETE CASCADE
            utilisateurService.supprimer(Nom);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }

    @Override
    public void modifier(Joueur joueur) throws SQLException {
        try{
            //utilisateurService.modifier(joueurToUtilisateur(joueur));
            request = "UPDATE joueur,utilisateur SET ID_Equipe ='"+joueur.getEquipe().getID_Equipe()+"',Nbr_Buts ='"+joueur.getNbr_Buts()+"',Nbr_Assists ='"+joueur.getNbr_Assists()+"',Position ='"+joueur.getPosition()+"',Capitaine ='"+joueur.getCapitaine()+"'" +
                      ",Login ='"+joueur.getLogin()+"', Password ='"+joueur.getPassword()+"', Type ='"+joueur.getType()+"', FirstName ='"+joueur.getFirstName()+"',LastName ='"+joueur.getLastName()+"' WHERE ID_Joueur ='"+joueur.getID_Joueur()+"' AND ID_Utilisateur = '"+joueur.getID_Joueur()+"'";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }

    @Override
    public Joueur recuperer(int idJoueur) throws SQLException {
        Joueur joueur = null;
        try{
            request = "SELECT * FROM utilisateur,joueur,equipe WHERE ID_Joueur = ID_Utilisateur AND ID_Joueur ='"+idJoueur+"' AND equipe.ID_Equipe = joueur.ID_Equipe;";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                 joueur = getJoueur();
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return joueur;
    }

    public Joueur recuperer(String login) throws SQLException {
        Joueur joueur = null;
        try{
            request = "SELECT * FROM utilisateur,joueur,equipe WHERE ID_Joueur = ID_Utilisateur AND Login ='"+login+"' AND (equipe.ID_Equipe = joueur.ID_Equipe OR joueur.ID_Equipe;";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                joueur = getJoueur();

            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return joueur;
    }
    public List<Joueur> getListJoueur() throws SQLException {
        List<Joueur> JoueurList = new ArrayList<>();
        Joueur joueur;

        try{
            request = "SELECT * FROM utilisateur,joueur,equipe WHERE ID_Joueur = ID_Utilisateur AND equipe.ID_Equipe = joueur.ID_Equipe ;";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                joueur = getJoueur();
                JoueurList.add(joueur);
                }
            request = "SELECT * FROM utilisateur,joueur WHERE ID_Joueur = ID_Utilisateur AND joueur.ID_Equipe IS NULL ;";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                joueur = getJoueurWithoutTeam();
                JoueurList.add(joueur);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return JoueurList;
    }

    private Joueur getJoueur() throws SQLException {
        Joueur joueur;
        Equipe equipe = new Equipe(resultSet.getInt(13),resultSet.getString(14),resultSet.getInt(15));
        joueur=new Joueur(resultSet.getInt(1),resultSet.getString (2), resultSet.getString (3), resultSet.getString (4), resultSet.getString (5), resultSet.getString (6), equipe, resultSet.getInt (9), resultSet.getInt (10), resultSet.getString (11), resultSet.getInt(12));
        return joueur;
    }
    private Joueur getJoueurWithoutTeam() throws SQLException {
        Joueur joueur;
        Equipe equipe = new Equipe(0,"Sans Equipe",0);
        joueur=new Joueur(resultSet.getInt(1),resultSet.getString (2), resultSet.getString (3), resultSet.getString (4), resultSet.getString (5), resultSet.getString (6), equipe, resultSet.getInt (9), resultSet.getInt (10), resultSet.getString (11), resultSet.getInt(12));
        return joueur;
    }
    public Boolean VerifLogin(String login) throws SQLException {
        boolean exist = false;
        try{
            request = "SELECT * FROM utilisateur WHERE Login ='"+login+"'";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            if (resultSet.next()){
                return true;
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return exist;
    }

    public Boolean HasTeam(int idJoueur) throws SQLException {
        boolean exist = false;
        try{
            request = "SELECT * FROM joueur WHERE ID_Joueur ="+idJoueur+" AND ID_Equipe IS NOT NULL";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            if (resultSet.next()){
                return true;
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return exist;
    }

    public Boolean IsCaptain(int idJoueur) throws SQLException {
        boolean exist = false;
        try {
            request = "SELECT * FROM joueur WHERE ID_Joueur =" + idJoueur + " AND Capitaine = 1";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        };
        return exist;

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
    public Joueur recuperer2(int idequipe) throws SQLException {
        Joueur joueur = null;
        try{
            request = "SELECT * FROM utilisateur,joueur WHERE ID_Joueur = ID_Utilisateur AND ID_Equipe ='"+idequipe+"' AND Capitaine= 1;";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                joueur = getJoueurWithoutTeam();

            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return joueur;
    }

}
