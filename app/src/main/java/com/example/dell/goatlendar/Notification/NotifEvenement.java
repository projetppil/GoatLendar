package com.example.dell.goatlendar.Notification;

public class NotifEvenement extends Notification{

    /**
     * Constructeur de la classe
     * @param id Id de la notification
     */
    public NotifEvenement(int id) {
        super(id);
    }

    /**
     * @return Le type de la notification
     */
    public String getTypeNotification(){
        return "Evenement";
    }

}
