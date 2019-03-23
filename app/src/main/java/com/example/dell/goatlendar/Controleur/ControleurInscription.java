package com.example.dell.goatlendar.Controleur;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.url.Constants;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ControleurInscription extends AppCompatActivity {

    private ProgressBar progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        //showMenu();

       // progressDialog = new ProgressBar(this);

        Button b = findViewById(R.id.lancerInscription);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nom_inscription = findViewById(R.id.nom_inscription);
                TextView prenom_inscription= findViewById(R.id.prenom_inscription);
                TextView email_inscription= findViewById(R.id.email_inscription);
                TextView mdp_inscription= findViewById(R.id.mdp_inscription);
                TextView mdp_confirm_inscription= findViewById(R.id.mdp_confirm_inscription);

                String pattern = "[a-zA-Z\\s]*";
                String pattern2 = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";

                if(nom_inscription.getText().toString().length() <20){
                    if (nom_inscription.getText().toString().matches(pattern)){
                        String nom = nom_inscription.getText().toString();
                        if(prenom_inscription.getText().toString().length() <20){
                           if (prenom_inscription.getText().toString().matches(pattern)){
                               String prenom =prenom_inscription.getText().toString();
                               if (email_inscription.getText().toString().matches(pattern2)){
                                   String email= email_inscription.getText().toString();
                                   if (mdp_inscription.getText().toString().length() <20){
                                       String mdp =mdp_inscription.getText().toString();
                                       if (mdp_confirm_inscription.getText().toString().equals(mdp_inscription.getText().toString())){
                                           enregistrerUtilisateur(nom , prenom, email ,mdp);
                                       }else {
                                           Toast.makeText(getApplicationContext(),"Confirmer votre MDP", Toast.LENGTH_SHORT).show();
                                       }
                                   }else {
                                       Toast.makeText(getApplicationContext(),"Mot de passe tres long", Toast.LENGTH_SHORT).show();
                                   }
                               }else {
                                   Toast.makeText(getApplicationContext(),"Exemple invalide \n ex : abcdef@gmail.com", Toast.LENGTH_SHORT).show();
                               }
                           }else {
                               Toast.makeText(getApplicationContext(),"Prenom invalide", Toast.LENGTH_SHORT).show();
                           }
                        }else {
                            Toast.makeText(getApplicationContext(),"Prenom tres long", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Nom invalide", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Nom tres long", Toast.LENGTH_SHORT).show();
                }
              //


                            }
        });

    }

    private void enregistrerUtilisateur(String n , String pn , String em ,String mdpasse){

        final String  prenom= pn;
        final String nom=n;
        final String mdp= mdpasse;
        final String email=em;
        final  String chemin = "/image.png";

        //progressbar
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_Inscript, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Récupération de la réponse JSON
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("prenom",prenom);
                params.put("nom",nom);
                params.put("mdp",mdp);
                params.put("email",email);
                params.put("chemin" , chemin);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }
}