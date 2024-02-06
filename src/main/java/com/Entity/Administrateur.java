package com.Entity;

public class Administrateur extends Utilisateur{
    private int ID_Admin;

    public Administrateur(int ID_Utilisateur, String login, String password, String type, String firstName, String lastName) {
        super(ID_Utilisateur, login, password, type, firstName, lastName);
        ID_Admin = ID_Utilisateur;
    }
    public Administrateur( String login, String password, String firstName, String lastName) {
        super(login,password, "ADM", firstName, lastName);

    }
    public int getID_Admin() {
        return ID_Admin;
    }

    public void setID_Admin(int ID_Admin) {
        this.ID_Admin = ID_Admin;
    }
}
