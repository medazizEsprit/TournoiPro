package com.Entity.ListView;

import com.Entity.Equipe;
import com.Entity.Stade;
import com.Entity.Tournoi;

import java.util.Date;

public class PartieListView {
    private int id;
    private String dateMatch;
    private int score;
    private String equipe1;
    private String equipe2;
    private String tournoi;
    private String stade;

    public PartieListView(int id, String dateMatch, int score, String equipe1, String equipe2, String tournoi, String stade) {
        this.id = id;
        this.dateMatch = dateMatch;
        this.score = score;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.tournoi = tournoi;
        this.stade = stade;
    }

    public PartieListView(int id, String dateMatch, int score) {
        this.id = id;
        this.dateMatch = dateMatch;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(String dateMatch) {
        this.dateMatch = dateMatch;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(String equipe1) {
        this.equipe1 = equipe1;
    }

    public String getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(String equipe2) {
        this.equipe2 = equipe2;
    }

    public String getTournoi() {
        return tournoi;
    }

    public void setTournoi(String tournoi) {
        this.tournoi = tournoi;
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }
}
