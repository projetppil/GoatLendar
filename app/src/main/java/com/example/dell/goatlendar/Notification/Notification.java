package com.example.dell.goatlendar.Notification;

public abstract class Notification {

    // Id de la notification
    private int id;

    /**
     * Constructeur de la classe
     * @param id Id de la notification
     */
    public Notification(int id){
        this.id = id;
    }

    public abstract String getTypeNotification();

}
