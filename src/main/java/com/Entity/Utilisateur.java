package com.Entity;

public class Utilisateur {
    private int ID_Utilisateur;
    private String Login;
    private String Password;
    private String Type;
    private String FirstName;
    private String LastName;

    public Utilisateur() {

    }

    public int getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Utilisateur(int ID_Utilisateur, String login, String password, String type, String firstName, String lastName) {
        this.ID_Utilisateur = ID_Utilisateur;
        Login = login;
        Password = password;
        Type = type;
        FirstName = firstName;
        LastName = lastName;
    }
    public Utilisateur( String login, String password, String type, String firstName, String lastName) {
        ID_Utilisateur = 0;
        Login = login;
        Password = password;
        Type = type;
        FirstName = firstName;
        LastName = lastName;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "ID_Utilisateur=" + ID_Utilisateur +
                ", Login='" + Login + '\'' +
                ", Password='" + Password + '\'' +
                ", Type='" + Type + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                '}';
    }
}
