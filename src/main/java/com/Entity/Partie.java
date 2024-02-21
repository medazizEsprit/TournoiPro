package com.Entity;

import java.util.Date;

public class Partie {
    private int id;
    private Date dateMatch;
    private int score;
    private Equipe equipe1;
    private Equipe equipe2;
    private Tournoi tournoi;
    private Stade stade;

    public Partie(int id, Date dateMatch, int score, Equipe equipe1, Equipe equipe2, Tournoi tournoi, Stade stade) {
        this.id = id;
        this.dateMatch = dateMatch;
        this.score = score;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.tournoi = tournoi;
        this.stade = stade;
    }

    public Partie(Date dateMatch, Equipe equipe1, Equipe equipe2, Tournoi tournoi, Stade stade) {
        this.dateMatch = dateMatch;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.tournoi = tournoi;
        this.stade = stade;
    }

    public Partie(int id, Date dateMatch, int score) {
        this.id = id;
        this.dateMatch = dateMatch;
        this.score = score;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

}
