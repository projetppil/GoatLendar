package com.example.dell.goatlendar.Social;

import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.util.ArrayList;

public class Groupe extends GestionnaireAmis {

    int id;
    // Nom du groupe
    private String nom;
    // Liste d'amis qui compose le groupe
   private ArrayList<CompteUtilisateur> listeAmi;

    /**
     * Constructeur de la classe
     * @param nom Nom du groupe
     * @param id Liste des amis qui compose le groupe au départ
     */
   public Groupe(int id , String nom){
        this.nom = nom;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * @return Le nom du groupe
     */
    public String getNom(){
        return nom;
    }
    public ArrayList<CompteUtilisateur> getListeAmi(){
        listeAmi = new ArrayList<>();
        listeAmi.add(new CompteUtilisateur(1 , "Abdi" , "Karim" , "karim213@gmail.com"));
        listeAmi.add(new CompteUtilisateur(2 , "El kefif" , "Mohamed Mehdi" , "mehdi213@gmail.com"));
        listeAmi.add(new CompteUtilisateur(3 , "Salhi" , "Mohamed El Reda" , "salhi213@gmail.com"));
        return listeAmi;
    }

}
