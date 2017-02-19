package claudia.com.sqlitefavoris.stockage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Classe abstraite pour gérer les interactions avec la base
 */

public abstract class DAO {


        protected final static int VERSION = 1;  //version de la base

        protected final static String NOM = "database.db";

        protected SQLiteDatabase mDb = null;
        protected DataBaseHandler mHandler = null;

    /**
     * Constructeur
     * @param pContext
     */
        public DAO(Context pContext) {
            this.mHandler = new DataBaseHandler(pContext, NOM, null, VERSION);
        }

    /**
     * Méthode pour ouvrir une connexion avec la base
     * @return
     */
        public SQLiteDatabase open() {
            mDb = mHandler.getWritableDatabase();
            return mDb;
        }

        public void close() {
            mDb.close();
        }

    /**
     * Retourne une instance de la base
     * @return
     */
        public SQLiteDatabase getDb() {
            return mDb;
        }
}

