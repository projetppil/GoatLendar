package com.example.dell.goatlendar.Controleur;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.goatlendar.Adapter.AdapterAutocompeteCreateEvent;
import com.example.dell.goatlendar.Adapter.AdapterListEvent;
import com.example.dell.goatlendar.Adapter.AdapterListeInvite;
import com.example.dell.goatlendar.Application;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.evenement.Evenement;
import com.example.dell.goatlendar.user.CompteUtilisateur;

import com.example.dell.goatlendar.url.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ControleurAccueil extends Fragment  {
     //la liste des invite lors de la creation d'un nouveau evenement
    private ListView listeInvitees;
    //pour recuperer la vue cree evenement
    private View promptView;
     Button image_chooser , image_remove;

    //affichage des evenement dans la page d'accueil
    public ArrayList<Evenement> evenements = new ArrayList<>();

    //
    private ListView listView ;
    private TextView textViewNomUser;
    private TextView textViewEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        //affichage de la page d'accueil
        View view = inflater.inflate(R.layout.calendar , container , false);
        //recuperation du bouton ajouter un evenement
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);

        //gestion du click sur le bouton ajouter un evenement
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //ouvrir une boite de dialoge contenant la page de creation d'in nouveau evenement
                 LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                 promptView = layoutInflater.inflate(R.layout.create_event, null);
                 final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                 alertDialogBuilder.setView(promptView);

                Button valider_creation = (Button) promptView.findViewById(R.id.button_ok);
                Button annuler_creation = (Button) promptView.findViewById(R.id.button_cancel);


                final AlertDialog dialog = alertDialogBuilder.create();


                //rrecuperation du bouton selectionner une image de la page cree evenemenr
                  image_chooser = (Button)promptView.findViewById(R.id.button_choose_image);
                //rrecuperation du bouton retirer une image de la page cree evenemenr
                 image_remove = (Button)promptView.findViewById(R.id.button_remove_image);

                //gestion du sur le bouton selectionner une image
                image_chooser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            } else {
                                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(galleryIntent, 1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


                //gestion du sur le bouton retirer une image
                image_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageView imageView = (ImageView) promptView.findViewById(R.id.selectedImage);
                        imageView.setImageBitmap(null);
                        imageView.setVisibility(View.GONE);
                        image_remove.setVisibility(View.GONE);
                        image_chooser.setVisibility(View.VISIBLE);


                    }
                });


                /**
                 * recuperer unr arraylist<CompteUtilisateur> qui va contenir toutes les utilisateur de l'application
                 * dans mon cas je vais la remplir mannuellement apres changer la  avec les bonnes informations
                 */
                final ArrayList<CompteUtilisateur> users = new ArrayList<>();
                users.add(new CompteUtilisateur(1 , "Abdi" , "Karim" , "karim213@gmail.com"));
                users.add(new CompteUtilisateur(2 , "Salhi" , "Mohamed El reda" , "reda213@gmail.com"));
                users.add(new CompteUtilisateur(3 , "El kefif" , "Mohamed Mehdi" , "mehdi213@gmail.com"));

                final ArrayList<String> users_names = new ArrayList<>();
                for (int i=0 ; i<users.size() ; i++)
                    users_names.add(users.get(i).getNom() + " "+ users.get(i).getPrenom());

                //remplissage manuellle des utilisateur
                final ArrayList<CompteUtilisateur> personnes = new ArrayList<>();

                //permet de stcker les memebre selectionner :  le type a descuter, il faut une class qui stock les information des inviter a la place de string
                final ArrayList<CompteUtilisateur> values = new ArrayList<>();

                //gestion de l'autocomplete : rechercher une personne
                AdapterAutocompeteCreateEvent adapter = new AdapterAutocompeteCreateEvent(getContext(),users_names);
                final AutoCompleteTextView textView = (AutoCompleteTextView) promptView.findViewById(R.id.autoCompleteTextView1);
                textView.setAdapter(adapter);

                 //initialiser la liste des invites
                 listeInvitees  = (ListView) promptView.findViewById(R.id.liste_affichage_amis);

                 //adaprtateur liste invites
                final  AdapterListeInvite adapter2 = new AdapterListeInvite(getContext(), values);
                listeInvitees.setAdapter(adapter2);

                 //quand on click sur un item de l'auto complete, l'utilisateur selection sera ajouter a la liste des inviter
                textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        System.out.println(adapterView.getAdapter().getItem(i));
                        values.add(users.get(i));
                        adapter2.notifyDataSetChanged();

                    }


                });


                valider_creation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.main, new ControleurEvenement(), "NewFragmentTag");
                        ft.addToBackStack(null);
                        ft.commit();

                        dialog.dismiss();

                    }
                });

                annuler_creation.setOnClickListener(new View.OnClickListener() {
                       @Override
                        public void onClick(View view) {
                          dialog.dismiss();
                        }
                });
                dialog.show();


            }

        });


        //*************************
        listView = (ListView)view.findViewById(R.id.listeEvent);
        //*************************

        CalendarView calendarView = (CalendarView)view.findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //appel a une fonction qui prend en entree une date et retourne arrayliste<Evenement> contenant la liste des evenement
                Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
                String m ;
                String d ;

                if (month+1<10)
                    m="0"+String.valueOf(month+1);
                else
                    m=String.valueOf(month+1);

                if (dayOfMonth<10)
                    d="0"+String.valueOf(dayOfMonth);
                else
                    d=String.valueOf(dayOfMonth);

                String date = String.valueOf(year) + "-" + m + "-" + d;
                recupererTouslesEvenements(date);
            }

        });


        //Remplir les infos de la barre de la navigation
        CompteUtilisateur c= Application.getInstance().getUtilisateurActuel();
        NavigationView  navigationView = getActivity().findViewById(R.id.barre_de_nav);
        textViewNomUser = navigationView.getHeaderView(0).findViewById(R.id.textNom);
        textViewEmail = navigationView.getHeaderView(0).findViewById(R.id.textEmail);
        textViewNomUser.setText(c.getNom());
        textViewEmail.setText(c.getEmail());


        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1  && null != data) {



            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) promptView.findViewById(R.id.selectedImage);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            imageView.setVisibility(View.VISIBLE);
            image_remove.setVisibility(View.VISIBLE);
            image_chooser.setVisibility(View.GONE);

        }

    }

    private void recupererTouslesEvenements(String d){

        final String  date = d;

        System.out.println(d);
        //progressbar
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_GetAllEvenements, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Récupération de la réponse JSON
                try {
                    evenements.clear();
                    JSONObject jsonObject= new JSONObject(response);

                    if (jsonObject.getBoolean("error")){
                        //erreur de retour
                        Toast.makeText(getActivity(),"Pas d'evenements disponibles", Toast.LENGTH_SHORT).show();
                    }else{

                        int nbEvenetsRecuperer =jsonObject.length()-2;
                        JSONObject jsonObject2;
                        for (int i = 0; i < nbEvenetsRecuperer ; i++){
                            jsonObject2 = new JSONObject(jsonObject.getString(String.valueOf(i)));

                            int TypeEvent = Integer.valueOf(jsonObject2.getString("Type_eve"));
                            String nomEvent = jsonObject2.getString("Nom_eve");


                            evenements.add(new Evenement(TypeEvent, new Color(), nomEvent,
                                    new Timestamp(System.currentTimeMillis()), date));

                        }
                        AdapterListEvent adapterListEvent = new AdapterListEvent(getActivity() , evenements);
                        listView.setAdapter(adapterListEvent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("date",date);
                return params;
            }
        };
        //not sure
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());

        requestQueue.add(stringRequest);
    }


    private void afficherUnEvenement(int idEvent){
        final String  id = String.valueOf(idEvent);

        //progressbar
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_GetEvenement, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Récupération de la réponse JSON
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    //remplire les evenements
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("date",id);
                return params;
            }
        };
        //not sure
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());

        requestQueue.add(stringRequest);
    }

}