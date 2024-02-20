package com.Entity;

import java.util.Date;

public class Tournoi {
    private int ID_Tournoi;
    private String Nom_Tournoi;
    private Date Date_Debut;
    private Date Date_Fin;
    private int Nbr_Equipe;
    private Utilisateur Createur;

    public int getID_Tournoi() {
        return ID_Tournoi;
    }

    public void setID_Tournoi(int ID_Tournoi) {
        this.ID_Tournoi = ID_Tournoi;
    }

    public String getNom_Tournoi() {
        return Nom_Tournoi;
    }

    public void setNom_Tournoi(String nom_Tournoi) {
        Nom_Tournoi = nom_Tournoi;
    }

    public Date getDate_Debut() {
        return Date_Debut;
    }

    public void setDate_Debut(Date date_Debut) {
        Date_Debut = date_Debut;
    }

    public Date getDate_Fin() {
        return Date_Fin;
    }

    public void setDate_Fin(Date date_Fin) {
        Date_Fin = date_Fin;
    }

    public int getNbr_Equipe() {
        return Nbr_Equipe;
    }

    public void setNbr_Equipe(int nbr_Equipe) {
        Nbr_Equipe = nbr_Equipe;
    }

    public Utilisateur getCreateur() {
        return Createur;
    }

    public void setCreateur(Utilisateur createur) {
        Createur = createur;
    }

    public Tournoi(int ID_Tournoi, String nom_Tournoi, Date date_Debut, Date date_Fin, int nbr_Equipe, Utilisateur createur) {
        this.ID_Tournoi = ID_Tournoi;
        Nom_Tournoi = nom_Tournoi;
        Date_Debut = date_Debut;
        Date_Fin = date_Fin;
        Nbr_Equipe = nbr_Equipe;
        Createur = createur;
    }

    public Tournoi(int ID_Tournoi) {
        this.ID_Tournoi = ID_Tournoi;
    }

    public Tournoi(String nom_Tournoi, Date date_Debut, Date date_Fin, int nbr_Equipe, Utilisateur createur) {
        Nom_Tournoi = nom_Tournoi;
        Date_Debut = date_Debut;
        Date_Fin = date_Fin;
        Nbr_Equipe = nbr_Equipe;
        Createur = createur;
    }

    public Tournoi() {
    }
}
