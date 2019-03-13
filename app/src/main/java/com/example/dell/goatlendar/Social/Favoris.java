package com.example.dell.goatlendar.Social;

import java.util.ArrayList;

public class Favoris extends GestionnaireAmis{

    // Listes des groupes ajoutés aux favoris de l'utilisateur
    private ArrayList<Groupe> groupes;

    /**
     * Constructeur de la classe
     */
    public Favoris(){
        groupes = new ArrayList<>();
    }

    /**
     * Regarde si un groupe de même nom existe déjà
     * @param nom du groupe
     * @return Vrai si un groupe existe déjà sous ce nom
     */
    public boolean existeNom(String nom){
        int cpt = 0;
        boolean trouve = false;
        Groupe g;
        // On cherche s'il existe un groupe de même nom
        while(cpt < groupes.size() && !trouve){
            g = groupes.get(cpt);
            // Si on trouve un groupe du même nom on arrête tout
            if(g.getNom().equals(nom)){
                trouve = true;
            }
        }
        return trouve;
    }

    /**
     * Ajoute un nouveau groupe
     * @param nom Nom du nouveau groupe
     * @param listeAmi Liste des amis de départ qui vont composer le groupe
     */
    public void creerGroupe(String nom, ArrayList<CompteUtilisateur> listeAmi){
        groupes.add(new Groupe(nom,listeAmi));
    }
}
