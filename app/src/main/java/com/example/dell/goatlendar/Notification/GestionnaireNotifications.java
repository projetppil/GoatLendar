package com.example.dell.goatlendar.Notification;

import com.example.dell.goatlendar.Social.GestionnaireAmis;

import java.util.ArrayList;

public class GestionnaireNotifications {

    // Liste des notifications
    private ArrayList<Notification> listeNotification;
    // Compteur des id pour les notifs
    private static int id=0;

   /* public void notifieSupression(Evenement event){
        id++;
    }*/

    /**
     * @param id id de la notification à retourner
     * @return La notification
     */
    public Notification getNotification(int id){
        return listeNotification.get(id);
    }

    /**
     * @param id Id de la notification
     * @return Retourne le type de la notification
     */
    public String chercherTypeDeNotif(int id){
        return listeNotification.get(id).getTypeNotification();
    }



}
