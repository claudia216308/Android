package com.veliparis.outils;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/* Cette classe permet de récupérer les infos dans la BDD dans un second thread */

public class AsynchRecuperation extends AsyncTask {


    private InputStream is;
    private String json = "";
    private String cheminUrl;


    public interface AsyncResponse {
        void processFinish(Object output);
    }

    public AsyncResponse delegate = null;

    public AsynchRecuperation(AsyncResponse delegate, String cheminFichier){
        this.delegate = delegate;
        this.cheminUrl = cheminFichier;
    }


    @Override
    protected String doInBackground(Object[] params) {


        try {

            //connexion au script PHP
            URL monUrl = new URL(cheminUrl);
            URLConnection connexion = monUrl.openConnection();
            connexion.setConnectTimeout(5000);
            connexion.connect();


            //récupération des données
            is = connexion.getInputStream();

            //on met les données dans un flux
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);

            //conversion en string
            json  = ConversionJSON.convertionEnJson(reader);

            is.close();

            return json;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "erreur de connexion avec la base de donnée";

    }//Background


    @Override
    protected void onPostExecute(Object o) {
            delegate.processFinish(o);
    }//onPost


}//Asynch