package com.example.dell.goatlendar;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ControleurMenu extends Application {
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
        actionBarDrawerToggle = new ActionBarDrawerToggle(this , dl ,R.string.Open , R.string.Close);

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView barre_de_nav = (NavigationView)findViewById(R.id.barre_de_nav);
        barre_de_nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem) {
                FragmentManager fg = getSupportFragmentManager();
                switch (menuItem.getItemId()) {
                    case R.id.notification:
                        //ajouter l'action d'ouverture d'activity notification
                        return true;
                    case R.id.calendrier:
                        ControleurAccueil app = new ControleurAccueil();
                        toolbar.setTitle("Événements");
                        fg.beginTransaction().replace(R.id.main , app).commit();

                        return true;
                    case R.id.calendrierEvent:
                        //ajouter l'action d'ouverture d'activity calendrier Évènements
                        return false;
                    case R.id.amis:
                        //ajouter l'action d'ouverture d'activity amis
                        return true;
                    case R.id.clendrierPropretaire:
                        //ajouter l'action d'ouverture d'activity Mes Évènements
                        return true;
                    case R.id.impor:
                        //ajouter l'action d'ouverture d'activity import
                        return true;
                    case R.id.settings:
                        ControleurParametres parametres = new ControleurParametres();
                        toolbar.setTitle("Paramètres de compte");
                        fg.beginTransaction().replace(R.id.main , parametres).addToBackStack("back").commit();

                        return true;
                    case R.id.exit:
                        //ajouter l'action d'ouverture d'activity exit
                        return true;

                }
                return false;
            }
        });
        ControleurAccueil app = new ControleurAccueil();
        FragmentManager fg = getSupportFragmentManager();
        fg.beginTransaction().replace(R.id.main , app).commit();


    }
}