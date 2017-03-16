package com.veliparis.connexion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.veliparis.outils.AsynchEnvoi;
import com.veliparis.outils.ConversionMD5;
import com.veliparis.objet.Utilisateur;

import paris.veli.com.veliparis.MainActivity;
import paris.veli.com.veliparis.R;

public class InscriptionActivity extends AppCompatActivity {

    private EditText email;
    private EditText mdp;
    private EditText confirmation;
    private EditText prenom;
    private EditText nom;
    private RadioGroup group;
    private Button inscriptionBouton;
    private Utilisateur utilisateur;
    private boolean isSuper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


        email = (EditText) findViewById(R.id.emailInscription);
        mdp = (EditText) findViewById(R.id.mdpInscription);
        confirmation = (EditText)findViewById(R.id.mdpConfirmation);
        nom = (EditText) findViewById(R.id.nomInscription);
        inscriptionBouton = (Button) findViewById(R.id.inscription);


        inscriptionBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //si tous les paramètres sont bon on envoie dans la bdd
                if(verification()){

                    //cryptage du mot de passe
                    String mdpCrypte = ConversionMD5.md5(mdp.getText().toString());

                    utilisateur = new Utilisateur(email.getText().toString(), nom.getText().toString(), mdpCrypte);

                    //Enregistrement dans la bdd
                    AsynchEnvoi task = new AsynchEnvoi(utilisateur, getString(R.string.URL_INSCRIPTION_PHP));
                     task.execute();

                    //Une fois l'inscription terminée, on bascule sur la configuration du compte
                    Intent inter = new Intent(InscriptionActivity.this, ConfigActivity.class);
                    startActivity(inter);
                }


            }
        });

    }

    private boolean verification(){

        //récupération des valeurs
        String emailText  = email.getText().toString();
        String mdpText   = mdp.getText().toString();
        String mdpConfText  = confirmation.getText().toString();
        String pseudoText  = nom.getText().toString();

        //vérification email
        if(emailText.isEmpty()){
            Toast.makeText(getApplicationContext(), "Veuillez entrer un email valide", Toast.LENGTH_LONG).show();
            return false ;
        }

        //vérification pseudo
        else if (pseudoText.isEmpty()){
            Toast.makeText(getApplicationContext(), "Veuillez entre un pseudo ", Toast.LENGTH_LONG).show();
            return false ;
        }
        //A FAIRE : vérifier l'existence du pseudo dans la bdd

        //vérification de l'existence puis de l'égalité des mots de passe :
        else if(mdpText.isEmpty()){
            Toast.makeText(getApplicationContext(), "Veuillez entrer un mot de passe", Toast.LENGTH_LONG).show();
            return false;

        }
        else if(!mdpText.equals(mdpConfText) && !mdpText.isEmpty()){
            Toast.makeText(getApplicationContext(), "Le mot de passe et la confirmation sont différents", Toast.LENGTH_LONG).show();
            return false ;

        }


        return true ;
    }

}
