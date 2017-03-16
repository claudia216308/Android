package com.veliparis.connexion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.veliparis.outils.AsynchRecuperation;
import com.veliparis.outils.ConversionMD5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import paris.veli.com.veliparis.MainActivity;
import paris.veli.com.veliparis.R;


public class LoginActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button validation = (Button) findViewById(R.id.validation);
        Button inscription  = (Button) findViewById(R.id.inscription);
        TextView texte = (TextView) findViewById(R.id.texte);


        //Quand on clique sur le bouton valider
       validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText pseudoConnexion = (EditText) findViewById(R.id.pseudoConnexion);
                String email = pseudoConnexion.getText().toString();

                EditText mdpConnexion = (EditText) findViewById(R.id.mdpConnexion);
                String mdp = mdpConnexion.getText().toString();

                final String convert = ConversionMD5.md5(mdp); //CRYPTE LE MOT DE PASSE
                Log.i("mot de passe crypté", convert);

                String url =  getString(R.string.URL_CONNEXION_PHP) + "?pseudo=" + email;
                Log.i("url de connexion :" , url);

                new AsynchRecuperation(new AsynchRecuperation.AsyncResponse() {
                    @Override
                    public void processFinish(Object output) {

                        try {

                            JSONArray jarray = new JSONArray(output.toString());
                            JSONObject jobj = new JSONObject(jarray.getString(0));

                            //comparaison des mots de passe
                            String mdpBDD=(jobj.getString("mdp"));
                            Log.i("mot de passe bdd ", mdpBDD);

                            //récupération du type d'utilisateur
                            if (convert.equals(mdpBDD)){
                                Toast.makeText(getApplicationContext(), "Connexion au compte réussie !", Toast.LENGTH_LONG).show();

                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Erreur : Mot de passe incorrect", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Erreur : Pseudo inconnu", Toast.LENGTH_LONG).show();
                        }
                    }
                },url).execute();


            }
        });


        //Quand on clique sur le bouton inscription
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inter = new Intent(LoginActivity.this, InscriptionActivity.class);
                startActivity(inter);

            }
        });


    }



}

