package com.Service;


import com.Entity.*;
import com.Utils.Datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class AdministrateurService implements IService<Administrateur> {
    UtilisateurService utilisateurService = new UtilisateurService();
    Utilisateur utilisateur;
    PreparedStatement preparedStatement;
    String request;
    ResultSet resultSet;
    public Utilisateur adminToUtilisateur(Administrateur admin){
        return new Utilisateur(admin.getLogin(),admin.getPassword(),admin.getType(),admin.getFirstName(),admin.getLastName());
    }
    public int ajout(Administrateur admin) throws SQLException {
        int generatedID=0;
        try
        {
            generatedID = utilisateurService.ajout(adminToUtilisateur(admin));
            System.out.println("ID : "+generatedID);
            request ="INSERT INTO administrateur(ID_Admin) " + "VALUES ('"+generatedID+"')";
            Datasource.getInstance().getCon().createStatement().executeUpdate(request,Statement.RETURN_GENERATED_KEYS);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return generatedID;
    }
    public void supprimer(int idAdmin) throws SQLException {
        try
        {
            //request ="DELETE FROM administrateur WHERE ID_Admin ='"+idAdmin+"'";
            //Datasource.getInstance().getCon().createStatement().executeUpdate(request);
            //ON DELETE CASCADE
            utilisateurService.supprimer(idAdmin);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }
    public void modifier(Administrateur admin) throws SQLException {
        try{
            utilisateurService.modifier(adminToUtilisateur(admin));
          //request = "UPDATE administrateur SET ID_Admin='"+admin.getID_Admin()+"' WHERE ID_Admin='"+admin.getID_Admin()+"'";
          //Datasource.getInstance().getCon().createStatement().executeUpdate(request);
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
    }
    public Administrateur recuperer(int idAdmin) throws SQLException {
        Administrateur admin = null;
        try{
            //request = "SELECT * FROM utilisateur WHERE ID_Admin='"+idAdmin+"'";
            //resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            //while (resultSet.next()){
            //    admin=new Administrateur(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
            //}
            utilisateur = utilisateurService.recuperer(idAdmin);
            admin = new Administrateur(utilisateur.getID_Utilisateur(),utilisateur.getLogin(), utilisateur.getPassword(),
                        utilisateur.getType(), utilisateur.getFirstName(), utilisateur.getLastName());
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return admin;
    }
    public List<Administrateur> getListAdministrateur() throws SQLException {
        List<Administrateur> AdministrateurList = new ArrayList<>();
        Administrateur admin;

        try{
            request = "SELECT * FROM utilisateur,administrateur WHERE ID_Utilisateur = ID_Admin";
            resultSet = Datasource.getInstance().getCon().createStatement().executeQuery(request);
            while (resultSet.next()){
                admin=new Administrateur(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
                AdministrateurList.add(admin);
            }
        }
        catch (SQLException exception){
            System.out.println(exception);
        };
        return AdministrateurList;
    }
}