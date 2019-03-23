package com.example.dell.goatlendar.Controleur;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.dell.goatlendar.Adapter.AdapterListEvent;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.evenement.Evenement;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ControleurAccueil extends Fragment  {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.calendar , container , false);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragment= new ControleurEvenement();
                FragmentManager fg = getActivity().getSupportFragmentManager();
                fg.beginTransaction().replace(R.id.main , fragment).commit();

            }
        });
        ArrayList<Evenement> evenements = new ArrayList<>();

        evenements.add(new Evenement(1, new Color(), "Anniversaire 1",  new Timestamp(System.currentTimeMillis()),  new Timestamp(System.currentTimeMillis())));
        evenements.add(new Evenement(2, new Color(), "Anniversaire 2",  new Timestamp(System.currentTimeMillis()),  new Timestamp(System.currentTimeMillis())));
        evenements.add(new Evenement(3, new Color(), "Anniversaire 3",  new Timestamp(System.currentTimeMillis()),  new Timestamp(System.currentTimeMillis())));
        evenements.add(new Evenement(4, new Color(), "Anniversaire 4",  new Timestamp(System.currentTimeMillis()),  new Timestamp(System.currentTimeMillis())));

        ListView listView = (ListView)view.findViewById(R.id.listeEvent);


        AdapterListEvent adapterListEvent = new AdapterListEvent(getActivity() , evenements);
        listView.setAdapter(adapterListEvent);


        CalendarView calendarView = (CalendarView)view.findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();

            }

        });



        return view;
    }


}