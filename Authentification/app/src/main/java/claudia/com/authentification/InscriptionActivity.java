package claudia.com.authentification;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import claudia.com.authentification.com.claudia.authentification.outils.AsynchEnvoi;
import claudia.com.authentification.com.claudia.authentification.outils.ConversionMD5;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        email = (EditText) findViewById(R.id.emailInscription);
        mdp = (EditText) findViewById(R.id.mdpInscription);
        confirmation = (EditText)findViewById(R.id.mdpConfirmation);
        prenom = (EditText) findViewById(R.id.prenomInscription);
        nom = (EditText) findViewById(R.id.nomInscription);
        group = (RadioGroup) findViewById(R.id.group);
        inscriptionBouton = (Button) findViewById(R.id.inscription);


        inscriptionBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(group.getCheckedRadioButtonId() == R.id.humain){
                    isSuper = false;
                }

                //vérification de l'égalité des mots de passe :
                if(!mdp.getText().toString().equals(confirmation.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Le mot de passe et la confirmation sont différents", Toast.LENGTH_LONG).show();

                }

                else {

                    //cryptage du mot de passe
                    String mdpCrypte = ConversionMD5.md5(mdp.getText().toString());

                    utilisateur = new Utilisateur(email.getText().toString(), isSuper,mdpCrypte, nom.getText().toString(), prenom.getText().toString());

                    //Enregistrement dans la bdd
                    AsynchEnvoi task = new AsynchEnvoi(utilisateur, getString(R.string.URL_INSCRIPTION_PHP));
                    task.execute();

                    Intent inter = new Intent(InscriptionActivity.this, ConnexionActivity.class);
                    startActivity(inter);
                }


            }
        });

    }


}
