package com.example.dell.goatlendar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.dell.goatlendar.Notification.Notification;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.evenement.Evenement;

import java.util.ArrayList;

public class AdapterNotifications extends ArrayAdapter<Notification> {
    private final Context context;
    private final ArrayList<Notification> values;

    public AdapterNotifications(Context context, ArrayList<Notification> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_notification, parent, false);

        return rowView;
    }
}

