package com.veliparis.objet;

import java.util.Date;
import java.util.List;

/**
 * Created by claudia on 23/08/2016.
 */
public class Semaine {


    private int idUtilisateur;
    private Date dateLundi;
    private List<Integer> listeTemps; // liste de 7 éléments (temps de chaque jour de la semaine )


    public Semaine(Date dateLundi, List<Integer> listeTemps, int idUtilisateur) {
        this.dateLundi = dateLundi;
        this.listeTemps = listeTemps;
        this.idUtilisateur = idUtilisateur;
    }

    public Date getDateLundi() {
        return dateLundi;
    }

    public void setDateLundi(Date dateLundi) {
        this.dateLundi = dateLundi;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public List<Integer> getListeTemps() {
        return listeTemps;
    }

    public void setListeTemps(List<Integer> listeTemps) {
        this.listeTemps = listeTemps;
    }
}
