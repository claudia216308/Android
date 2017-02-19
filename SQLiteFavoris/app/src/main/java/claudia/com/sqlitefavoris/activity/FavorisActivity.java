package claudia.com.sqlitefavoris.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import claudia.com.sqlitefavoris.R;
import claudia.com.sqlitefavoris.stockage.*;

public class FavorisActivity extends AppCompatActivity {

    private EditText couleur;
    private EditText animal;
    private DAOFavoris db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        Button bouton = (Button) findViewById(R.id.button);
        couleur = (EditText) findViewById(R.id.couleur);
        animal = (EditText) findViewById(R.id.animal);

        
        //création de l'objet de gestion de la base
         db  = new DAOFavoris(getApplicationContext());

        //qd l'utilisateur clique sur le bouton valider
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //création du favori
                Favoris fav1 = new Favoris(animal.getText().toString(), couleur.getText().toString());

                //ouverture d'une connexion
                db.open();

                //ajout du favori à la base
                db.ajouter(fav1);

                //lancement de l'activité qui affiche le favoris
                Intent inter = new Intent(FavorisActivity.this, ResultActivity.class);
                startActivity(inter);

            }


        });



    }
}
