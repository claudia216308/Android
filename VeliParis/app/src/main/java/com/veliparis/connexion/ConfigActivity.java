package com.veliparis.connexion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import paris.veli.com.veliparis.R;

/**
 * Classe qui permet de configurer un compte utilisateur juste après l'inscription
 *          --> ajout d'une photo de profil
 *          --> demander l'autorisation pour la géolocalisation
 *          --> numéro vélib
 */
public class ConfigActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        //récupération des paramètres
        Bundle b = getIntent().getExtras();


        if(b == null ){  //si on a pas fait une fois la configuration
            //Ajout de la photo de profil
            Intent inter = new Intent(ConfigActivity.this, PhotoActivity.class);
            startActivity(inter);

        }


    }

}
