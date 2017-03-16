package paris.veli.com.veliparis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.veliparis.com.veliparis.stockage.Favoris;
import com.veliparis.com.veliparis.stockage.FavorisActivity;
import com.veliparis.connexion.InscriptionActivity;
import com.veliparis.connexion.LoginActivity;
import com.veliparis.connexion.PhotoActivity;
import com.veliparis.guide.GuideActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sql
        Button sqlButton  = (Button) findViewById(R.id.sql);
        sqlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inter = new Intent(MainActivity.this, FavorisActivity.class);
                startActivity(inter);

            }
        });

        //si l'utilisateur s'est déconnecté la dernière fois
            //LoginActivity
        Button login = (Button) findViewById(R.id.loginButton);

        //Quand on clique sur le bouton inscription
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inter = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(inter);

            }
        });


        //si c'est la première fois que l'application s'ouvre
            //Inscription
        Button inscription  = (Button) findViewById(R.id.inscriptionButton);

        //Quand on clique sur le bouton inscription
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inter = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(inter);

            }
        });


        //si l'utilisateur est connecté
            //Accueil
        Button accueil  = (Button) findViewById(R.id.accueilButton);
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inter  = new Intent(MainActivity.this, GuideActivity.class);
                //ajout de paramètre à l'activité pour lancer le guide à partir d'un bouton
                inter.putExtra("accueil", "display");
                startActivity(inter);

            }
        });


        //bouton Photo

        Button photo = (Button) findViewById(R.id.photoButton);
        photo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent inter  = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(inter);

            }
        });

    }
}
