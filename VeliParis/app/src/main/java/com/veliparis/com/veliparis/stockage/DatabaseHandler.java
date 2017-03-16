package com.veliparis.com.veliparis.stockage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by claudia on 04/02/2017.
 */
public class DatabaseHandler  extends SQLiteOpenHelper{

    //Attributs de la table FavorisActivity
    public static final String FAVORIS_ID = "id";
    public static final String FAVORIS_NOM = "nom";
    public static final String FAVORIS_LONGITUDE = "longitude";
    public static final String FAVORIS_LATITUDE = "latitude";

    //Nom de la table
    public static final String FAVORIS_TABLE_NAME = "Favoris";

    //requête de création de la table favoris
    public static final String FAVORIS_TABLE_CREATE =
            "CREATE TABLE " + FAVORIS_TABLE_NAME + " (" +
                    FAVORIS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FAVORIS_NOM + " TEXT, " +
                    FAVORIS_LONGITUDE + " REAL, " +
                    FAVORIS_LATITUDE + " REAL);";

    /**
     * Constructeur
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Création des tables
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FAVORIS_TABLE_CREATE);
    }


    public static final String METIER_TABLE_DROP = "DROP TABLE IF EXISTS " + FAVORIS_TABLE_NAME + ";";

    /**
     * En cas de mise à jour, suppression de l'ancienne base et création de la nouvell
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(METIER_TABLE_DROP);
        onCreate(db);
    }
}


