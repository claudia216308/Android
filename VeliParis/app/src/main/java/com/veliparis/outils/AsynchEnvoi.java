/*
Copyright (C) 2015 Techis4Help, Sébastien Bodin

All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:
1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products
   derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

Contributors:

*/


package com.veliparis.outils;


import android.os.AsyncTask;
import android.util.Log;

import com.veliparis.objet.Utilisateur;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;



public class AsynchEnvoi extends AsyncTask {


    protected InputStream is;
    protected String json = "";
    protected JSONArray jobj;
    private Utilisateur monUtilisateur;
    private String chemin;


    public AsynchEnvoi(Utilisateur ut, String cheminFichier) {
        monUtilisateur = ut;
        chemin  = cheminFichier;
    }

    @Override
    protected Boolean doInBackground(Object[] params) {

        try {


            //Données à envoyer
            String get="?pseudo=" + monUtilisateur.getPseudo();
            get += "&email=" + monUtilisateur.getEmail();
            get += "&mdp=" + monUtilisateur.getMdp();
            get += "&urlPhoto=" + monUtilisateur.getUrlPhoto();


            chemin += get;
            Log.i("url finale :", chemin);

            //connexion au script PHP
            URL monUrl = new URL(chemin);
            URLConnection connexion = monUrl.openConnection();
            connexion.setConnectTimeout(5000);
            connexion.connect();


            is = connexion.getInputStream();
            Log.i("is", is.toString());

            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }//Background

    @Override
    protected void onPostExecute(Object o) {

    }//onPost
}//Asynch