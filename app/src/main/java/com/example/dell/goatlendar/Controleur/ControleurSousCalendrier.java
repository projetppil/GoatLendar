package com.example.dell.goatlendar.Controleur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.dell.goatlendar.Adapter.AdapterListeGroupe;
import com.example.dell.goatlendar.Adapter.AdapterListeSousCalendrier;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.evenement.Evenement;
import com.example.dell.goatlendar.user.SousCalendrier;

import java.util.ArrayList;

public class ControleurSousCalendrier extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.liste_sous_calendrier, container, false);
        ListView liste = (ListView)view.findViewById(R.id.liste_sous_calendriers);

        ArrayList<SousCalendrier> calendriers = new ArrayList<>(6);
        calendriers.add(new SousCalendrier(new ArrayList<Evenement>(4) , "test"));
        calendriers.add(new SousCalendrier(new ArrayList<Evenement>(4) , "test"));
        calendriers.add(new SousCalendrier(new ArrayList<Evenement>(4) , "test"));
        calendriers.add(new SousCalendrier(new ArrayList<Evenement>(4) , "test"));
        calendriers.add(new SousCalendrier(new ArrayList<Evenement>(4) , "test"));
        calendriers.add(new SousCalendrier(new ArrayList<Evenement>(4) , "test"));


        AdapterListeSousCalendrier adapterListeGroupe = new AdapterListeSousCalendrier(getContext() , calendriers);
        liste.setAdapter(adapterListeGroupe);


        return view;
    }
}
