package com.example.dell.goatlendar.Social;

import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.util.ArrayList;

public class Groupe extends GestionnaireAmis {

    // Nom du groupe
    private String nom;
    // Liste d'amis qui compose le groupe
   private ArrayList<CompteUtilisateur> listeAmi;

    /**
     * Constructeur de la classe
     * @param nom Nom du groupe
     * @param listeAmi Liste des amis qui compose le groupe au départ
     */
   public Groupe(String nom,ArrayList<CompteUtilisateur> listeAmi){
        this.nom = nom;
        this.listeAmi = listeAmi;
    }

    /**
     * @return Le nom du groupe
     */
    public String getNom(){
        return nom;
    }
    public ArrayList<CompteUtilisateur> getListeAmi(){
        return listeAmi;
    }

}
