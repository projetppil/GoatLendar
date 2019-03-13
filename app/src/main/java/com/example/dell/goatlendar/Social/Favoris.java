package com.example.dell.goatlendar.Social;

import java.util.ArrayList;

public class Favoris extends GestionnaireAmis{

    private ArrayList<Groupe> groupes;

    public Favoris(){
        groupes = new ArrayList<>();
    }

    public boolean existeNom(String nom){
        return false;
    }

    public void creerGroupe(String nom, ArrayList<CompteUtilisateur> listeAmi){
        groupes.add(new Groupe(nom,listeAmi));
    }
}
