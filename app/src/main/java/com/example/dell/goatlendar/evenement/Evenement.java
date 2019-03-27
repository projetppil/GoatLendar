package com.example.dell.goatlendar.evenement;


import android.graphics.Color;
import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;


public class Evenement {
    private int type;
    private int id;
    private Color couleur;
    private String nom;
    private Chat chat;
    private GestionnaireMembre gestionnaireMembre;
    private String dateFin;
    private String dateDebut;
    private String HeureDebut;
    private String HeureFin;
    private String lieuEvent ;
    private String DescriptiveEvent;

    public Evenement() {
        this.chat = new Chat();
        this.gestionnaireMembre = new GestionnaireMembre();
    }

    public String getNom() {
        return nom;
    }

    public String getHeur(){
        return dateDebut;
    }


    public Evenement(int type, Color couleur, String nom,  String dateFin, String dateDebut , String HeureDebut, String lieu , String Descriptive) {
        this.chat = new Chat();
        this.gestionnaireMembre = new GestionnaireMembre();
        this.type = type;
        this.couleur = couleur;
        this.nom = nom;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.HeureDebut =  HeureDebut;
        this.lieuEvent = lieu;
        this.DescriptiveEvent = Descriptive;
    }

    public Evenement(int type, Color couleur, String nom, String dateFin, String dateDebut ,String HeureDebut, String lieu , String Descriptive
                     ,int id) {
        this.chat = new Chat();
        this.gestionnaireMembre = new GestionnaireMembre();
        this.type = type;
        this.couleur = couleur;
        this.nom = nom;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.id = id;
        this.HeureDebut =  HeureDebut;
        this.lieuEvent = lieu;
        this.DescriptiveEvent = Descriptive;
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

    public Color getCouleur() {
        return couleur;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getHeureDebut() {
        return HeureDebut;
    }

    public String getHeureFin() {
        return HeureFin;
    }

    public String getLieuEvent() {
        return lieuEvent;
    }

    public String getDescriptiveEvent() {
        return DescriptiveEvent;
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

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setHeureDebut(String heureDebut) {
        HeureDebut = heureDebut;
    }

    public void setHeureFin(String heureFin) {
        HeureFin = heureFin;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
