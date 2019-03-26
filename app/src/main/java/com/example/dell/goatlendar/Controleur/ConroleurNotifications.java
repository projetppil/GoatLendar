package com.example.dell.goatlendar.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.goatlendar.Adapter.AdapterListeInvite;
import com.example.dell.goatlendar.Adapter.AdapterNotifications;
import com.example.dell.goatlendar.Application;
import com.example.dell.goatlendar.Notification.NotifEvenement;
import com.example.dell.goatlendar.Notification.NotifInformation;
import com.example.dell.goatlendar.Notification.Notification;
import com.example.dell.goatlendar.R;
import com.example.dell.goatlendar.url.Constants;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConroleurNotifications extends Fragment {
    protected Intent intent;
    ArrayList<NotifEvenement> notifications;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.notification , container , false);

       // ListView listView = (ListView)view.findViewById(R.id.listeNotif);

        //AdapterNotifications adapterNotifications = new AdapterNotifications(getActivity() , notifications);
       // listView.setAdapter(adapterNotifications);
        notifications = getNotifications(70);
        System.out.println("======> HERE");
        ListView listeNotifs  = (ListView) view.findViewById(R.id.listeNotif);
        //adaprtateur liste invites
        final AdapterNotifications adapter2 = new AdapterNotifications(getContext(), notifications);
        System.out.printf("LONGUEUR ARRAY LIST : " + notifications.size());
        listeNotifs.setAdapter(adapter2);
        return view;
    }

    public ArrayList<NotifEvenement> getNotifications(int id ) {
        final ArrayList<NotifEvenement> res = new ArrayList<>();
        
        final int idUser = id;
        //System.out.printf("here0");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GetNotifications, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Récupération de la réponse JSON
                try {
                    //System.out.printf("here1");
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getBoolean("error")) {
                        //System.out.println("OSKUR");
                        Toast.makeText(getContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }else{
                        //System.out.printf("here2");
                        int nbNotifs = jsonObject.length()-2;
                        /*System.out.println("================");
                        System.out.println(jsonObject);
                        System.out.println("====> len : " + nbNotifs);
                        System.out.println("================");*/
                        JSONObject jsonObject1;
                        for(int i =0 ;i<nbNotifs;i++) {
                            jsonObject1 = new JSONObject(jsonObject.getString(String.valueOf(i)));
                            /*System.out.println("=======debut========");
                            System.out.println(jsonObject1);
                            System.out.println("=======end=========\n\n");*/
                            NotifEvenement notification = new NotifEvenement(
                                    Integer.valueOf(jsonObject1.getString("IdNotif")),
                                    Integer.valueOf(jsonObject1.getString("IdEvent")),
                                    jsonObject1.getString("Titre"),
                                    jsonObject1.getString("Desc_notif"),
                                    jsonObject1.getString("Nom_eve"));

                            res.add(notification);
                            System.out.println(notifications);
                        }
                        //startActivity(intent);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("idu",""+idUser+"");
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        return res;

    }

}