package claudia.com.sqlitefavoris.stockage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 *
 */
public class DAOFavoris extends DAO {

    public static final String TABLE_NAME = "Favoris";

    public static final String COULEUR = "couleur";
    public static final String ANIMAL = "animal";

    /**
     * Constructeur
     * @param pContext
     */
    public DAOFavoris(Context pContext) {
        super(pContext);
    }


    /**
     * Méthode pour ajouter un enregistrement dans la base
     */
    public void ajouter(Favoris fav) {

        //Création de l'élément à ajouter
        ContentValues value = new ContentValues();
        value.put(DAOFavoris.ANIMAL ,  fav.getAnimal());
        value.put(DAOFavoris.COULEUR ,  fav.getCouleur());


        //ajout dans la base
        mDb.insert(DAOFavoris.TABLE_NAME, null, value);
    }

    /**
     * Récupération du favoris en fonction de la couleur
     */
    public Favoris selectionner() {

        String query   = "select * from " + TABLE_NAME  ;
        Cursor cursor = mDb.rawQuery(query, null );

        Favoris fav  = null;
        while (cursor.moveToNext()) {  //on parcourt les résultats ligne par ligne
            //pour chaque ligne
            long ids = cursor.getLong(0);
            String animal = cursor.getString(1); //l'animal est dans la colonne n°1
            String couleur = cursor.getString(2); //la couleur dans la colonne n°2

            fav = new Favoris(animal,couleur);
        }
        cursor.close();

        return fav;

    }

    /**
     * Récupération du favoris en fonction de la couleur
     * @param  couleur du favoris
     */
    public Favoris selectionner(String couleur) {

        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where couleur = ?", new String[]{couleur});

        Favoris fav  = null;
        while (cursor.moveToNext()) {  //on parcourt les résultats ligne par ligne
            //pour chaque ligne
            long ids = cursor.getLong(0);
            String animal = cursor.getString(1); //l'animal est dans la colonne n°1
            couleur = cursor.getString(2); //la couleur dans la colonne n°2

            fav = new Favoris(animal,couleur);
        }
        cursor.close();

        return fav;

    }



}
