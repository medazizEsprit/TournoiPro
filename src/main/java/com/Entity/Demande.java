package com.Entity;

public class Demande {
    private Tournoi tournoi;
    private String LibelleTournoi;
    private String LibelleEquipe;
    private Equipe equipe;

    public String getLibelleTournoi() {
        return LibelleTournoi;
    }

    public void setLibelleTournoi(String libelleTournoi) {
        LibelleTournoi = libelleTournoi;
    }

    public String getLibelleEquipe() {
        return LibelleEquipe;
    }

    public void setLibelleEquipe(String libelleEquipe) {
        LibelleEquipe = libelleEquipe;
    }

    public Demande() {
        this.tournoi = new Tournoi();
        this.equipe = new Equipe();
    }

    public Demande(Tournoi tournoi, Equipe equipe) {
        this.tournoi = tournoi;
        this.equipe = equipe;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
