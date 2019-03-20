package com.example.dell.goatlendar.Controleur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dell.goatlendar.R;

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




        return view;
    }


}