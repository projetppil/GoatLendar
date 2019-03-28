package com.example.dell.goatlendar.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.goatlendar.Application;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.Social.Groupe;
import com.example.dell.goatlendar.url.Constants;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControleurMenu extends AppCompatActivity {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout dl;
    FragmentManager fg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();


        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);

        dl = findViewById(R.id.ac);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView barre_de_nav = (NavigationView) findViewById(R.id.barre_de_nav);
        barre_de_nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                 fg = getSupportFragmentManager();
                switch (menuItem.getItemId()) {
                    case R.id.notification:
                        //ConroleurNotifications app = new ConroleurNotifications();
                        toolbar.setTitle("Notifications");
                        //fg.beginTransaction().replace(R.id.main , app).addToBackStack("back1").commit();
                        return true;
                    case R.id.calendrier:
                        ControleurAccueil app2 = new ControleurAccueil();
                        toolbar.setTitle("Événements");
                        fg.beginTransaction().replace(R.id.main, app2).commit();

                        return true;
                    case R.id.calendrierEvent:
                        //ajouter l'action d'ouverture d'activity calendrier Évènements
                        return false;
                    case R.id.amis:
                        getListeGroupe(Application.getInstance().getUtilisateurActuel().getId());
                        toolbar.setTitle("Mes Amis");

                        return true;
                    case R.id.clendrierPropretaire:
                        ControleurSousCalendrier app5 = new ControleurSousCalendrier();
                        toolbar.setTitle("Mes calendriers");
                        fg.beginTransaction().replace(R.id.main, app5).commit();

                        return true;
                    case R.id.impor:
                        //ajouter l'action d'ouverture d'activity import
                        return true;
                    case R.id.settings:
                        ControleurParametres parametres = new ControleurParametres();
                        toolbar.setTitle("Paramètres de compte");
                        fg.beginTransaction().replace(R.id.main, parametres).addToBackStack("back").commit();

                        return true;
                    case R.id.exit:
                        //ajouter l'action d'ouverture d'activity exit
                        Application.getInstance().setUtilisateurActuel(null);
                        Intent intent = new Intent(getApplication() , ControleurLogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        return true;

                }
                return false;
            }
        });
        ControleurAccueil app = new ControleurAccueil();
        FragmentManager fg = getSupportFragmentManager();
        fg.beginTransaction().replace(R.id.main, app).commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_droit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;
            case R.id.item1:
                //ajouter l'action d'ouverture d'activity notification
                Toast.makeText(getApplicationContext(), "item1", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                //ajouter l'action d'ouverture d'activity exit
                Toast.makeText(getApplicationContext(), "item2", Toast.LENGTH_LONG).show();
                return true;
        }

         return false;
    }


    void getListeGroupe(int idU){
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
                        Toast.makeText(getApplicationContext(),"Pas de groupe disponible", Toast.LENGTH_SHORT).show();
                    }else{

                        int nbEvenetsRecuperer =jsonObject.length()-2;
                        JSONObject jsonObject2;
                        for (int i = 0; i < nbEvenetsRecuperer ; i++){
                            jsonObject2 = new JSONObject(jsonObject.getString(String.valueOf(i)));
                            String nomGroupe = jsonObject2.getString("NomGroupe");
                            //id Groupe non gérer dans la bdd
                            if (!nomGroupe.equals("")) {
                                liste.add(new Groupe(1, nomGroupe));
                            }
                        }

                        ControleurListeGroupe groupes = new ControleurListeGroupe();
                        groupes.setGroupes(liste);
                        fg.beginTransaction().replace(R.id.main, groupes).addToBackStack("back").commit();

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
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);

    }

}