package com.example.dell.goatlendar.evenement;


import android.graphics.Color;
import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;


public class Evenement {
    private int type;
    private Color couleur;
    private String nom;
    private Chat chat;
    private GestionnaireMembre gestionnaireMembre;
    private Timestamp dateFin;
    private Timestamp dateDebut;

    public Evenement() {
        this.chat = new Chat();
        this.gestionnaireMembre = new GestionnaireMembre();
    }

    public Evenement(int type, Color couleur, String nom, Timestamp dateFin, Timestamp dateDebut) {
        this.chat = new Chat();
        this.gestionnaireMembre = new GestionnaireMembre();
        this.type = type;
        this.couleur = couleur;
        this.nom = nom;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
    }

    public int getType(){
        return type;
    }

    public void inviterMembres(CompteUtilisateur... invites){
        ArrayList<CompteUtilisateur> listeInvites = new ArrayList<CompteUtilisateur>(Arrays.asList(invites));
        //gestionnaireMembre.ajouterMembres(listeInvites);
    }

    public ArrayList<CompteUtilisateur> getInvites(){

        //return gestionnaireMembre.getLesInvites();
        return null;
    }

    public String identifierEvenement() {
        return nom;
    }

    public Chat getChat() {
        return chat;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }
}
