package claudia.com.sqlitefavoris.stockage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * La classe DataBaseHandler permet de gérer la base
 * Elle hérite de la classe abstraite  SQLiteOpenHelper
 */
public class DataBaseHandler  extends SQLiteOpenHelper {

    //nom de la table  : Favoris
    public static final String FAVORIS_TABLE_NAME = "Favoris";

    //Attributs de la table Favoris
    public static final String FAVORIS_ID = "id";
    public static final String FAVORIS_ANIMAL = "animal";
    public static final String FAVORIS_COULEUR = "couleur";


    //requête de création de la table favoris
    public static final String FAVORIS_TABLE_CREATE =
            "CREATE TABLE " + FAVORIS_TABLE_NAME + " (" +
                    FAVORIS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FAVORIS_ANIMAL + " TEXT, " +
                    FAVORIS_COULEUR + " TEXT); " ;


    public static final String METIER_TABLE_DROP = "DROP TABLE IF EXISTS " + FAVORIS_TABLE_NAME + ";";

    /**
     * Constructeur
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /**
     * Méthode appelée automatiquement la première fois pour créer la base
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FAVORIS_TABLE_CREATE);
    }

    /**
     * Cette méthode est appelée lorque le numéro de version change, elle permet
     * de supprimer l'ancienne base et de créer la nouvelle
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //requête pour supprimer l'ancienne base
            db.execSQL(METIER_TABLE_DROP);

            onCreate(db);  //pour créer la nouvelle base
    }
}
