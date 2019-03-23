package com.example.dell.goatlendar.Controleur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.dell.goatlendar.Adapter.AdapterNotifications;
import com.example.dell.goatlendar.Notification.NotifInformation;
import com.example.dell.goatlendar.Notification.Notification;
import com.example.dell.goatlendar.R;

import java.util.ArrayList;

public class ConroleurNotifications extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.notification , container , false);
        ListView listView = (ListView)view.findViewById(R.id.listeNotif);
        ArrayList<Notification> notifications = new ArrayList<>();
        notifications.add(new NotifInformation(1));
        notifications.add(new NotifInformation(2));
        notifications.add(new NotifInformation(3));
        notifications.add(new NotifInformation(4));
        AdapterNotifications adapterNotifications = new AdapterNotifications(getActivity() , notifications);
        listView.setAdapter(adapterNotifications);

        return view;
    }


}