package com.example.dell.goatlendar.Controleur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dell.goatlendar.R;

public class ControleurParametres extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.parametres , container , false);
        return view;
    }
}