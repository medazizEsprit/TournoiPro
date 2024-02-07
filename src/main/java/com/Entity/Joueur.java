package com.Entity;

public class Joueur extends Utilisateur {
    private int ID_Joueur;
    private com.Entity.Equipe Equipe;
    private int Nbr_Buts;
    private int Nbr_Assists;
    private String Position;
    private int Capitaine;

    @Override
    public String toString() {
        return "Joueur{" +
                "ID_Joueur=" + ID_Joueur +
                ", Login='" + this.getLogin() + '\'' +
                ", Password='" + this.getPassword() + '\'' +
                ", Type='" + this.getType() + '\'' +
                ", FirstName='" + this.getFirstName() + '\'' +
                ", LastName='" + this.getLastName() + '\'' +
                ", Equipe=" + Equipe +
                ", Nbr_Buts=" + Nbr_Buts +
                ", Nbr_Assists=" + Nbr_Assists +
                ", Position='" + Position + '\'' +
                ", Capitaine=" + Capitaine +
                '}';
    }

    public Joueur(int ID_Utilisateur, String login, String password, String type, String firstName, String lastName, Equipe equipe, int nbr_Buts, int nbr_Assists, String position, int capitaine) {
        super(ID_Utilisateur, login, password, type, firstName, lastName);
        ID_Joueur = ID_Utilisateur;
        Equipe = equipe;
        Nbr_Buts = nbr_Buts;
        Nbr_Assists = nbr_Assists;
        Position = position;
        Capitaine = capitaine;
    }
    public Joueur(String login, String password, String type, String firstName, String lastName, Equipe equipe, int nbr_Buts, int nbr_Assists, String position, int capitaine) {
        super(login, password, type, firstName, lastName);
        Equipe = equipe;
        Nbr_Buts = nbr_Buts;
        Nbr_Assists = nbr_Assists;
        Position = position;
        Capitaine = capitaine;
    }
    public Joueur(String login, String password, String firstName, String lastName, Equipe equipe, int nbr_Buts, int nbr_Assists, String position, int capitaine) {
        super(login, password, "JOU", firstName, lastName);
        Equipe = equipe;
        Nbr_Buts = nbr_Buts;
        Nbr_Assists = nbr_Assists;
        Position = position;
        Capitaine = capitaine;
    }
    public Joueur(String login, String password, String firstName, String lastName,int nbr_Buts, int nbr_Assists, String position, int capitaine) {
        super(login, password, "JOU", firstName, lastName);
        Nbr_Buts = nbr_Buts;
        Nbr_Assists = nbr_Assists;
        Position = position;
        Capitaine = capitaine;
    }

    public int getID_Joueur() {
        return ID_Joueur;
    }

    public void setID_Joueur(int ID_Joueur) {
        this.ID_Joueur = ID_Joueur;
    }

    public Equipe getEquipe() {
        return Equipe;
    }

    public void setEquipe(Equipe equipe) {
        Equipe = equipe;
    }

    public int getNbr_Buts() {
        return Nbr_Buts;
    }

    public void setNbr_Buts(int nbr_Buts) {
        Nbr_Buts = nbr_Buts;
    }

    public int getNbr_Assists() {
        return Nbr_Assists;
    }

    public void setNbr_Assists(int nbr_Assists) {
        Nbr_Assists = nbr_Assists;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getCapitaine() {
        return Capitaine;
    }

    public void setCapitaine(int capitaine) {
        Capitaine = capitaine;
    }



}
