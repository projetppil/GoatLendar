package com.example.dell.goatlendar.Controleur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.goatlendar.Adapter.AdapterListeInvite;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.url.Constants;
import com.example.dell.goatlendar.user.CompteUtilisateur;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControleurListInvites extends Fragment {


    private int IdEvent ;
    private ListView listeInvitees;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.listinvites , container , false);


        //initialiser la liste des invites
        listeInvitees  = (ListView) view.findViewById(R.id.liste_invite_event);
        afficherListeEvenement(IdEvent);


        return view;
    }


    private void afficherListeEvenement(int idEvent){
        final String  id = String.valueOf(idEvent);


        //progressbar
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_GetInvites, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Récupération de la réponse JSON
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    //remplire la liste des invites
                    if (jsonObject.getBoolean("error"))
                        Toast.makeText(getActivity(),"Pas d'invités pour cet evenement", Toast.LENGTH_SHORT).show();
                    else{
                        int nbInvitesRecuperer =jsonObject.length()-2;
                        final ArrayList<CompteUtilisateur> personnes = new ArrayList<>();
                        JSONObject jsonObject2 ;

                        //remplir la listes des view avec les invites correspendants
                        for (int i = 0; i<nbInvitesRecuperer;i++){
                            jsonObject2 = new JSONObject(jsonObject.getString(String.valueOf(i)));
                            int IdInvites =Integer.valueOf(jsonObject2.getString("IdUser_User"));
                            String NomInvites =jsonObject2.getString("Nom_User");;
                            String PrenomInvites =jsonObject2.getString("Prenom_User");;
                            String EmailInvites =jsonObject2.getString("Mail_User");;

                            personnes.add(new CompteUtilisateur(IdInvites,NomInvites,PrenomInvites,EmailInvites));
                        }
                        //adaprtateur liste invites

                        final  AdapterListeInvite adapter2 = new AdapterListeInvite(getContext(), personnes);
                        listeInvitees.setAdapter(adapter2);

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
                params.put("IdEvent",id);
                return params;
            }
        };
        //not sure
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());

        requestQueue.add(stringRequest);
    }

    public int getIdEvent() {
        return IdEvent;
    }

    public void setIdEvent(int idEvent) {
        IdEvent = idEvent;
    }
}