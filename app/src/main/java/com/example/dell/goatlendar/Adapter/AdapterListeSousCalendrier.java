package com.example.dell.goatlendar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.dell.goatlendar.Notification.Notification;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.user.SousCalendrier;

import java.util.ArrayList;

public class AdapterListeSousCalendrier extends ArrayAdapter<SousCalendrier> {
    private final Context context;
    private final ArrayList<SousCalendrier> values;

    public AdapterListeSousCalendrier(Context context, ArrayList<SousCalendrier> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_sous_calendrier, parent, false);

        return rowView;
    }
}

