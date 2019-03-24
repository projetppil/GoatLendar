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
}
