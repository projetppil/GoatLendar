package com.example.dell.goatlendar.evenement;


import com.example.dell.goatlendar.user.CompteUtilisateur;

public class Commentaire {
    private String texte;
    private CompteUtilisateur utilisateur;

    public Commentaire(String texte, CompteUtilisateur utilisateur) {
        this.texte = texte;
        this.utilisateur = utilisateur;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public CompteUtilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(CompteUtilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
