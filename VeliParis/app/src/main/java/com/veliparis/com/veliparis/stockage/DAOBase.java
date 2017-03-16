package com.veliparis.com.veliparis.stockage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by claudia on 04/02/2017.
 */
public abstract class DAOBase {

    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 1;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "database.db";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {

        mDb = mHandler.getWritableDatabase();
                            //getWritableDataBase ferme la base automatiquement
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
