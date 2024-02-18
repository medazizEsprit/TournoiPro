package com.Entity.ListView;

import com.Entity.Equipe;
import com.Entity.Stade;
import com.Entity.Tournoi;

import java.util.Date;

public class PartieListView {
    private int id;
    private Date dateMatch;
    private int score;
    private int equipe1;
    private int equipe2;
    private int tournoi;
    private int stade;

    public PartieListView(int id, Date dateMatch, int score, int equipe1, int equipe2, int tournoi, int stade) {
        this.id = id;
        this.dateMatch = dateMatch;
        this.score = score;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.tournoi = tournoi;
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

    public int getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(int equipe1) {
        this.equipe1 = equipe1;
    }

    public int getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(int equipe2) {
        this.equipe2 = equipe2;
    }

    public int getTournoi() {
        return tournoi;
    }

    public void setTournoi(int tournoi) {
        this.tournoi = tournoi;
    }

    public int getStade() {
        return stade;
    }

    public void setStade(int stade) {
        this.stade = stade;
    }
}
