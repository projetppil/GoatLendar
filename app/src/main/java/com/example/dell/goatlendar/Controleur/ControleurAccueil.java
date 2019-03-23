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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.dell.goatlendar.Adapter.AdapterAutocompeteCreateEvent;
import com.example.dell.goatlendar.Adapter.AdapterListEvent;
import com.example.dell.goatlendar.Adapter.AdapterListeInvite;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.evenement.Evenement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ControleurAccueil extends Fragment  {
     //la liste des invite lors de la creation d'un nouveau evenement
    private ListView listeInvitees;
    //pour recuperer la vue cree evenement
    private View promptView;
     Button image_chooser , image_remove;




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


                //remplissage manuellle des utilisateur
                ArrayList<String> personnes = new ArrayList<>();
                personnes.add("Karim");
                personnes.add("Kamel");
                personnes.add("Mohamed");
                personnes.add("Pierre");
                personnes.add("Emilien");
                personnes.add("Julien");

                //permet de stcker les memebre selectionner :  le type a descuter, il faut une class qui stock les information des inviter a la place de string
                final ArrayList<String> values = new ArrayList<>();
                //gestion de l'autocomplete : rechercher une personne
                AdapterAutocompeteCreateEvent adapter = new AdapterAutocompeteCreateEvent(getContext(),personnes);
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
                        values.add(adapterView.getAdapter().getItem(i).toString());
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


        //affichage des evenement dans la page d'accueil
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
                //appel a une fonction qui prend en entree une date et retourne arrayliste<Evenement> contenant la liste des evenement
                Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();

            }

        });



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


}