package com.example.dell.goatlendar.evenement;


import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.util.ArrayList;

public class GestionnaireMembre {
    private ArrayList<CompteUtilisateur> lesInvites;

    public GestionnaireMembre() {
        this.lesInvites = new ArrayList<>();
    }

    public void ajouterMembre(CompteUtilisateur membre){
        lesInvites.add(membre);
    }

    public void ajouterMembres(ArrayList<CompteUtilisateur> membres){
        lesInvites.addAll(membres);
    }

    public void supprimer(CompteUtilisateur membre){
        lesInvites.remove(membre);
    }

    public ArrayList<CompteUtilisateur> getLesInvites() {
        return lesInvites;
    }


}
