package com.example.dell.goatlendar.Controleur;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.example.dell.goatlendar.Adapter.AdapterListeGroupe;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.Social.Groupe;
import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.util.ArrayList;

public class ControleurListeGroupe extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_groupe , container , false);

        ListView liste_groupe = (ListView)view.findViewById((R.id.liste__groupes));
        ArrayList<Groupe> groupes = new ArrayList<>();
        groupes.add(new Groupe("Groupe 1" , new ArrayList<CompteUtilisateur>()));
        groupes.add(new Groupe("Groupe 2" , new ArrayList<CompteUtilisateur>()));
        groupes.add(new Groupe("Groupe 3" , new ArrayList<CompteUtilisateur>()));
        groupes.add(new Groupe("Groupe 4" , new ArrayList<CompteUtilisateur>()));
        groupes.add(new Groupe("Groupe 5" , new ArrayList<CompteUtilisateur>()));

        AdapterListeGroupe adapterListeGroupe = new AdapterListeGroupe(getContext() , groupes);
        liste_groupe.setAdapter(adapterListeGroupe);


        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fabcreategroupe);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ouvrir une boite de dialoge contenant la page de creation d'in nouveau evenement
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View promptView = layoutInflater.inflate(R.layout.create_group, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setView(promptView);

                Button valider_creation = (Button) promptView.findViewById(R.id.valider_create_event);
                Button annuler_creation = (Button) promptView.findViewById(R.id.annuler_create_event);

                final AlertDialog dialog = alertDialogBuilder.create();

                valider_creation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
}