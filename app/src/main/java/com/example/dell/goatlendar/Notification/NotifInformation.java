package com.example.dell.goatlendar.Notification;

public class NotifInformation extends Notification{

    /**
     * Constructeur de la classe
     * @param id Id de la notification
     */
    public NotifInformation(int id) {
        super(id);
    }

    /**
     * @return Le type de la notification
     */
    public String getTypeNotification(){
        return "Information";
    }

}
