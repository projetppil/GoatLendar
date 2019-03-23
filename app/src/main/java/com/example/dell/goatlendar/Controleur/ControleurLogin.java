package com.example.dell.goatlendar.Controleur;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class ControleurLogin extends AppCompatActivity {
    protected int res = 0;
    protected Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    public void inscription(View view) {
        Intent intent = new Intent(this , ControleurInscription.class);
        startActivity(intent);
    }

    public void connexion(View view) {
        /**
         * verification des champs
         */
        TextView nom_inscription = findViewById(R.id.email_connexion);
        TextView mdp_inscription= findViewById(R.id.password_connexion);
        intent = new Intent(this , ControleurMenu.class);

        String pattern2 = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";

        if (nom_inscription.getText().toString().matches(pattern2)){
            String email = nom_inscription.getText().toString();
            if (mdp_inscription.getText().toString().length()>0){
                String mdp = mdp_inscription.getText().toString();
                connexion(email , mdp);

            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Entrer Un mot de passe", Toast.LENGTH_SHORT);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                v.setTextColor(Color.WHITE);
                toast.show();
                //Toast.makeText(getApplicationContext(),"Entrer Un mot de passe", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Email invalide", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
            toast.show();

        }

    }

    private void connexion(String em , String mdpasse){

        final String mdp= mdpasse;
        final String email=em;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_Connexion, new Response.Listener<String>() {

            @Override
                public void onResponse(String response) {
                //Récupération de la réponse JSON
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getBoolean("error")) {
                        res = 1;
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(intent);
                    }


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
                params.put("mdp",mdp);
                params.put("email",email);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
