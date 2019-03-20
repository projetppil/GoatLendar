package com.example.dell.goatlendar.user;


import com.example.dell.goatlendar.evenement.Evenement;

import java.util.ArrayList;

public class CompteUtilisateur {
    private String nom;
    private String prenom;
    private String email;
    private String image;
    private ArrayList<SousCalendrier> sousCalendriers;


    public CompteUtilisateur(String nom, String prenom, String email, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.image = image;
    }

    public CompteUtilisateur() {


    }

    public ArrayList<String> getNomsSousCalendriers() {
        ArrayList<String> lesNoms = new ArrayList<>();
        for(SousCalendrier s : sousCalendriers){
            lesNoms.add(s.getNom());
        }
        return lesNoms;
    }

    public ArrayList<Evenement> getEvenements(String nomSousCalendrier) {
        ArrayList<Evenement> evenements = new ArrayList<>();
        for(SousCalendrier s : sousCalendriers){
            if(s.getNom()==nomSousCalendrier){
               //evenements = s.getEvenements();
            }
        }
        return evenements;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<SousCalendrier> getSousCalendriers() {
        return sousCalendriers;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
