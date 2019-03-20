package com.example.dell.goatlendar.Social;

import java.util.ArrayList;

public abstract class GestionnaireAmis {

    // Liste des amis de l'utilisateur
    //private ArrayList<CompteUtilisateur> users;

    /**
     * Constructeur de la classe
     */
    public GestionnaireAmis(){
       // users = new ArrayList<>();
    }

    /**
     * Ajoute un utilisateur en ami
     * @param ami Ami à ajouter
     */
    /*public void ajouter(CompteUtilisateur ami){
        users.add(ami);
    }*/

    public void majBDD(){

    }

    public String consulterBDD(){
        return "";
    }

    /**
     * Ajoute un ami dans un groupe
     * @param ami Ami à ajouter
     * @param groupe Groupe dans lequelle il faut ajouter l'ami
     */
    public void ajouterAmiGroupe(String ami, String groupe){

    }

}
