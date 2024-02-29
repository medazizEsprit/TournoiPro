package com.Utils;

import com.Entity.Administrateur;
import com.Entity.Joueur;
import com.Entity.Utilisateur;

public class Session {
    // Private static instance variable to hold the single instance of Session
    private static Session instance;
    private static int joueurConnected;
    private static int administrateurConnected;


    // Private constructor to prevent instantiation from outside
    private Session() {
        if (joueurConnected == 0)
            joueurConnected = 0;
        if (administrateurConnected == 0)
            administrateurConnected = 0;
    }

    // Public static method to get the single instance of Session
    public static Session getInstance() {
        // Lazy initialization: Create instance if it doesn't exist
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public static int getJoueurConnected() {
        return joueurConnected;
    }

    public static void setJoueurConnected(int joueurConnected) {
        Session.joueurConnected = joueurConnected;
    }

    public static int getAdministrateurConnected() {
        return administrateurConnected;
    }

    public static void setAdministrateurConnected(int administrateur) {
        Session.administrateurConnected = administrateur;
    }

}
