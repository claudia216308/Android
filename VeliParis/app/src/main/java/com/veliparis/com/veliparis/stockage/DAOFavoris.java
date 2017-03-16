package com.veliparis.com.veliparis.stockage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by claudia on 04/02/2017.
 */
public class DAOFavoris extends DAOBase {

    public static final String TABLE_NAME = "Favoris";
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOM + " TEXT, "  + LONGITUDE + "  REAL, " + LONGITUDE + " REAL);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public DAOFavoris(Context pContext) {
        super(pContext);
    }

    /**
     * @param m le métier à ajouter à la base
     */
    public void ajouter(Favoris m) {
        //ContentValue  = objet contenant les valeurs à ajouter dans la base (colonne , valeur)
        ContentValues value = new ContentValues();
        value.put(DAOFavoris.NOM, m.getNom());
        value.put(DAOFavoris.LONGITUDE, m.getLongitude());
        value.put(DAOFavoris.LATITUDE, m.getLatitude());

        //ajout dans la base
        mDb.insert(DAOFavoris.TABLE_NAME, null , value); //nom de la table, null, ContentValue

    }

    /**
     * @param id l'identifiant du métier à supprimer
     */
    public void supprimer(long id) {
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    /**
     * @param m le métier modifié
     */
    public void modifier(Favoris m) {
        // CODE
    }

    /**
     * @param nom le nom du favoris à récupérer
     */
    public Favoris selectionner(String nom) {
        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where nom = ?", new String[]{nom});

        //itération sur les résultats
        Favoris fav  = null;
        while (cursor.moveToNext()) {  //ligne par ligne
            //pour chaque ligne
            long ids = cursor.getLong(0);
            String nomR = cursor.getString(1); //le numéro de la colonne est 1 pour nom
            double longitude  = cursor.getDouble(2);
            double latitude  = cursor.getDouble(3);

            fav = new Favoris(ids, latitude,longitude,nomR);
        }
        cursor.close();

        return fav;
    }
}
