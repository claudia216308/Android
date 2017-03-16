package com.veliparis.guide;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by claudia on 24/08/2016.
 * Permet de gérer l'affichage du guide : s'affiche automatiquement si c'est la 1ère fois que l'app est lancée
 */
public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {                    //retourne true si c'st la 2ère fois que l'app est lancée
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
