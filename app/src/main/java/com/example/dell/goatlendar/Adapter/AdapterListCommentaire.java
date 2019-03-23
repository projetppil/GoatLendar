package com.example.dell.goatlendar.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.dell.goatlendar.Controleur.ControleurEvenement;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.evenement.Commentaire;
import com.example.dell.goatlendar.evenement.Evenement;

import java.util.ArrayList;

public class AdapterListCommentaire extends ArrayAdapter<Commentaire> {
    private final Context context;
    private final ArrayList<Commentaire> values;

    public AdapterListCommentaire(Context context, ArrayList<Commentaire> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        View rowView = inflater.inflate(R.layout.item_comment, parent, false);

        TextView nom = (TextView)rowView.findViewById(R.id.commentateur);
        nom.setText(values.get(position).getUtilisateur().getNom() + " " + values.get(position).getUtilisateur().getPrenom());
        TextView text = (TextView)rowView.findViewById(R.id.commentaire);
        text.setText(values.get(position).getTexte());

        return rowView;
    }
}
