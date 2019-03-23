package com.example.dell.goatlendar.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.dell.goatlendar.Controleur.ControleurEvenement;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.evenement.Evenement;

import java.util.ArrayList;

public class AdapterListEvent extends ArrayAdapter<Evenement> {
    private final Context context;
    private final ArrayList<Evenement> values;

    public AdapterListEvent(Context context, ArrayList<Evenement> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_event, parent, false);
        TextView nom = (TextView) rowView.findViewById(R.id.nom_evenement);
        TextView proprietaire = (TextView) rowView.findViewById(R.id.proprietaire_event);
        TextView heur = (TextView) rowView.findViewById(R.id.heur_event);

        nom.setText(values.get(position).getNom());
        proprietaire.setText("karim");
        heur.setText(values.get(position).getHeur());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final FragmentTransaction ft = ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main, new ControleurEvenement(), "NewFragmentTag");
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        return rowView;
    }
}
