package com.example.dell.goatlendar;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.goatlendar.Social.GestionnaireAmis;
import com.example.dell.goatlendar.evenement.Evenement;
import com.example.dell.goatlendar.url.Constants;
import com.example.dell.goatlendar.user.CompteUtilisateur;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private static  final String ATTR_ID="idUser";
    private static  final String ATTR_PRENOM="Prenom";
    private static final String ATTR_NOM="Nom";
    private static  final String ATTR_MAIL="Mail";






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


    public ArrayList<CompteUtilisateur> getUsers( Context context) {
        final ArrayList<CompteUtilisateur> res = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GetAmis, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Je suis la réponse "+response);

                try {
                    //System.out.printf("here1");
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getBoolean("error")) {
                    }else{

                        int nbNotifs = jsonObject.length()-2;
                        JSONObject jsonObject1;
                        for(int i =0 ;i<nbNotifs;i++) {
                            jsonObject1 = new JSONObject(jsonObject.getString(String.valueOf(i)));
                            CompteUtilisateur user = new CompteUtilisateur(
                                    Integer.valueOf(jsonObject1.getString(ATTR_ID)),
                                    jsonObject1.getString(ATTR_NOM),
                                    jsonObject1.getString(ATTR_PRENOM),
                                    jsonObject1.getString(ATTR_MAIL));

                            res.add(user);

                        }
                    }

                } catch (JSONException e) {
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        requestQueue.add(stringRequest);
        return res;

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

    public CompteUtilisateur getUtilisateurCourant(){
      return utilisateurActuel;
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
