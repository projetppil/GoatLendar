package com.example.dell.goatlendar.url;

public class Constants {
    //URL à modifier selon l'adresse IP du serveur
    public static String ROOT_URL="https://goatlendar.000webhostapp.com/Android/v1/";
    //URL de la requete inscription
    public static final String URL_Inscript=ROOT_URL+"enregistrerUtilisateur.php";
    //URL de la requete get Id Utilisateur
    public static final String URL_Connexion=ROOT_URL+"getIdUtilisateur.php";
    //URL de la requete get tous les evenements
    public static final String URL_GetAllEvenements=ROOT_URL+"getAllEvenements.php";
    //URL de la requete get evenement
    public static final String URL_GetEvenement=ROOT_URL+"getEvenement.php";
    //URL de création groupe
    public  static final String URL_CreateGroupe=ROOT_URL+"creerGroupe.php";
    //URL de recupération liste groupes
    public  static final String URL_GetUsersGroupe=ROOT_URL+"getUserGroups.php";
    //URL de la création d'un événement
    public static final String URL_CreerEvenement = ROOT_URL+"creerEvenement.php";
    //URL de récupération de notifications
    public  static final String URL_GetNotifications=ROOT_URL+"getNotifications.php";
    //URL de récupération ddes invites
    public static final String URL_GetInvites = ROOT_URL + "getInvites.php";
    //URL de récupérationde mes evenements
    public static final String URL_GetMesEvenements = ROOT_URL + "getMesEvenements.php";
    //URL de superssion dun evenement
    public  static final String URL_SupprimerEvenemnt=ROOT_URL+"supprimerEvenement.php";
    public static final String URL_GetAmis = ROOT_URL+"getAmis.php";
}
