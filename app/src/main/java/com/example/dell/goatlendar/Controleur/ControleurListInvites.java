package com.example.dell.goatlendar.Controleur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.dell.goatlendar.Adapter.AdapterListeInvite;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.util.ArrayList;

public class ControleurListInvites extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.listinvites , container , false);


        final ArrayList<CompteUtilisateur> personnes = new ArrayList<>();
        personnes.add(new CompteUtilisateur(1 , "Abdi" , "Karim" , "karim213@gmail.com"));
        personnes.add(new CompteUtilisateur(2 , "Salhi" , "Mohamed El reda" , "reda213@gmail.com"));
        personnes.add(new CompteUtilisateur(3 , "El kefif" , "Mohamed Mehdi" , "mehdi213@gmail.com"));


        //initialiser la liste des invites
        ListView listeInvitees  = (ListView) view.findViewById(R.id.liste_invite_event);
        //adaprtateur liste invites
        final  AdapterListeInvite adapter2 = new AdapterListeInvite(getContext(), personnes);
        listeInvitees.setAdapter(adapter2);



        return view;
    }
}