package com.example.dell.goatlendar.user;

import com.example.dell.goatlendar.evenement.Evenement;

import java.util.ArrayList;

public class SousCalendrier {

    private ArrayList<Evenement> evenements;
    private String nom;

    public SousCalendrier(ArrayList<Evenement> evenements, String nom) {
        this.evenements = evenements;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Evenement getEvenement(String nomEvenement){
        Evenement evenement = null;
        for(int i=0;i<evenements.size();i++){
            if(evenements.get(i).identifierEvenement()==nomEvenement){
                evenement = evenements.get(i);
            }
        }
        return evenement;
    }

    public Evenement getEvenement(int id){
        Evenement evenement = null;
        for(int i=0;i<evenements.size();i++){
            if(evenements.get(i).getId()==id){
                evenement = evenements.get(i);
            }
        }
        return evenement;
    }

    public void creerEvenement(){
        Evenement evenement = new Evenement();
    }

    public ArrayList<Evenement> rechercherEvenement(String recherche) {
        ArrayList<Evenement> resultRecherche = new ArrayList<>();
        for(Evenement e : evenements){
            if(e.identifierEvenement().indexOf(recherche)!=-1){
                resultRecherche.add(e);
            }
        }
        return resultRecherche;
    }

    public ArrayList<Evenement> filtrerEvenement(int type) {
        ArrayList<Evenement> filtre = new ArrayList<>();
        for(Evenement e : evenements){

        }
        return filtre;
    }

    public ArrayList<Evenement> getEvenements() {
        return evenements;
    }
}
