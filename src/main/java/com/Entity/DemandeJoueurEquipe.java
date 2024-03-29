package com.Entity;

public class DemandeJoueurEquipe {
    private int ID_Joueur;
    private int ID_Equipe;
    private String Position;

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public DemandeJoueurEquipe(int ID_Joueur, int ID_Equipe, String Position) {
        this.ID_Joueur = ID_Joueur;
        this.ID_Equipe = ID_Equipe;
        this.Position = Position;
    }

    public int getID_Joueur() {
        return ID_Joueur;
    }

    public void setID_Joueur(int ID_Joueur) {
        this.ID_Joueur = ID_Joueur;
    }

    public int getID_Equipe() {
        return ID_Equipe;
    }

    public void setID_Equipe(int ID_Equipe) {
        this.ID_Equipe = ID_Equipe;
    }
}
