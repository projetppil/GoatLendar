package com.example.dell.goatlendar.Controleur;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.goatlendar.Adapter.*;
import com.example.dell.goatlendar.Application;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.Social.Groupe;
import com.example.dell.goatlendar.evenement.Evenement;
import com.example.dell.goatlendar.url.Constants;
import com.example.dell.goatlendar.user.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControleurListeGroupe extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_groupe , container , false);

        ListView liste_groupe = (ListView)view.findViewById((R.id.liste__groupes));
        //recupération de la liste des groupes
        //idU a modifier
        ArrayList<Groupe> groupes = getListeGroupe(22);

        AdapterListeGroupe adapterListeGroupe = new AdapterListeGroupe(getContext() , groupes);
        liste_groupe.setAdapter(adapterListeGroupe);


        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fabcreategroupe);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ouvrir une boite de dialoge contenant la page de creation d'in nouveau evenement
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                final View promptView = layoutInflater.inflate(R.layout.create_group, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setView(promptView);

                Button valider_creation = (Button) promptView.findViewById(R.id.valider_create_event);
                Button annuler_creation = (Button) promptView.findViewById(R.id.annuler_create_event);


                final AlertDialog dialog = alertDialogBuilder.create();

                valider_creation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //enregistrement des infos du groupe
                        TextView nom=promptView.findViewById(R.id.name_new_group);
                        //à modifier pour recupérer le id User Connecté
                        int idUser= 22;
                        creerGroupe("Groupe2",idUser);
                        dialog.dismiss();
                    }
                });


                annuler_creation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });



                dialog.show();


            }
        });



        return view;
    }
    public void creerGroupe(String nom,int id){
        final String  nomG= nom;
        final int idUser=id;

        //progressbar
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_CreateGroupe, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Récupération de la réponse JSON
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getBoolean("error")){
                        Toast.makeText(getContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();


                    }else
                    {
                        Toast.makeText(getContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        //startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map params=new HashMap<>();
                params.put("nom",nomG);
                params.put("idUser",idUser);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());

        requestQueue.add(stringRequest);
    }
    ArrayList<Groupe> getListeGroupe(int idU){
        final int idUser=idU;
        final ArrayList<Groupe> liste=new ArrayList<>();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_GetUsersGroupe, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Récupération de la réponse JSON
                try {
                    JSONObject jsonObject= new JSONObject(response);

                    if (jsonObject.getBoolean("error")){
                        //erreur de retour
                        Toast.makeText(getActivity(),"Pas de groupe disponible", Toast.LENGTH_SHORT).show();
                    }else{

                        int nbEvenetsRecuperer =jsonObject.length()-2;
                        JSONObject jsonObject2;
                        for (int i = 0; i < nbEvenetsRecuperer ; i++){
                            jsonObject2 = new JSONObject(jsonObject.getString(String.valueOf(i)));
                            //id Utilisateur
                            int idGroupe = Integer.valueOf(jsonObject2.getString("idGroupe"));
                            String nomGroupe = jsonObject2.getString("nomGroupe");
                            liste.add(new Groupe(nomGroupe,new ArrayList<CompteUtilisateur>()));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("idUser", String.valueOf(idUser));
                return params;
            }
        };
        //not sure
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());

        requestQueue.add(stringRequest);
        return liste;
    }
}