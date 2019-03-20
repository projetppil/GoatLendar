package com.example.dell.goatlendar.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.dell.goatlendar.R;

public class ControleurLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void inscription(View view) {
        Intent intent = new Intent(this , ControleurInscription.class);
        startActivity(intent);
    }

    public void connexion(View view) {
        /**
         * verification des champs
         */
        Intent intent = new Intent(this , ControleurMenu.class);
        startActivity(intent);
    }
}
