package com.Entity;

public class Participation {
    private Tournoi Tournoi;
    private Equipe Equipe;

    public Participation(int idTournoi, int idEquipe) {
        Tournoi = new Tournoi(idTournoi);
        Equipe = new Equipe(idEquipe);
    }

    public Participation() {
    }

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
