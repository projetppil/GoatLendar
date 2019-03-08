package com.example.dell.goatlendar;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Menu
    //code a transferer a la classe Accueil
    //--------------------------------------
    private DrawerLayout dl;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    //--------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        //Menu
        //code a transferer a la classe Accueil
        //--------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);

        dl = findViewById(R.id.ac);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this , dl ,R.string.Open , R.string.Close);

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView barre_de_nav = (NavigationView)findViewById(R.id.barre_de_nav);
        barre_de_nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.notification:
                        //ajouter l'action d'ouverture d'activity notification
                        Toast.makeText(getApplicationContext(),"u",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.calendrier:
                        //ajouter l'action d'ouverture d'activity calendrier
                        Toast.makeText(getApplicationContext(),"calendrier",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.calendrierEvent:
                        //ajouter l'action d'ouverture d'activity calendrier Évènements
                        Toast.makeText(getApplicationContext(),"Évènements",Toast.LENGTH_LONG).show();
                        return false;
                    case R.id.amis:
                        //ajouter l'action d'ouverture d'activity amis
                        Toast.makeText(getApplicationContext(),"amis",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.clendrierPropretaire:
                        //ajouter l'action d'ouverture d'activity Mes Évènements
                        Toast.makeText(getApplicationContext(),"clendrierPropretaire",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.impor:
                        //ajouter l'action d'ouverture d'activity import
                        Toast.makeText(getApplicationContext(),"import",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.settings:
                        //ajouter l'action d'ouverture d'activity settings
                        Toast.makeText(getApplicationContext(),"settings",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.exit:
                        //ajouter l'action d'ouverture d'activity exit
                        Toast.makeText(getApplicationContext(),"exit",Toast.LENGTH_LONG).show();
                        return true;
                }
                return false;
            }
        });
        //--------------------------------------
    }

    //Menu
    //code a transferer a la classe Accueil
    //--------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_droit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;
            case R.id.item1:
                //ajouter l'action d'ouverture d'activity notification
                Toast.makeText(getApplicationContext(),"item1",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                //ajouter l'action d'ouverture d'activity exit
                Toast.makeText(getApplicationContext(),"item2",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //--------------------------------------

    public void mdp_oubler(View view) {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.mdp_oublier);
        dialog.show();

    }
}
