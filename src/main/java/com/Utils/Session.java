package com.Utils;

import com.Entity.Administrateur;
import com.Entity.Joueur;
import com.Entity.Utilisateur;

public class Session {
    // Private static instance variable to hold the single instance of Session
    private static Session instance;
    private static Joueur joueurConnected;
    private static Administrateur administrateurConnected;


    // Private constructor to prevent instantiation from outside
    private Session() {
        if (joueurConnected == null)
            joueurConnected = new Joueur();
        if (administrateurConnected == null)
            administrateurConnected = new Administrateur();
    }

    // Public static method to get the single instance of Session
    public static Session getInstance() {
        // Lazy initialization: Create instance if it doesn't exist
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public static Joueur getJoueurConnected() {
        return joueurConnected;
    }

    public static void setJoueurConnected(Joueur joueurConnected) {
        Session.joueurConnected = joueurConnected;
    }

    public static Administrateur getAdministrateurConnected() {
        return administrateurConnected;
    }

    public static void setAdministrateurConnected(Administrateur administrateur) {
        Session.administrateurConnected = administrateur;
    }

}
