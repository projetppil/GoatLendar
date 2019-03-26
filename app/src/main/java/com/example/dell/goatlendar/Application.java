package com.example.dell.goatlendar;

import com.example.dell.goatlendar.Social.GestionnaireAmis;
import com.example.dell.goatlendar.evenement.Evenement;
import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.util.ArrayList;

public class Application {
    public static Application instance  =  new Application();
    private CompteUtilisateur utilisateurActuel = new CompteUtilisateur();
    private GestionnaireMail mail;

    private Application(){

    }

    public void majBDD(){

    }

    public void ajouterLettreRecherche(){

    }

    public void supprimerAmi(String s, String g){
        //utilisateurActuel.supprimerAmi(s,g);
    }

    public void changerAffichage(String a){

    }

    public ArrayList<CompteUtilisateur> getInvites(){
        ArrayList<CompteUtilisateur> invites = new ArrayList<>();
        return invites;
    }

    public void afficherAmis(GestionnaireAmis amis){
       // amis.afficherAmis();
    }

    public void ajouterAmisGroupe(String a,String g){
        //utilisateurActuel.ajouterAmisGroupe(a,g);
    }

    //TODO on sait c'est quoi les paramètres
    public boolean verifierDonnees(ArrayList<String> donnees){
           return true;
    }
    //TODO on sait c'est quoi les paramètres

    public void modifierDonnees(Evenement e, ArrayList<String> donnees){}

    public void afficherEvenement(Evenement event){

    }

     public  static Application  getInstance(){
           return instance;
     }


     public void setUtilisateurActuel(CompteUtilisateur utilisateurActuel) {

       this.utilisateurActuel = utilisateurActuel;
     }

    public  CompteUtilisateur getUtilisateurActuel() {
        return utilisateurActuel;
    }
}
