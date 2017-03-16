package com.veliparis.objet;

/**
 * Created by claudia on 23/08/2016.
 */
public class Favoris {

    private int idUtilisateur;
    private String adresse;
    private String nomFavoris;

    public Favoris(String adresse, int idUtilisateur, String nomFavoris) {
        this.adresse = adresse;
        this.idUtilisateur = idUtilisateur;
        this.nomFavoris = nomFavoris;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomFavoris() {
        return nomFavoris;
    }

    public void setNomFavoris(String nomFavoris) {
        this.nomFavoris = nomFavoris;
    }
}


