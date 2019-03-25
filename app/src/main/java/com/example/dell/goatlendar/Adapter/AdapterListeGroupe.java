package com.example.dell.goatlendar.Adapter;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import com.example.dell.goatlendar.Controleur.ControleurAccueil;
import com.example.dell.goatlendar.Controleur.ControleurEvenement;
import com.example.dell.goatlendar.Controleur.ControleurListeMembreGroupe;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.Social.Groupe;
import com.example.dell.goatlendar.user.CompteUtilisateur;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterListeGroupe extends ArrayAdapter<Groupe> {
    private final Context context;
    private final ArrayList<Groupe> values;

    public AdapterListeGroupe(Context context, ArrayList<Groupe> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.item_group, parent, false);
        TextView nom = (TextView)rowView.findViewById(R.id.nom_groupe);
        nom.setText(values.get(position).getNom());
        TextView nombre_membre = (TextView)rowView.findViewById(R.id.nombre_membre_groupe);
        if(values.size() >1)
           nombre_membre.setText(values.size() + " Membres");
        else
            nombre_membre.setText(values.size() + " Membre");

        final ImageView options = (ImageView)rowView.findViewById(R.id.option_groupe);

        options.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           PopupMenu popup = new PopupMenu(getContext(), view);
                                           Menu m = popup.getMenu();
                                           MenuInflater inflater = popup.getMenuInflater();
                                           inflater.inflate(R.menu.options_groupe, popup.getMenu());
                                           popup.show();


                                           popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                                               @Override
                                               public boolean onMenuItemClick(MenuItem item) {
                                                   Toast.makeText(getContext(),
                                                           "suppression groupe onclick", Toast.LENGTH_SHORT).show();
                                                   return true;
                                               }
                                           });
                                       }
                                   });

             rowView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     final FragmentTransaction ft = ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction();
                     Bundle data = new Bundle();
                     data.putInt("idGroupe" , values.get(position).getId());
                     data.putString("nomGroupe" , values.get(position).getNom());
                     ControleurListeMembreGroupe fragment = new ControleurListeMembreGroupe();
                     fragment.setArguments(data);
                     ft.replace(R.id.main, fragment, "NewFragmentTag");
                     ft.addToBackStack(null);
                     ft.commit();
                 }
             });

                return rowView;
        }


}
