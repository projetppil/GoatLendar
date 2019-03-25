package com.example.dell.goatlendar.Controleur;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import com.example.dell.goatlendar.Adapter.AdapterAutocompeteCreateEvent;
import com.example.dell.goatlendar.Adapter.AdapterListeInvite;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.Social.Groupe;
import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.util.ArrayList;

public class ControleurListeMembreGroupe extends Fragment {


    private ArrayList<CompteUtilisateur> membre_groupe ;
    private ArrayList<CompteUtilisateur> selected_users ;
    private ArrayList<String> users_name;


    /*public ControleurListeMembreGroupe(Groupe groupe){
         membre_groupe = groupe.getListeAmi();
         users_name = new ArrayList<>();
         for(CompteUtilisateur user : membre_groupe)
               users_name.add(user.getNom() + " " + user.getPrenom());
    }
*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.liste_membre_groupe , container , false);

        Bundle arguments = getArguments();
        membre_groupe = (ArrayList<CompteUtilisateur>) arguments.get("Groupe");
        for(CompteUtilisateur user : membre_groupe)
            users_name.add(user.getNom() + " " + user.getPrenom());


        FloatingActionButton button = (FloatingActionButton)view.findViewById(R.id.fabAddInvite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ouvrir une boite de dialoge contenant la page de creation d'in nouveau evenement
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View promptView = layoutInflater.inflate(R.layout.inviter_membre_groupe, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setView(promptView);
                final AlertDialog dialog = alertDialogBuilder.create();

                Button valider_ajout = (Button) promptView.findViewById(R.id.valider_ajouter_membres_groupe);
                Button annuler_ajout = (Button) promptView.findViewById(R.id.annuler_ajouter_membres_groupe);

                dialog.show();
                valider_ajout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                annuler_ajout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                AdapterAutocompeteCreateEvent adapter = new AdapterAutocompeteCreateEvent(getContext(),users_name);
                final AutoCompleteTextView textView = (AutoCompleteTextView) promptView.findViewById(R.id.autoCompleteAddUsersGroupe);
                textView.setAdapter(adapter);

                //initialiser la liste des invites
                ListView liste  = (ListView) promptView.findViewById(R.id.liste_affichage_amis);

                //adaprtateur liste invites
                final AdapterListeInvite adapter2 = new AdapterListeInvite(getContext(), selected_users);
                liste.setAdapter(adapter2);


            }
        });

        return view;
    }
}