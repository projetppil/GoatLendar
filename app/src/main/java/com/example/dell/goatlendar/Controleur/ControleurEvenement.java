package com.example.dell.goatlendar.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.goatlendar.Adapter.AdapterListCommentaire;
import com.example.dell.goatlendar.Adapter.AdapterListEvent;
import com.example.dell.goatlendar.Application;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.evenement.Commentaire;
import com.example.dell.goatlendar.evenement.Evenement;
import com.example.dell.goatlendar.url.Constants;
import com.example.dell.goatlendar.user.CompteUtilisateur;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControleurEvenement extends Fragment {

    private Evenement evenement;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.evenement , container , false);

        ArrayList<Commentaire> commentaires = new ArrayList<>();
        commentaires.add(new Commentaire("test1" , new CompteUtilisateur(1 , "Abdi" , "Karim" , "kabdi213@gmail.com")));
        commentaires.add(new Commentaire("test2" , new CompteUtilisateur(2 , "Salhi" , "Mohamed El reda" , "salhi213@gmail.com")));
        commentaires.add(new Commentaire("test3" , new CompteUtilisateur(3 , "El kefif" , "Mohamed Mehdi" , "mehdi213@gmail.com")));

        ListView listView = (ListView)view.findViewById(R.id.list_comment);
        AdapterListCommentaire adapterListComment = new AdapterListCommentaire(getActivity() , commentaires);
        listView.setAdapter(adapterListComment);

        //Remplire les infos de l'affichage
        TextView nomEvent = view.findViewById(R.id.nom_event);
        nomEvent.setText(this.evenement.getNom());
        TextView descriptionEvent = view.findViewById(R.id.description_event);
        descriptionEvent.setText(this.evenement.getDescriptiveEvent());
        TextView HeureEvent = view.findViewById(R.id.heur_event);
        HeureEvent.setText(this.evenement.getHeureDebut());
        TextView DateEvent = view.findViewById(R.id.date_event);
        DateEvent.setText(this.evenement.getDateDebut());
        TextView VilleEvent = view.findViewById(R.id.ville_event);
        VilleEvent.setText(this.evenement.getLieuEvent());
        View includedLayout = view.findViewById(R.id.proprietaire_event);
        TextView nomProprietaire = view.findViewById(R.id.nom_proprietaire);
        //nom proprietaire
        nomProprietaire.setText(this.evenement.getNom());



        ImageView icon_liste_invite = (ImageView)view.findViewById(R.id.invites_icon);
        icon_liste_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction ft = ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction();
                ControleurListInvites c = new ControleurListInvites();
                c.setIdEvent(evenement.getId());
                ft.replace(R.id.main, c, "invited");
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        ImageView icon_delete_event = (ImageView)view.findViewById(R.id.delete_event);
        icon_delete_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supprimerEvenement(evenement.getId());
//                final FragmentTransaction ft = ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction();
//                ControleurListInvites c = new ControleurListInvites();
//                c.setIdEvent(evenement.getId());
//                ft.replace(R.id.main, c, "invited");
//                ft.addToBackStack(null);
//                ft.commit();

            }
        });


        return view;
    }

    private void supprimerEvenement(int IdEvent){

        final int idEvent = IdEvent;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_SupprimerEvenemnt, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Récupération de la réponse JSON
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getBoolean("error")) {
                        Toast.makeText(getContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }else{

                        //OUVRIRE LE MENU 

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Pas de connexion", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("IdEvent",String.valueOf(idEvent));
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }


    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}