package com.example.dell.goatlendar.Social;

import java.util.ArrayList;

public class Groupe extends GestionnaireAmis {

    private String nom;
    private ArrayList<CompteUtilisateur> listeAmi;

    public Groupe(String nom,ArrayList<CompteUtilisateur> listeAmi){
        this.nom = nom;
        this.listeAmi = listeAmi;
    }

}
