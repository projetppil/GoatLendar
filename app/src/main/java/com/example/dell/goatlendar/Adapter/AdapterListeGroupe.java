package com.example.dell.goatlendar.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.Social.Groupe;
import com.example.dell.goatlendar.user.CompteUtilisateur;

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

        ImageView editPrivilege = (ImageView)rowView.findViewById(R.id.option_groupe);

        editPrivilege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        return rowView;
    }
}
