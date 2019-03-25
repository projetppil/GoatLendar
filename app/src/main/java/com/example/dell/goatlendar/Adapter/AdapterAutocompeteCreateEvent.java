package com.example.dell.goatlendar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.dell.goatlendar.Notification.Notification;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.util.ArrayList;

public class AdapterAutocompeteCreateEvent extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;

    public ArrayList<String> getValues() {
        return values;
    }

    public AdapterAutocompeteCreateEvent(Context context, ArrayList<String> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_user, parent, false);
        TextView name = (TextView)rowView.findViewById(R.id.nom_user_item);
        name.setText(values.get(position));

        return rowView;
    }
}