package com.Entity;

public class Stade {
    private int ID_Stade;
    private String NomStade;
    private int NumSpectateurs;
    private String Lieu;

    @Override
    public String toString() {
        return "Stade{" +
                "ID_Stade=" + ID_Stade +
                ", NomStade='" + NomStade + '\'' +
                ", NumSpectateurs=" + NumSpectateurs +
                ", Lieu='" + Lieu + '\'' +
                '}';
    }

    public Stade(int ID_Stade, String nomStade, int numSpectateurs, String lieu) {
        this.ID_Stade = ID_Stade;
        NomStade = nomStade;
        NumSpectateurs = numSpectateurs;
        Lieu = lieu;
    }
    public Stade(String nomStade, int numSpectateurs, String lieu) {
        NomStade = nomStade;
        NumSpectateurs = numSpectateurs;
        Lieu = lieu;
    }
    public int getID_Stade() {
        return ID_Stade;
    }

    public void setID_Stade(int ID_Stade) {
        this.ID_Stade = ID_Stade;
    }

    public String getNomStade() {
        return NomStade;
    }

    public void setNomStade(String nomStade) {
        NomStade = nomStade;
    }

    public int getNumSpectateurs() {
        return NumSpectateurs;
    }

    public void setNumSpectateurs(int numSpectateurs) {
        NumSpectateurs = numSpectateurs;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String lieu) {
        Lieu = lieu;
    }
}
