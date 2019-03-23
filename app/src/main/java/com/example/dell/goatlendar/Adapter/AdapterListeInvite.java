package com.example.dell.goatlendar.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.example.dell.goatlendar.Notification.Notification;
import com.example.dell.goatlendar.R;

import java.util.ArrayList;

public class AdapterListeInvite extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;

    public AdapterListeInvite(Context context, ArrayList<String> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_invite, parent, false);

        ImageView editPrivilege = (ImageView)rowView.findViewById(R.id.editInvite);

        editPrivilege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                final View promptView = layoutInflater.inflate(R.layout.privilege_invite, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setView(promptView);

                Button valider_priv = (Button) promptView.findViewById(R.id.button_valider_privilege);
                Button annuler_priv = (Button) promptView.findViewById(R.id.button_cancel_privilege);


                final AlertDialog dialog = alertDialogBuilder.create();

                valider_priv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int priv = 0;

                        CheckBox edit = (CheckBox)promptView.findViewById(R.id.invite_privilege_dialog);
                        if(edit.isChecked())
                            priv++;

                        CheckBox invit = (CheckBox)promptView.findViewById(R.id.invite_privilege_dialog);
                        if(invit.isChecked())
                            priv++;

                        CheckBox remove = (CheckBox)promptView.findViewById(R.id.invite_privilege_dialog);
                        if(remove.isChecked())
                            priv++;

                        dialog.dismiss();

                    }
                });

                annuler_priv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                // Display the custom alert dialog on interface
                dialog.show();



            }
        });


        return rowView;
    }
}
