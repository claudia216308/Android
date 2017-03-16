package com.veliparis.com.veliparis.stockage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import paris.veli.com.veliparis.R;

public class FavorisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //création de l'objet de gestion de la base
        DAOFavoris db  = new DAOFavoris(getApplicationContext());

        //création des favoris
        Favoris fav1  = new Favoris(0, 2.245, 3.2566534, "Maison" );
        Favoris fav2  = new Favoris(0, 4.245, 5.2566534, "Travail" );

        //ouverture d'une connexion
        db.open();

        //ajout des favoris à la base
        db.ajouter(fav1);
        db.ajouter(fav2);


        //récupération du favoris maison
        Favoris result  = db.selectionner("Maison");

        TextView favText = (TextView) findViewById(R.id.favText);
        favText.setText("Mon lieu favoris est " + result.getNom() + "   dont la longitude est   " + result.getLongitude()
                            + "   et la latitude est " + result.getLatitude() );

    }

}
