package com.example.dell.goatlendar;

import java.util.ArrayList;

public class Application {

    private CompteUtilisateur utilisateurActuel;
    private GestionnaireMail mail;

    public void majBDD(){

    }

    public void ajouterLettreRecherche(){

    }

    public void supprimerAmi(String s, String g){
        utilisateurActuel.supprimerAmi(s,g);
    }

    public void changerAffichage(String a){

    }

    public ArrayList<CompteUtilisateur> getInvites(){
        ArrayList<CompteUtilisateur> invites = new ArrayList<>();
        return invites;
    }

    public void afficherAmis(GestionnaireAmis amis){
        amis.afficherAmis();
    }

    public void ajouterAmisGroupe(String a,String g){
        utilisateurActuel.ajouterAmisGroupe(a,g);
    }

    public boolean verifierDonnees(ArrayList<> donnees){

    }

    public void modifierDonnees(Evenement e, ArrayList<> donnees){}

    public void afficherEvenement(Evenement event){
        event.
    }
}
