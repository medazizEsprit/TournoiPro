package com.Entity;

public class Equipe {
    private int ID_Equipe;
    private String Nom_Equipe;
    private int Nbr_Joueur;

    @Override
    public String toString() {
        return "Equipe{" +
                "ID_Equipe=" + ID_Equipe +
                ", Nom_Equipe='" + Nom_Equipe + '\'' +
                ", Nbr_Joueur=" + Nbr_Joueur +
                '}';
    }

    public Equipe(int ID_Equipe, String Nom_Equipe, int Nbr_Joueur) {
        this.ID_Equipe = ID_Equipe;
        this.Nom_Equipe = Nom_Equipe;
        this.Nbr_Joueur = Nbr_Joueur;
    }
    public Equipe(String Nom_Equipe, int Nbr_Joueur) {
        this.Nom_Equipe = Nom_Equipe;
        this.Nbr_Joueur = Nbr_Joueur;
    }

    public Equipe() {
    }

    public int getID_Equipe() {
        return ID_Equipe;
    }

    public void setID_Equipe(int ID_Equipe) {
        this.ID_Equipe = ID_Equipe;
    }

    public String getNom_Equipe() {
        return Nom_Equipe;
    }

    public void setNom_Equipe(String Nom_Equipe) {
        this.Nom_Equipe = Nom_Equipe;
    }

    public int getNbr_Joueur() {
        return Nbr_Joueur;
    }

    public void setNbr_Joueur(int Nbr_Joueur) {
        this.Nbr_Joueur = Nbr_Joueur;
    }
}
