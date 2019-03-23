package com.example.dell.goatlendar.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.dell.goatlendar.Adapter.AdapterListCommentaire;
import com.example.dell.goatlendar.Adapter.AdapterListEvent;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.evenement.Commentaire;
import com.example.dell.goatlendar.user.CompteUtilisateur;

import java.util.ArrayList;

public class ControleurEvenement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.evenement , container , false);

        ArrayList<Commentaire> commentaires = new ArrayList<>();
        commentaires.add(new Commentaire("test1" , new CompteUtilisateur(1 , "Abdi" , "Karim" , "kabdi213@gmail.com")));
        commentaires.add(new Commentaire("test2" , new CompteUtilisateur(2 , "Salhi" , "Mohamed El reda" , "salhi213@gmail.com")));
        commentaires.add(new Commentaire("test3" , new CompteUtilisateur(3 , "El kefif" , "Mohamed Mehdi" , "mehdi213@gmail.com")));

        ListView listView = (ListView)view.findViewById(R.id.list_comment);
        AdapterListCommentaire adapterListComment = new AdapterListCommentaire(getActivity() , commentaires);
        listView.setAdapter(adapterListComment);





        return view;
    }


}