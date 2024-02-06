package com.Entity;

public class Participation {
    private Tournoi Tournoi;
    private Equipe Equipe;

    public Tournoi getTournoi() {
        return Tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        Tournoi = tournoi;
    }

    public Equipe getEquipe() {
        return Equipe;
    }

    public void setEquipe(Equipe equipe) {
        Equipe = equipe;
    }
}
