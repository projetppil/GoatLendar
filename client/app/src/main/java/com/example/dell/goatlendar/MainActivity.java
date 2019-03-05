package com.example.dell.goatlendar;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mdp_oubler(View view) {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.mdp_oublier);
        dialog.show();

    }
}
