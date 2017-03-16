package claudia.com.sqlitefavoris.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import claudia.com.sqlitefavoris.R;
import claudia.com.sqlitefavoris.stockage.DAOFavoris;
import claudia.com.sqlitefavoris.stockage.Favoris;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView result  = (TextView) findViewById(R.id.result);


        //récupération du favoris
        DAOFavoris db = new DAOFavoris(getApplicationContext());
        db.open();

        Favoris fav  = db.selectionner();

        result.setText(fav.getAnimal() + " de couleur " + fav.getCouleur());
    }


}
