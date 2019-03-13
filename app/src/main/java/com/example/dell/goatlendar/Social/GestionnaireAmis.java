package com.example.dell.goatlendar.Social;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class GestionnaireAmis {

    private ArrayList<CompteUtilisateur> users;

    public void ajouter(CompteUtilisateur ami){
        users.add(ami);
    }

    public void majBDD(){

    }

    public String consulterBDD(){
        return "";
    }

    public void ajouterAmiGroupe(String amis, String groupe){

    }

}
