package com.example.dell.goatlendar.Notification;

public class NotifEvenement extends Notification{

    private int idEvent;
    private String titre;
    private String description;
    private String nom_eve;
    /**
     * Constructeur de la classe
     * @param id Id de la notification
     */
    public NotifEvenement(int id ,int idEvent, String titre, String description, String nom_eve) {

        super(id);
        this.idEvent = idEvent;
        this.titre = titre;
        this.description = description;
        this.nom_eve = nom_eve;
    }

    /**
     * @return Le type de la notification
     */
    public String getTypeNotification(){
        return "Evenement";
    }

    public int getIdEvent() {
        return idEvent;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getNom_eve() {
        return nom_eve;
    }
}
